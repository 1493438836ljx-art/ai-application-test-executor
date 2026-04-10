/*
* Copyright(c) Huawei Technologies Co., Ltd. 2026-2026. All rights reserved.
*/

package com.huawei.cloudopenlabs.aiapplicationtestexecutor.strategy;

import com.huawei.cloudopenlabs.aiapplicationtestexecutor.dto.SkillExecutionRequest;
import com.huawei.cloudopenlabs.aiapplicationtestexecutor.dto.SkillExecutionResult;

/**
 * Skill执行策略接口
 *
 * @author GNEEC LIVE
 * @version 27.0.1.1
 */
public interface SkillExecutionStrategy {

    /**
     * 获取执行类型
     * @return "AUTOMATED" / "AI"
     */
    String getExecutionType();

    /**
     * 执行Skill
     *
     * @param request 执行请求
     * @return 执行结果
     */
    SkillExecutionResult execute(SkillExecutionRequest request);

    /**
     * 获取策略名称（用于日志）
     */
    default String getStrategyName() {
        return "默认执行策略";
    }
}
