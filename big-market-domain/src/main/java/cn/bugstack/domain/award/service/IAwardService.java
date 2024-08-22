package cn.bugstack.domain.award.service;

import cn.bugstack.domain.award.model.entity.UserAwardRecordEntity;

/**
 * @DateTime: 2024/8/22
 * @Description: 奖品服务
 * @Author: 阿涛
 **/
public interface IAwardService {
    void saveUserAwardRecord(UserAwardRecordEntity userAwardRecordEntity);
}
