package cn.bugstack.domain.strategy.repository;

import cn.bugstack.domain.strategy.model.entity.StrategyAwardEntity;
import cn.bugstack.domain.strategy.model.entity.StrategyEntity;
import cn.bugstack.domain.strategy.model.entity.StrategyRuleEntity;
import cn.bugstack.domain.strategy.model.vo.RuleTreeVO;
import cn.bugstack.domain.strategy.model.vo.StrategyAwardRuleModelVO;
import cn.bugstack.domain.strategy.model.vo.StrategyAwardStockKeyVO;

import java.util.List;
import java.util.Map;

/**
 * @DateTime: 2024/8/14
 * @Description: 策略仓储服务
 * @Author: 阿涛
 **/
public interface IStrategyRepository {
    List<StrategyAwardEntity> queryStrategyAwardList(Long strategyId);

    void storeStrategyAwardRateTable(String key, int rateRange, Map<Integer, Integer> shuffleStrategyAwardTable);

    int getRateRange(Long strategyId);

    int getRateRange(String key);

    Integer getStrategyAwardAssemble(String key, int rateKey);

    StrategyEntity queryStrategy(Long strategyId);

    StrategyRuleEntity queryStrategyRule(Long strategyId, String ruleModel);

    String queryStrategyRuleValue(Long strategyId, String ruleModel);

    String queryStrategyRuleValue(Long strategyId, Integer awardId, String ruleModel);

    StrategyAwardRuleModelVO queryStrategyAwardRuleModelVO(Long strategyId, Integer awardId);

    RuleTreeVO queryRuleTreeVOByTreeId(String treeId);

    void cacheStrategyAwardCount(String cacheKey, Integer awardCount);

    boolean subtractionAwardCount(String cacheKey);

    void awardStockConsumeSendQueue(StrategyAwardStockKeyVO strategyAwardStockKeyVO);

    StrategyAwardStockKeyVO takeQueueValue();

    void updateStrategyAwardStock(Long strategyId, Integer awardId);
}
