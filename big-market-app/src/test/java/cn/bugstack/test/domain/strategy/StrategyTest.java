package cn.bugstack.test.domain.strategy;

import cn.bugstack.domain.strategy.service.armory.IStrategyArmory;
import cn.bugstack.domain.strategy.service.armory.IStrategyDispatch;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
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
public class StrategyTest {
    @Resource
    private IStrategyDispatch strategyDispatch;

    @Resource
    private IStrategyArmory strategyArmory;

    /**
     * 策略ID；100001L、100002L 装配的时候创建策略表写入到 Redis Map 中
     */
    @Test
    public void test_strategyArmory() {
        boolean success = strategyArmory.assembleLotteryStrategy(100001L);
        log.info("测试结果：{}", success);
    }
    @Test
    public void test_getRandomAwardId() {
        log.info("测试结果：{} - 奖品ID值", strategyDispatch.getRandomAwardId(100001L));
    }


    @Test
    public void test_getRandomAwardId_ruleWeightValue() {
        Integer awardId = strategyDispatch.getRandomAwardId(100001L, "4000:102,103,104");
        log.info("测试结果：{} - 4000 策略配置", awardId);
        log.info("测试结果：{} - 5000 策略配置", strategyDispatch.getRandomAwardId(100001L, "5000:102,103,104,105,106,107"));
        log.info("测试结果：{} - 6000 策略配置", strategyDispatch.getRandomAwardId(100001L, "6000:102,103,104,105,106,107,108,109"));
    }



}
