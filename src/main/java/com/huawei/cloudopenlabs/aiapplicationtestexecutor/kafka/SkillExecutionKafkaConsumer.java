/*
* Copyright(c) Huawei Technologies Co., Ltd. 2026-2026. All rights reserved.
*/

package com.huawei.cloudopenlabs.aiapplicationtestexecutor.kafka;

import com.huawei.cloudopenlabs.aiapplicationtestexecutor.dto.SkillExecutionResult;
import com.huawei.cloudopenlabs.aiapplicationtestexecutor.handler.SkillExecutionHandler;
import com.huawei.cloudopenlabs.aiapplicationtestexecutor.kafka.dto.SkillExecutionKafkaRequest;
import com.huawei.cloudopenlabs.aiapplicationtestexecutor.kafka.dto.SkillExecutionKafkaResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Kafka consumer for Skill execution
 * Listens to execution request topic, sends results to response topic after execution
 *
 * @author GNEEC LIVE
 * @version 27.0.1.1
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class SkillExecutionKafkaConsumer {

    private final SkillExecutionHandler handler;
    private final KafkaTemplate<String, SkillExecutionKafkaResponse> kafkaTemplate;

    @Value("${skill-executor.kafka.response-topic}")
    private String responseTopic;

    /**
     * Listen for Skill execution requests
     */
    @KafkaListener(
            topics = "${skill-executor.kafka.request-topic}",
            containerFactory = "skillRequestKafkaListenerContainerFactory"
    )
    public void handleRequest(SkillExecutionKafkaRequest kafkaRequest) {
        String requestId = kafkaRequest.getRequestId();
        log.info("Received Skill execution request: requestId={}, skillId={}, executionType={}",
                requestId,
                kafkaRequest.getRequest() != null ? kafkaRequest.getRequest().getSkillId() : "null",
                kafkaRequest.getRequest() != null ? kafkaRequest.getRequest().getExecutionType() : "null");

        SkillExecutionKafkaResponse response;

        try {
            // Execute Skill
            SkillExecutionResult result = handler.execute(kafkaRequest.getRequest());

            // Build success response
            response = SkillExecutionKafkaResponse.builder()
                    .requestId(requestId)
                    .executionId(kafkaRequest.getExecutionId())
                    .nodeUuid(kafkaRequest.getNodeUuid())
                    .success(result.isSuccess())
                    .outputs(result.getOutputs())
                    .logs(result.getLogs())
                    .durationMs(result.getDurationMs())
                    .errorMessage(result.getErrorMessage())
                    .errorStack(result.getErrorStack())
                    .executorTimestamp(System.currentTimeMillis())
                    .build();

        } catch (Exception e) {
            log.error("Skill execution exception: requestId={}", requestId, e);

            // Build failure response
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));

            response = SkillExecutionKafkaResponse.builder()
                    .requestId(requestId)
                    .executionId(kafkaRequest.getExecutionId())
                    .nodeUuid(kafkaRequest.getNodeUuid())
                    .success(false)
                    .errorMessage("Executor internal error: " + e.getMessage())
                    .errorStack(sw.toString())
                    .executorTimestamp(System.currentTimeMillis())
                    .build();
        }

        // Send response to Kafka
        kafkaTemplate.send(responseTopic, requestId, response);
        log.info("Sent Skill execution response: requestId={}, success={}", requestId, response.isSuccess());
    }
}
