package cn.bugstack.domain.task.repository;

import cn.bugstack.domain.task.model.entity.TaskEntity;

import java.util.List;

/**
 * @DateTime: 2024/8/22
 * @Description: 任务服务仓储接口
 * @Author: 阿涛
 **/
public interface ITaskRepository {
    List<TaskEntity> queryNoSendMessageTaskList();

    void sendMessage(TaskEntity taskEntity);

    void updateTaskSendMessageCompleted(String userId, String messageId);

    void updateTaskSendMessageFail(String userId, String messageId);
}
