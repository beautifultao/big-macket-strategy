package cn.bugstack.domain.activity.model.entity;

import lombok.Data;

/**
 * @DateTime: 2024/8/21
 * @Description: 参与抽奖活动实体对象
 * @Author: 阿涛
 **/
@Data
public class PartakeRaffleActivityEntity {

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 活动ID
     */
    private Long activityId;

}

