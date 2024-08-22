package cn.bugstack.domain.activity.service.quota.rule;

import cn.bugstack.domain.activity.model.entity.ActivityCountEntity;
import cn.bugstack.domain.activity.model.entity.ActivityEntity;
import cn.bugstack.domain.activity.model.entity.ActivitySkuEntity;

/**
 * @DateTime: 2024/8/20
 * @Description: 下单规则过滤接口
 * @Author: 阿涛
 **/
public interface IActionChain extends IActionChainArmory {
    boolean action(ActivitySkuEntity activitySkuEntity, ActivityEntity activityEntity, ActivityCountEntity activityCountEntity);
}
