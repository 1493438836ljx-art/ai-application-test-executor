/*
* Copyright(c) Huawei Technologies Co., Ltd. 2026-2026. All rights reserved.
*/

package com.huawei.cloudopenlabs.aiapplicationtestexecutor.kafka.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * Skill execution Kafka response message
 *
 * @author GNEEC LIVE
 * @version 27.0.1.1
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SkillExecutionKafkaResponse {

    /** Request ID, corresponds to SkillExecutionKafkaRequest.requestId */
    private String requestId;

    /** Workflow execution ID */
    private String executionId;

    /** Node UUID */
    private String nodeUuid;

    /** Whether execution succeeded */
    private boolean success;

    /** Output parameters */
    private Map<String, Object> outputs;

    /** Execution logs */
    private String logs;

    /** Execution duration (milliseconds) */
    private Long durationMs;

    /** Error message */
    private String errorMessage;

    /** Error stack trace */
    private String errorStack;

    /** Executor processing timestamp */
    private long executorTimestamp;
}
