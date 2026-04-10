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
 * AI代理执行策略（骨架实现）
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
        return "AI代理执行策略";
    }

    @Override
    public SkillExecutionResult execute(SkillExecutionRequest request) {
        log.info("执行AI代理: skillId={}, skillName={}",
                request.getSkillId(), request.getSkillName());

        long startTime = System.currentTimeMillis();

        try {
            // TODO: 实现真正的AI代理执行逻辑
            log.warn("AI代理执行策略尚未完全实现，返回模拟结果");

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
            log.info("AI代理执行完成(模拟): skillId={}, durationMs={}, outputs={}",
                    request.getSkillId(), durationMs, outputs);

            return SkillExecutionResult.success(outputs, "AI代理执行完成(模拟)", durationMs);

        } catch (Exception e) {
            log.error("AI代理执行异常: skillId={}", request.getSkillId(), e);
            return SkillExecutionResult.failure("AI代理执行失败: " + e.getMessage());
        }
    }
}
