package cn.bugstack.domain.activity.service.quota.rule;

/**
 * @DateTime: 2024/8/20
 * @Description: TODO(一句话描述此类的作用)
 * @Author: 阿涛
 **/
public interface IActionChainArmory {

    IActionChain next();

    IActionChain appendNext(IActionChain next);

}
