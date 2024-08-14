package cn.bugstack.infrastructure.persistent.po;

import lombok.Data;

import java.util.Date;

/**
 * @DateTime: 2024/8/12
 * @Description: 策略持久层
 * @Author: 阿涛
 **/
@Data
public class Strategy {
    private Long id;
    // 自增ID
    private Long strategyId;
    // 抽奖策略ID
    private String strategyDesc;
    // 抽奖策略描述
    private String ruleModels;
    // 规则模型，rule配置的模型同步到此表，便于使用
    private Date createTime;
    // 创建时间
    private Date updateTime;
}
