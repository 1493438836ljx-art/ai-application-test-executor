package com.huawei.cloudopenlabs.aiapplicationtestexecutor.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * Skill执行请求
 * 包含执行所需的所有信息，不依赖数据库连接
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

    /** 执行类型: AUTOMATED / AI */
    private String executionType;

    /** 执行位置: SERVICE / CLIENT */
    private String executionLocation;

    /** 超时时间（毫秒） */
    private Long timeoutMs;

    /** 执行套件文件路径 */
    private String suitePath;

    /** 执行套件文件名 */
    private String suiteFilename;

    /** 执行套件内容（ZIP格式的字节数组，通过Kafka传输时自动base64编解码） */
    private byte[] suiteContent;

    /** 入口脚本文件名 */
    private String entryScript;

    /** 输入参数定义 */
    private List<SkillParameterDef> inputParameters;

    /** 输出参数定义 */
    private List<SkillParameterDef> outputParameters;

    /** 实际输入参数值 */
    private Map<String, Object> inputs;

    /** 执行上下文信息 */
    private Map<String, String> context;

    /**
     * Skill参数定义
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
