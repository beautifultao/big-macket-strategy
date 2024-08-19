package cn.bugstack.domain.strategy.service.rule.chains.factory;

import cn.bugstack.domain.strategy.model.entity.StrategyEntity;
import cn.bugstack.domain.strategy.repository.IStrategyRepository;
import cn.bugstack.domain.strategy.service.rule.chains.ILogicChain;
import lombok.*;
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

        ILogicChain logicChain = null;
        ILogicChain current = null;

        // 检查是否包含 "rule_blacklist"
        if (Arrays.asList(ruleModels).contains("rule_blacklist")) {
            logicChain = logicChainGroup.get("rule_blacklist");
            current = logicChain;
        }

        // 按照顺序装填责任链，并跳过已处理的 "rule_blacklist"
        for (String ruleModel : ruleModels) {
            if ("rule_blacklist".equals(ruleModel) && logicChain != null) {
                continue; // 跳过已经添加到责任链的 "rule_blacklist"
            }
            ILogicChain nextChain = logicChainGroup.get(ruleModel);
            if (logicChain == null) {
                logicChain = nextChain;  // 初始化第一个责任链
                current = nextChain;
            } else {
                current = current.appendNext(nextChain);
            }
        }

        // 确保责任链的最后装填默认责任链
        if (current != null) {
            current.appendNext(logicChainGroup.get("default"));
        } else {
            logicChain = logicChainGroup.get("default");
        }

        return logicChain;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class StrategyAwardVO {

        private Integer awardId;
        private String logicModel;
    }

    @Getter
    @AllArgsConstructor
    public enum LogicModel {

        RULE_DEFAULT("rule_default", "默认抽奖"),
        RULE_BLACKLIST("rule_blacklist", "黑名单抽奖"),
        RULE_WEIGHT("rule_weight", "权重规则"),
        ;

        private final String code;
        private final String info;

    }
}
