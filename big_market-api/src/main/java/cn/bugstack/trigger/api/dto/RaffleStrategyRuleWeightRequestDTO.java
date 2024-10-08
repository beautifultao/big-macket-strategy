package cn.bugstack.trigger.api.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @DateTime: 2024/8/24
 * @Description: 抽奖策略规则，权重配置，查询N次抽奖可解锁奖品范围，请求对象
 * @Author: 阿涛
 **/
@Data
public class RaffleStrategyRuleWeightRequestDTO implements Serializable {

    // 用户ID
    private String userId;
    // 抽奖活动ID
    private Long activityId;

}

