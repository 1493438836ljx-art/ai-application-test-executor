package com.huawei.cloudopenlabs.aiapplicationtestexecutor.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * Skill执行结果
 * 包含执行完成后的所有信息
 *
 * @author GNEEC LIVE
 * @version 27.0.1.1
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SkillExecutionResult {

    private boolean success;
    private Map<String, Object> outputs;
    private String logs;
    private Long durationMs;
    private String errorMessage;
    private String errorStack;

    public static SkillExecutionResult success(Map<String, Object> outputs) {
        return SkillExecutionResult.builder()
                .success(true)
                .outputs(outputs)
                .build();
    }

    public static SkillExecutionResult success(Map<String, Object> outputs, String logs) {
        return SkillExecutionResult.builder()
                .success(true)
                .outputs(outputs)
                .logs(logs)
                .build();
    }

    public static SkillExecutionResult success(Map<String, Object> outputs, String logs, Long durationMs) {
        return SkillExecutionResult.builder()
                .success(true)
                .outputs(outputs)
                .logs(logs)
                .durationMs(durationMs)
                .build();
    }

    public static SkillExecutionResult failure(String errorMessage) {
        return SkillExecutionResult.builder()
                .success(false)
                .errorMessage(errorMessage)
                .build();
    }

    public static SkillExecutionResult failure(String errorMessage, String errorStack) {
        return SkillExecutionResult.builder()
                .success(false)
                .errorMessage(errorMessage)
                .errorStack(errorStack)
                .build();
    }
}
