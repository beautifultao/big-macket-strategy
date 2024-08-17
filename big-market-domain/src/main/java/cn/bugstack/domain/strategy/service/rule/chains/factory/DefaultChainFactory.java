package cn.bugstack.domain.strategy.service.rule.chains.factory;

import cn.bugstack.domain.strategy.model.entity.StrategyEntity;
import cn.bugstack.domain.strategy.repository.IStrategyRepository;
import cn.bugstack.domain.strategy.service.rule.chains.ILogicChain;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;

/**
 * @DateTime: 2024/8/16
 * @Description: 搭建责任链工程
 * @Author: 阿涛
 **/
@Service
public class DefaultChainFactory {
    private final Map<String, ILogicChain> logicChainGroup;
    protected IStrategyRepository repository;

    public DefaultChainFactory(Map<String, ILogicChain> logicChainGroup, IStrategyRepository repository) {
        this.logicChainGroup = logicChainGroup;
        this.repository = repository;
    }

    public ILogicChain openLogicChain(Long strategyId) {
        StrategyEntity strategy = repository.queryStrategy(strategyId);
        String[] ruleModels = strategy.ruleModels();

        // 如果未配置策略规则，则只装填一个默认责任链
        if (null == ruleModels || 0 == ruleModels.length) return logicChainGroup.get("default");

        ILogicChain logicChain;
        ILogicChain current;
        boolean hasBlacklist = Arrays.asList(ruleModels).contains("rule_blacklist");

        if (hasBlacklist) {
            // 将 "rule_blacklist" 作为第一个责任链
            logicChain = logicChainGroup.get("rule_blacklist");
            current = logicChain;

            // 将 "rule_blacklist" 过滤掉，防止重复添加
            ruleModels = Arrays.stream(ruleModels)
                    .filter(rule -> !"rule_blacklist".equals(rule))
                    .toArray(String[]::new);
        } else {
            // 否则按顺序取第一个规则作为责任链的开始
            logicChain = logicChainGroup.get(ruleModels[0]);
            current = logicChain;
        }

        // 按照配置顺序装填用户配置的责任链
        for (String ruleModel : ruleModels) {
            ILogicChain nextChain = logicChainGroup.get(ruleModel);
            current = current.appendNext(nextChain);
        }

        // 责任链的最后装填默认责任链
        current.appendNext(logicChainGroup.get("default"));

        return logicChain;
    }


}
