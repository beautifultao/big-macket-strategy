package cn.bugstack.domain.award.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @DateTime: 2024/8/22
 * @Description: 任务状态值对象
 * @Author: 阿涛
 **/
@Getter
@AllArgsConstructor
public enum TaskStateVO {

    create("create", "创建"),
    complete("complete", "发送完成"),
    fail("fail", "发送失败"),
    ;

    private final String code;
    private final String desc;

}
