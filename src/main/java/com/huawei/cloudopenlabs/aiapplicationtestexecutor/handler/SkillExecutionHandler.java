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
 * Skill执行处理器
 * 接收Kafka请求，分发到对应的执行策略
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
     * 执行Skill
     *
     * @param request 执行请求
     * @return 执行结果
     */
    public SkillExecutionResult execute(SkillExecutionRequest request) {
        String executionType = request.getExecutionType();
        log.info("处理Skill执行请求: skillId={}, executionType={}", request.getSkillId(), executionType);

        SkillExecutionStrategy strategy = strategyRegistry.getStrategy(executionType);
        if (strategy == null) {
            log.error("未找到执行策略: executionType={}", executionType);
            return SkillExecutionResult.failure("未找到执行策略: " + executionType);
        }

        log.info("使用执行策略: {}", strategy.getStrategyName());
        return strategy.execute(request);
    }
}
