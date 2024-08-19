package cn.bugstack.trigger.api.dto;

import lombok.Data;

/**
 * @DateTime: 2024/8/19
 * @Description: 抽奖请求参数
 * @Author: 阿涛
 **/
@Data
public class RaffleRequestDTO {

    // 抽奖策略ID
    private Long strategyId;

}
