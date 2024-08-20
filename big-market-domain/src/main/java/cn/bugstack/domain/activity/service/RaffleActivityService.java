package cn.bugstack.domain.activity.service;

import cn.bugstack.domain.activity.repository.IActivityRepository;
import org.springframework.stereotype.Service;

/**
 * @DateTime: 2024/8/20
 * @Description: 抽奖活动服务
 * @Author: 阿涛
 **/
@Service
public class RaffleActivityService extends AbstractRaffleActivity {

    public RaffleActivityService(IActivityRepository activityRepository) {
        super(activityRepository);
    }

}
