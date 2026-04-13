/*
* Copyright(c) Huawei Technologies Co., Ltd. 2026-2026. All rights reserved.
*/

package com.huawei.cloudopenlabs.aiapplicationtestexecutor.strategy;

import com.huawei.cloudopenlabs.aiapplicationtestexecutor.dto.SkillExecutionRequest;
import com.huawei.cloudopenlabs.aiapplicationtestexecutor.dto.SkillExecutionResult;

/**
 * Skill execution strategy interface
 *
 * @author GNEEC LIVE
 * @version 27.0.1.1
 */
public interface SkillExecutionStrategy {

    /**
     * Get execution type
     * @return "AUTOMATED" / "AI"
     */
    String getExecutionType();

    /**
     * Execute Skill
     *
     * @param request execution request
     * @return execution result
     */
    SkillExecutionResult execute(SkillExecutionRequest request);

    /**
     * Get strategy name (for logging)
     */
    default String getStrategyName() {
        return "Default execution strategy";
    }
}
