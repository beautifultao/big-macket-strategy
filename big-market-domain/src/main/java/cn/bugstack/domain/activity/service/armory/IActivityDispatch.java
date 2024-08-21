package cn.bugstack.domain.activity.service.armory;

import java.util.Date;

/**
 * @DateTime: 2024/8/21
 * @Description: TODO(一句话描述此类的作用)
 * @Author: 阿涛
 **/
public interface IActivityDispatch {

    /**
     * 根据策略ID和奖品ID，扣减奖品缓存库存
     *
     * @param sku 互动SKU
     * @param endDateTime 活动结束时间，根据结束时间设置加锁的key为结束时间
     * @return 扣减结果
     */
    boolean subtractionActivitySkuStock(Long sku, Date endDateTime);
}
