package cn.bugstack.domain.strategy.service.armory;

/**
 * @DateTime: 2024/8/14
 * @Description: 策略抽奖调度
 * @Author: 阿涛
 **/
public interface IStrategyDispatch {
    Integer getRandomAwardId(Long strategyId);

    Integer getRandomAwardId(Long strategyId, String ruleWeightValue);

    boolean subtractionAwardStock(Long strategyId, Integer awardId);
}
