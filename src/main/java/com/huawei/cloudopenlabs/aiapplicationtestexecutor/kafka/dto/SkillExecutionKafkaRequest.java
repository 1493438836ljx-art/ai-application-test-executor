package com.huawei.cloudopenlabs.aiapplicationtestexecutor.kafka.dto;

import com.huawei.cloudopenlabs.aiapplicationtestexecutor.dto.SkillExecutionRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Skill执行Kafka请求消息
 *
 * @author GNEEC LIVE
 * @version 27.0.1.1
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SkillExecutionKafkaRequest {

    /** 请求ID（UUID），用于关联请求和响应 */
    private String requestId;

    /** 工作流执行ID */
    private String executionId;

    /** 工作流ID */
    private String workflowId;

    /** 节点UUID */
    private String nodeUuid;

    /** 响应topic名称 */
    private String callbackTopic;

    /** 请求时间戳 */
    private long timestamp;

    /** Skill执行请求 */
    private SkillExecutionRequest request;
}
