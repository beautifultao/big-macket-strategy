package cn.bugstack.domain.rebate.model.entity;

import cn.bugstack.domain.award.model.valobj.TaskStateVO;
import cn.bugstack.domain.rebate.event.SendRebateMessageEvent;
import cn.bugstack.types.event.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @DateTime: 2024/8/24
 * @Description: TODO(一句话描述此类的作用)
 * @Author: 阿涛
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskEntity {

    /** 活动ID */
    private String userId;

    /** 消息主题 */
    private String topic;

    /** 消息编号 */
    private String messageId;

    /** 消息主体 */
    private BaseEvent.EventMessage<SendRebateMessageEvent.RebateMessage> message;

    /** 任务状态；create-创建、completed-完成、fail-失败 */
    private TaskStateVO state;
}
