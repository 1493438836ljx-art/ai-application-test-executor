/*
* Copyright(c) Huawei Technologies Co., Ltd. 2026-2026. All rights reserved.
*/

package com.huawei.cloudopenlabs.aiapplicationtestexecutor.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * Skill execution request
 * Contains all information required for execution, no database dependency
 *
 * @author GNEEC LIVE
 * @version 27.0.1.1
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SkillExecutionRequest {

    private String skillId;
    private String skillName;
    private String description;

    /** Execution type: AUTOMATED / AI */
    private String executionType;

    /** Execution location: SERVICE / CLIENT */
    private String executionLocation;

    /** Timeout (milliseconds) */
    private Long timeoutMs;

    /** Execution suite file path */
    private String suitePath;

    /** Execution suite filename */
    private String suiteFilename;

    /** Execution suite content (ZIP byte array, auto base64 encoded/decoded via Kafka) */
    private byte[] suiteContent;

    /** Entry script filename */
    private String entryScript;

    /** Input parameter definitions */
    private List<SkillParameterDef> inputParameters;

    /** Output parameter definitions */
    private List<SkillParameterDef> outputParameters;

    /** Actual input parameter values */
    private Map<String, Object> inputs;

    /** Execution context information */
    private Map<String, String> context;

    /**
     * Skill parameter definition
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SkillParameterDef {
        private String name;
        private String type;
        private String description;
        private Boolean required;
        private Object defaultValue;
        private Object value;
    }
}
