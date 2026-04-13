/*
* Copyright(c) Huawei Technologies Co., Ltd. 2026-2026. All rights reserved.
*/

package com.huawei.cloudopenlabs.aiapplicationtestexecutor.strategy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Skill execution strategy registry
 *
 * @author GNEEC LIVE
 * @version 27.0.1.1
 */
@Slf4j
@Component
public class SkillExecutionStrategyRegistry {

    private final Map<String, SkillExecutionStrategy> strategyMap = new HashMap<>();

    public SkillExecutionStrategyRegistry(List<SkillExecutionStrategy> strategies) {
        strategies.forEach(strategy -> {
            String type = strategy.getExecutionType();
            if (strategyMap.containsKey(type)) {
                log.warn("Overwriting existing Skill execution strategy: type={}, oldStrategy={}, newStrategy={}",
                        type,
                        strategyMap.get(type).getStrategyName(),
                        strategy.getStrategyName());
            }
            strategyMap.put(type, strategy);
            log.info("Registered Skill execution strategy: type={}, strategy={}",
                    type, strategy.getStrategyName());
        });

        if (strategyMap.isEmpty()) {
            log.warn("No Skill execution strategies registered");
        }
    }

    /**
     * Get execution strategy, defaults to AUTOMATED strategy
     */
    public SkillExecutionStrategy getStrategy(String executionType) {
        SkillExecutionStrategy strategy = strategyMap.get(executionType);
        if (strategy == null) {
            log.warn("No strategy found for execution type {}, falling back to default AUTOMATED strategy", executionType);
            return strategyMap.get("AUTOMATED");
        }
        return strategy;
    }

    public Set<String> getRegisteredTypes() {
        return strategyMap.keySet();
    }
}
