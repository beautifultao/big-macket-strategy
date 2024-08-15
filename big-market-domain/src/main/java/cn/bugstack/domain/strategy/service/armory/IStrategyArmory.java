package cn.bugstack.domain.strategy.service.armory;

import cn.bugstack.domain.strategy.model.entity.StrategyAwardEntity;

import java.util.List;

/**
 * @DateTime: 2024/8/14
 * @Description: 策略装配
 * @Author: 阿涛
 **/
public interface IStrategyArmory {
    boolean assembleLotteryStrategy(Long strategyId);
}
