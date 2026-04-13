/*
* Copyright(c) Huawei Technologies Co., Ltd. 2026-2026. All rights reserved.
*/

package com.huawei.cloudopenlabs.aiapplicationtestexecutor.strategy;

import com.huawei.cloudopenlabs.aiapplicationtestexecutor.dto.SkillExecutionRequest;
import com.huawei.cloudopenlabs.aiapplicationtestexecutor.dto.SkillExecutionResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * AI Agent execution strategy (skeleton implementation)
 *
 * @author GNEEC LIVE
 * @version 27.0.1.1
 */
@Slf4j
@Component
public class AIAgentSkillExecutionStrategy implements SkillExecutionStrategy {

    @Override
    public String getExecutionType() {
        return "AI";
    }

    @Override
    public String getStrategyName() {
        return "AI Agent execution strategy";
    }

    @Override
    public SkillExecutionResult execute(SkillExecutionRequest request) {
        log.info("Executing AI Agent: skillId={}, skillName={}",
                request.getSkillId(), request.getSkillName());

        long startTime = System.currentTimeMillis();

        try {
            // TODO: Implement actual AI Agent execution logic
            log.warn("AI Agent execution strategy not fully implemented, returning simulated result");

            Map<String, Object> outputs = new HashMap<>();
            if (request.getInputParameters() != null) {
                Number sum = null;
                for (SkillExecutionRequest.SkillParameterDef param : request.getInputParameters()) {
                    if (param.getValue() instanceof Number) {
                        if (sum == null) {
                            sum = ((Number) param.getValue()).doubleValue();
                        } else {
                            sum = sum.doubleValue() + ((Number) param.getValue()).doubleValue();
                        }
                    }
                }
                if (sum != null && request.getOutputParameters() != null && !request.getOutputParameters().isEmpty()) {
                    SkillExecutionRequest.SkillParameterDef outputParam = request.getOutputParameters().get(0);
                    if (outputParam != null) {
                        outputs.put(outputParam.getName(), sum.intValue());
                    }
                }
            }

            long durationMs = System.currentTimeMillis() - startTime;
            log.info("AI Agent execution completed (simulated): skillId={}, durationMs={}, outputs={}",
                    request.getSkillId(), durationMs, outputs);

            return SkillExecutionResult.success(outputs, "AI Agent execution completed (simulated)", durationMs);

        } catch (Exception e) {
            log.error("AI Agent execution exception: skillId={}", request.getSkillId(), e);
            return SkillExecutionResult.failure("AI Agent execution failed: " + e.getMessage());
        }
    }
}
