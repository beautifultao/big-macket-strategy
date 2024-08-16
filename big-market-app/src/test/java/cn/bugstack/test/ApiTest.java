package cn.bugstack.test;

import cn.bugstack.infrastructure.persistent.dao.IStrategyAwardDao;
import cn.bugstack.infrastructure.persistent.dao.IStrategyDao;
import cn.bugstack.infrastructure.persistent.dao.IStrategyRuleDao;
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
    private IStrategyRuleDao strategyRuleDao;
    @Resource
    private IStrategyAwardDao strategyAwardDao;

    @Resource
    private IStrategyDao strategyDao;
    @Test
    public void test() {

        Long strategyId = 100001L;
        String ruleModel = "rule_weight";
        StrategyRule strategyRule = strategyRuleDao.queryStrategyRule(strategyId, ruleModel);
        log.info(String.valueOf(strategyRule));
    }

    @Test
    public void test1(){
        List<StrategyAward> strategyAwards = strategyAwardDao.queryStrategyAwardListByStrategyId(100002L);

        System.out.println(strategyAwards);
    }
}
