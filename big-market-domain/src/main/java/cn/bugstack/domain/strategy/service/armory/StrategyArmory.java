package cn.bugstack.domain.strategy.service.armory;

import cn.bugstack.domain.strategy.model.entity.StrategyAwardEntity;
import cn.bugstack.domain.strategy.repository.IStrategyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @DateTime: 2024/8/14
 * @Description: TODO(一句话描述此类的作用)
 * @Author: 阿涛
 **/
@Service
@Slf4j
public class StrategyArmory implements IStrategyArmory{
    @Resource
    private IStrategyRepository strategyRepository;

    @Override
    public void assembleLotteryStrategy(Long strategyId) {
        // 1. 查询策略配置
        List<StrategyAwardEntity> strategyAwardEntities = strategyRepository.queryStrategyAwardList(strategyId);

        // 2. 获取最小概率值
        BigDecimal minAwardRate = strategyAwardEntities.stream()
                .map(StrategyAwardEntity::getAwardRate)
                .min(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);

        // 3. 获取所有概率值
        BigDecimal totalAwardRate = strategyAwardEntities.stream()
                .map(StrategyAwardEntity::getAwardRate)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // 4. 计算概率范围
        BigDecimal rateRange = totalAwardRate.divide(minAwardRate, 0, RoundingMode.CEILING);

        // 5. 填充
        ArrayList<Integer> strategyAwardsTable = new ArrayList<>(rateRange.intValue());
        for (StrategyAwardEntity strategyAwardEntity : strategyAwardEntities) {
            Integer awardId = strategyAwardEntity.getAwardId();
            BigDecimal awardRate = strategyAwardEntity.getAwardRate();

            // 根据概率计算产品数量
            for (int i = 0; i < rateRange.multiply(awardRate).setScale(0, RoundingMode.CEILING).intValue(); i++) {
                strategyAwardsTable.add(awardId);
            }
        }

        // 6. 乱序
        Collections.shuffle(strategyAwardsTable);

        // 7. 填充到Map中，方便查询
        HashMap<Integer,Integer> shuffleStrategyAwardTable = new HashMap<>();
        for (int i = 0; i < strategyAwardsTable.size(); i++) {
            shuffleStrategyAwardTable.put(i, strategyAwardsTable.get(i));
        }

        // 8. 存储到redis
        strategyRepository.storeStrategyAwardRateTable(strategyId, shuffleStrategyAwardTable.size(), shuffleStrategyAwardTable);


    }

    @Override
    public Integer getRandomAwardId(Long strategyId) {
        int rateRange = strategyRepository.getRateRange(strategyId);
        return strategyRepository.getStrategyAwardAssemble(strategyId, new SecureRandom().nextInt(rateRange));
    }
}
