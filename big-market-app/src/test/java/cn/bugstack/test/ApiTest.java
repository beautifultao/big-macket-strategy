package cn.bugstack.test;

import cn.bugstack.infrastructure.persistent.dao.IStrategyRuleDao;
import cn.bugstack.infrastructure.persistent.po.StrategyRule;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiTest {

    @Resource
    private IStrategyRuleDao strategyRuleDao;
    @Test
    public void test() {

        Long strategyId = 100001L;
        String ruleModel = "rule_weight";
        StrategyRule strategyRule = strategyRuleDao.queryStrategyRule(strategyId, ruleModel);
        log.info(String.valueOf(strategyRule));
    }

}
