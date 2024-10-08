package cn.bugstack.domain.strategy.service;

import cn.bugstack.domain.strategy.model.vo.RuleWeightVO;

import java.util.List;
import java.util.Map;

/**
 * @DateTime: 2024/8/23
 * @Description: 抽奖规则接口；提供对规则的业务功能查询
 * @Author: 阿涛
 **/
public interface IRaffleRule {

    /**
     * 根据规则树ID集合查询奖品中加锁数量的配置「部分奖品需要抽奖N次解锁」
     *
     * @param treeIds 规则树ID值
     * @return key 规则树，value rule_lock 加锁值
     */
    Map<String, Integer> queryAwardRuleLockCount(String[] treeIds);

    List<RuleWeightVO> queryAwardRuleWeightByActivityId(Long activityId);

    List<RuleWeightVO> queryAwardRuleWeight(Long strategyId);
}
