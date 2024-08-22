package cn.bugstack.domain.task.model.entity;

import lombok.Data;

/**
 * @DateTime: 2024/8/22
 * @Description: 任务实体对象
 * @Author: 阿涛
 **/
@Data
public class TaskEntity {

    /** 活动ID */
    private String userId;
    /** 消息主题 */
    private String topic;
    /** 消息编号 */
    private String messageId;
    /** 消息主体 */
    private String message;

}
