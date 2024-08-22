package cn.bugstack.infrastructure.persistent.dao;

import cn.bugstack.infrastructure.persistent.po.UserAwardRecord;
import cn.bugstack.middleware.db.router.annotation.DBRouterStrategy;
import org.apache.ibatis.annotations.Mapper;

/**
 * @DateTime: 2024/8/21
 * @Description: 用户中奖记录表
 * @Author: 阿涛
 **/
@Mapper
@DBRouterStrategy(splitTable = true)
public interface IUserAwardRecordDao {
    void insert(UserAwardRecord userAwardRecord);
}
