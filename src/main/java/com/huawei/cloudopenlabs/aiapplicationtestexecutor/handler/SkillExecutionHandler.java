/*
* Copyright(c) Huawei Technologies Co., Ltd. 2026-2026. All rights reserved.
*/

package com.huawei.cloudopenlabs.aiapplicationtestexecutor.handler;

import com.huawei.cloudopenlabs.aiapplicationtestexecutor.dto.SkillExecutionRequest;
import com.huawei.cloudopenlabs.aiapplicationtestexecutor.dto.SkillExecutionResult;
import com.huawei.cloudopenlabs.aiapplicationtestexecutor.strategy.SkillExecutionStrategy;
import com.huawei.cloudopenlabs.aiapplicationtestexecutor.strategy.SkillExecutionStrategyRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Skill execution handler
 * Receives Kafka requests, dispatches to corresponding execution strategy
 *
 * @author GNEEC LIVE
 * @version 27.0.1.1
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class SkillExecutionHandler {

    private final SkillExecutionStrategyRegistry strategyRegistry;

    /**
     * Execute Skill
     *
     * @param request execution request
     * @return execution result
     */
    public SkillExecutionResult execute(SkillExecutionRequest request) {
        String executionType = request.getExecutionType();
        log.info("Processing Skill execution request: skillId={}, executionType={}", request.getSkillId(), executionType);

        SkillExecutionStrategy strategy = strategyRegistry.getStrategy(executionType);
        if (strategy == null) {
            log.error("No execution strategy found: executionType={}", executionType);
            return SkillExecutionResult.failure("No execution strategy found: " + executionType);
        }

        log.info("Using execution strategy: {}", strategy.getStrategyName());
        return strategy.execute(request);
    }
}
