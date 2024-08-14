package cn.bugstack.test.infrastructure;

import cn.bugstack.infrastructure.persistent.redis.IRedisService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RMap;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @DateTime: 2024/8/14
 * @Description: TODO(一句话描述此类的作用)
 * @Author: 阿涛
 **/
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
    @Resource
    private IRedisService redisService;

    @Test
    public void test() {
        RMap<Object, Object> map = redisService.getMap("strategy_id_10001");
        map.put("key1", "value1");
        map.put("key2", "value2");
        log.info("Map: {}", redisService.getFromMap("strategy_id_10001","key1"));
    }
}
