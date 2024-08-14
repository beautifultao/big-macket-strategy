package cn.bugstack.infrastructure.persistent.po;

import lombok.Data;

import java.util.Date;

/**
 * @DateTime: 2024/8/13
 * @Description: 奖品表
 * @Author: 阿涛
 **/
@Data
public class Award {
    private Long id;
    private Long awardId;
    private String awardKey;
    private String awardConfig;
    private String awardDesc;
    private Date createTime;
    private Date updateTime;
}
