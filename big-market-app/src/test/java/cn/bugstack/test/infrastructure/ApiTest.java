package cn.bugstack.test.infrastructure;

import cn.bugstack.infrastructure.persistent.dao.IAwardDao;
import cn.bugstack.infrastructure.persistent.dao.IStrategyAwardDao;
import cn.bugstack.infrastructure.persistent.dao.IStrategyDao;
import cn.bugstack.infrastructure.persistent.dao.IStrategyRule;
import cn.bugstack.infrastructure.persistent.po.Award;
import cn.bugstack.infrastructure.persistent.po.Strategy;
import cn.bugstack.infrastructure.persistent.po.StrategyAward;
import cn.bugstack.infrastructure.persistent.po.StrategyRule;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiTest {

    @Resource
    private IAwardDao awardDao;
    @Resource
    private IStrategyDao strategyDao;
    @Resource
    private IStrategyRule strategyRuleDao;
    @Resource
    private IStrategyAwardDao strategyAwardDao;

    @Test
    public void test() {
        List<Award> awards = awardDao.queryAwardList();
        List<Strategy> strategies = strategyDao.queryStrategyList();
        List<StrategyRule> strategyRules = strategyRuleDao.queryStrategyRuleList();
        List<StrategyAward> strategyAwards = strategyAwardDao.queryStrategyAwardList();
        log.info("Awards: {}, Strategies: {}, StrategyRules: {} , StrategyAwards: {}",
                awards, strategies, strategyRules, strategyAwards);
    }
}
