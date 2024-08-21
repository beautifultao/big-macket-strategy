package cn.bugstack.domain.activity.service.rule;

/**
 * @DateTime: 2024/8/20
 * @Description: TODO(一句话描述此类的作用)
 * @Author: 阿涛
 **/
public abstract class AbstractActionChain implements IActionChain{
    private IActionChain nextChain;

    @Override
    public IActionChain next() {
        return nextChain;
    }

    @Override
    public IActionChain appendNext(IActionChain next) {
        this.nextChain = next;
        return next;
    }

}
