package cn.bugstack.config;

import cn.bugstack.types.annotations.DCCValue;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.CuratorCache;
import org.apache.curator.framework.recipes.cache.CuratorCacheListener;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @DateTime: 2024/8/25
 * @Description: 基于 Zookeeper 的配置中心实现原理
 * @Author: 阿涛
 **/
@Slf4j
@Configuration
public class DCCValueBeanFactory implements BeanPostProcessor {

    private static final String BASE_CONFIG_PATH = "/big-market-dcc";

    private static final String BASE_CONFIG_PATH_CONFIG = BASE_CONFIG_PATH + "/config";

    private final CuratorFramework client;

    private final Map<String, Object> dccObjGroup = new HashMap<>();

    public DCCValueBeanFactory(CuratorFramework client) throws Exception {
        this.client = client;
        // 检查Zookeeper中指定路径的节点是否存在
        if (null == client.checkExists().forPath(BASE_CONFIG_PATH_CONFIG)) {
            // 如果不存在，则创建这些中间节点
            client.create().creatingParentsIfNeeded().forPath(BASE_CONFIG_PATH_CONFIG);
            log.info("DCC 节点监听 base node {} not absent create new done!", BASE_CONFIG_PATH_CONFIG);
        }

        // 注册一个监听器，监听Zookeeper路径下节点是否变化
        CuratorCache curatorCache = CuratorCache.build(client, BASE_CONFIG_PATH_CONFIG);
        curatorCache.start();

        curatorCache.listenable().addListener((type, oldData, data) -> {
             if (Objects.requireNonNull(type) == CuratorCacheListener.Type.NODE_CHANGED) {
                String dccValuePath = data.getPath();
                Object objBean = dccObjGroup.get(dccValuePath);
                if (null == objBean) return;
                try {
                    Class<?> objBeanClass = objBean.getClass();
                    // 检查 objBean 是否是代理对象
                    if (AopUtils.isAopProxy(objBean)) {
                        // 获取代理对象的目标对象
                        objBeanClass = AopUtils.getTargetClass(objBean);
                    }

                    // 1. getDeclaredField 方法用于获取指定类中声明的所有字段，包括私有字段、受保护字段和公共字段。
                    // 2. getField 方法用于获取指定类中的公共字段，即只能获取到公共访问修饰符（public）的字段。
                    Field field = objBeanClass.getDeclaredField(dccValuePath.substring(dccValuePath.lastIndexOf("/") + 1));
                    field.setAccessible(true);
                    field.set(objBean, new String(data.getData()));
                    field.setAccessible(false);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        // 注意；增加 AOP 代理后，获得类的方式要通过 AopProxyUtils.getTargetClass(bean); 不能直接 bean.class 因为代理后类的结构发生变化，这样不能获得到自己的自定义注解了。
        Class<?> targetBeanClass = bean.getClass();
        Object targetBeanObject = bean;
        if (AopUtils.isAopProxy(bean)) {
            targetBeanClass = AopUtils.getTargetClass(bean);
            targetBeanObject = AopProxyUtils.getSingletonTarget(bean);
        }

        Field[] fields = targetBeanClass.getDeclaredFields();

        // 获取加了@DCCValue注解的字段
        for (Field field : fields) {
            if (!field.isAnnotationPresent(DCCValue.class)) {
                continue;
            }
            DCCValue dccValue = field.getAnnotation(DCCValue.class);

            String value = dccValue.value();
            if (StringUtils.isBlank(value)) {
                throw new RuntimeException(field.getName() + " @DCCValue is not config value config case 「isSwitch/isSwitch:1」");
            }

            String[] splits = value.split(":");
            String key = splits[0];
            String defaultValue = splits.length == 2 ? splits[1] : null;

            String keyPath = BASE_CONFIG_PATH_CONFIG.concat("/").concat(key);
            try {
                // 判断当前节点是否存在，不存在则创建出 Zookeeper 节点
                if (null == client.checkExists().forPath(keyPath)) {
                    client.create().creatingParentsIfNeeded().forPath(keyPath);
                    if (StringUtils.isNotBlank(defaultValue)) {
                        field.setAccessible(true);
                        field.set(targetBeanObject, defaultValue);
                        field.setAccessible(false);
                    }
                    log.info("DCC 节点监听 创建节点 {}", keyPath);
                } else {
                    // 读取指定路径上节点的数据
                    String configValue = new String(client.getData().forPath(keyPath));
                    if (StringUtils.isNotBlank(configValue)) {
                        field.setAccessible(true);
                        field.set(targetBeanObject, defaultValue);
                        field.setAccessible(false);
                    }
                    log.info("DCC 节点监听 设置配置 {} {} {}", keyPath, field.getName(), configValue);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            dccObjGroup.put(keyPath, targetBeanObject);
        }
        return bean;
    }
}
