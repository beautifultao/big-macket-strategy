package cn.bugstack.domain.strategy.service.rule.chains;

/**
 * @DateTime: 2024/8/16
 * @Description: 抽奖策略规则责任链接口
 * @Author: 阿涛
 **/
public interface ILogicChain extends ILogicChainArmory{
    /**
     * 责任链接口
     *
     * @param userId     用户ID
     * @param strategyId 策略ID
     * @return 奖品ID
     */

    Integer logic(String userId, Long strategyId);
}
