package cn.bugstack.trigger.api;

import cn.bugstack.types.model.Response;

/**
 * @DateTime: 2024/8/25
 * @Description: 动态配置中心
 * @Author: 阿涛
 **/
public interface IDCCService {
    Response<Boolean> updateConfig(String key, String value);
}
