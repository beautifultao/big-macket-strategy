package cn.bugstack.domain.strategy.service.rule.chains;

/**
 * @DateTime: 2024/8/16
 * @Description: 责任链装配
 * @Author: 阿涛
 **/
public interface ILogicChainArmory {
    ILogicChain next();
    ILogicChain appendNext(ILogicChain next);
}
