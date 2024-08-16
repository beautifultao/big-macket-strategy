package cn.bugstack.domain.strategy.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @DateTime: 2024/8/15
 * @Description: 抽奖因子
 * @Author: 阿涛
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RaffleFactorEntity {
    /** 用户ID */
    private String userId;
    /** 策略ID */
    private Long strategyId;
    /** 奖品ID*/
    private Integer awardId;
}
