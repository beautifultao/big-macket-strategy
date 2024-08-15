package cn.bugstack.infrastructure.persistent.dao;

import cn.bugstack.infrastructure.persistent.po.StrategyRule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;

/**
 * @DateTime: 2024/8/13
 * @Description: 抽奖策略规则Dao
 * @Author: 阿涛
 **/
@Mapper
public interface IStrategyRuleDao {
    List<StrategyRule> queryStrategyRuleList();

    StrategyRule queryStrategyRule(@Param("strategyId") Long strategyId, @Param("ruleModel") String ruleModel);

    String queryStrategyRuleValue(StrategyRule strategyRule);
}
