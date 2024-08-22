package cn.bugstack.domain.award.repository;

import cn.bugstack.domain.award.model.aggregate.UserAwardRecordAggregate;

/**
 * @DateTime: 2024/8/22
 * @Description: 奖品仓储服务
 * @Author: 阿涛
 **/
public interface IAwardRepository {
    void saveUserAwardRecord(UserAwardRecordAggregate userAwardRecordAggregate);
}
