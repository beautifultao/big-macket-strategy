package cn.bugstack.infrastructure.persistent.dao;

import cn.bugstack.infrastructure.persistent.po.Award;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @DateTime: 2024/8/13
 * @Description: TODO(一句话描述此类的作用)
 * @Author: 阿涛
 **/
@Mapper
public interface IAwardDao {
    List<Award> queryAwardList();
}
