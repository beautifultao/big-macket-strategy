package cn.bugstack.domain.activity.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @DateTime: 2024/8/20
 * @Description: 订单状态枚举值对象（用于描述对象属性的值，如枚举，不影响数据库操作的对象，无生命周期）
 * @Author: 阿涛
 **/
@Getter
@AllArgsConstructor
public enum OrderStateVO {

    completed("completed", "完成");

    private final String code;
    private final String desc;

}
