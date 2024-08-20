package cn.bugstack.domain.activity.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @DateTime: 2024/8/20
 * @Description: 活动状态值对象
 * @Author: 阿涛
 **/
@Getter
@AllArgsConstructor
public enum ActivityStateVO {

    create("create", "创建");

    private final String code;
    private final String desc;

}
