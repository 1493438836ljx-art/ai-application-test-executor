/*
* Copyright(c) Huawei Technologies Co., Ltd. 2026-2026. All rights reserved.
*/

package com.huawei.cloudopenlabs.aiapplicationtestexecutor.kafka.dto;

import com.huawei.cloudopenlabs.aiapplicationtestexecutor.dto.SkillExecutionRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Skill execution Kafka request message
 *
 * @author GNEEC LIVE
 * @version 27.0.1.1
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SkillExecutionKafkaRequest {

    /** Request ID (UUID), used to correlate request and response */
    private String requestId;

    /** Workflow execution ID */
    private String executionId;

    /** Workflow ID */
    private String workflowId;

    /** Node UUID */
    private String nodeUuid;

    /** Response topic name */
    private String callbackTopic;

    /** Request timestamp */
    private long timestamp;

    /** Skill execution request */
    private SkillExecutionRequest request;
}
