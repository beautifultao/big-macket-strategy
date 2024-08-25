package cn.bugstack.trigger.api.dto;

import lombok.Data;

/**
 * @DateTime: 2024/8/24
 * @Description: 用户活动账户请求对象
 * @Author: 阿涛
 **/

@Data
public class UserActivityAccountRequestDTO {
    /**
     * 用户ID
     */
    private String userId;

    /**
     * 活动ID
     */
    private Long activityId;
}