package cn.bugstack.trigger.api.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @DateTime: 2024/8/19
 * @Description: 抽奖奖品列表，请求对象
 * @Author: 阿涛
 **/
@Data
public class RaffleAwardListRequestDTO implements Serializable {

    // 抽奖策略ID
    @Deprecated
    private Long strategyId;

    // 用户ID
    private String userId;

    // 抽奖活动ID
    private Long activityId;
}
