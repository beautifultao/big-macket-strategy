package cn.bugstack.domain.strategy.service.rule.chains;

/**
 * @DateTime: 2024/8/16
 * @Description: TODO(一句话描述此类的作用)
 * @Author: 阿涛
 **/
public abstract class AbstractLogicChain implements ILogicChain{
    private ILogicChain nextChain;

    @Override
    public ILogicChain next() {
        return nextChain;
    }

    @Override
    public ILogicChain appendNext(ILogicChain next) {
        this.nextChain = next;
        return next;
    }

    protected abstract String ruleModel();
}
