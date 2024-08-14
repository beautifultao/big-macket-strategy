package cn.bugstack.domain.strategy.repository;

import cn.bugstack.domain.strategy.model.entity.StrategyAwardEntity;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

/**
 * @DateTime: 2024/8/14
 * @Description: 策略仓储服务
 * @Author: 阿涛
 **/
public interface IStrategyRepository {
    List<StrategyAwardEntity> queryStrategyAwardList(Long strategyId);

    void storeStrategyAwardRateTable(Long strategyId, int rateRange, HashMap<Integer, Integer> shuffleStrategyAwardTable);

    int getRateRange(Long strategyId);

    Integer getStrategyAwardAssemble(Long strategyId, int rateKey);
}
