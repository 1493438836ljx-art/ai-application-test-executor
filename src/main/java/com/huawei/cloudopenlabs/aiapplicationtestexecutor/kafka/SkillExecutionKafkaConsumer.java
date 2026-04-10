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
 * Skill执行Kafka消费者
 * 监听执行请求topic，执行后将结果发送到响应topic
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
     * 监听Skill执行请求
     */
    @KafkaListener(
            topics = "${skill-executor.kafka.request-topic}",
            containerFactory = "skillRequestKafkaListenerContainerFactory"
    )
    public void handleRequest(SkillExecutionKafkaRequest kafkaRequest) {
        String requestId = kafkaRequest.getRequestId();
        log.info("收到Skill执行请求: requestId={}, skillId={}, executionType={}",
                requestId,
                kafkaRequest.getRequest() != null ? kafkaRequest.getRequest().getSkillId() : "null",
                kafkaRequest.getRequest() != null ? kafkaRequest.getRequest().getExecutionType() : "null");

        SkillExecutionKafkaResponse response;

        try {
            // 执行Skill
            SkillExecutionResult result = handler.execute(kafkaRequest.getRequest());

            // 构建成功响应
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
            log.error("Skill执行异常: requestId={}", requestId, e);

            // 构建失败响应
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));

            response = SkillExecutionKafkaResponse.builder()
                    .requestId(requestId)
                    .executionId(kafkaRequest.getExecutionId())
                    .nodeUuid(kafkaRequest.getNodeUuid())
                    .success(false)
                    .errorMessage("Executor内部错误: " + e.getMessage())
                    .errorStack(sw.toString())
                    .executorTimestamp(System.currentTimeMillis())
                    .build();
        }

        // 发送响应到Kafka
        kafkaTemplate.send(responseTopic, requestId, response);
        log.info("已发送Skill执行响应: requestId={}, success={}", requestId, response.isSuccess());
    }
}
