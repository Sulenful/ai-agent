package com.lingfeng.domain.agent.model.entity;

import com.lingfeng.domain.agent.model.valobj.enums.AiExecuteSubType;
import com.lingfeng.domain.agent.model.valobj.enums.AiExecuteType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: LingFeng Su
 * @version: V1.0
 * @date: 2026/1/20 11:42
 * @description:
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AutoAgentExecuteResultEntity {
    /**
     * 主类型
     * @see AiExecuteType
     */
    private String type;

    /**
     * 子类型标识，用于前端细粒度展示
     * @see AiExecuteSubType
     */
    private String subType;

    /**
     * 当前步骤
     */
    private Integer step;

    /**
     * 数据内容
     */
    private String content;

    /**
     * 是否完成
     */
    private Boolean completed;

    /**
     * 时间戳
     */
    private Long timestamp;

    /**
     * 会话ID
     */
    private String sessionId;


    /**
     * 创建分析阶段细分结果
     */
    public static AutoAgentExecuteResultEntity createAnalysisSubResult(Integer step, String subType, String content, String sessionId) {
        return AutoAgentExecuteResultEntity.builder()
                .type("analysis")
                .subType(subType)
                .step(step)
                .content(content)
                .completed(false)
                .timestamp(System.currentTimeMillis())
                .sessionId(sessionId)
                .build();
    }

    /**
     * 创建执行阶段细分结果
     */
    public static AutoAgentExecuteResultEntity createExecutionSubResult(Integer step, String subType, String content, String sessionId) {
        return AutoAgentExecuteResultEntity.builder()
                .type("execution")
                .subType(subType)
                .step(step)
                .content(content)
                .completed(false)
                .timestamp(System.currentTimeMillis())
                .sessionId(sessionId)
                .build();
    }

    /**
     * 创建监督阶段结果
     */
    public static AutoAgentExecuteResultEntity createSupervisionResult(Integer step, String content, String sessionId) {
        return AutoAgentExecuteResultEntity.builder()
                .type("supervision")
                .step(step)
                .content(content)
                .completed(false)
                .timestamp(System.currentTimeMillis())
                .sessionId(sessionId)
                .build();
    }

    /**
     * 创建监督阶段细分结果
     */
    public static AutoAgentExecuteResultEntity createSupervisionSubResult(Integer step, String subType, String content, String sessionId) {
        return AutoAgentExecuteResultEntity.builder()
                .type("supervision")
                .subType(subType)
                .step(step)
                .content(content)
                .completed(false)
                .timestamp(System.currentTimeMillis())
                .sessionId(sessionId)
                .build();
    }

    /**
     * 创建总结阶段细分的结果
     */
    public static AutoAgentExecuteResultEntity createSummarySubResult(String subType, String content, String sessionId) {
        return AutoAgentExecuteResultEntity.builder()
                .type("summary")
                .subType(subType)
                .step(4)
                .content(content)
                .completed(false)
                .timestamp(System.currentTimeMillis())
                .sessionId(sessionId)
                .build();
    }

    /**
     * 创建总结阶段结果
     */
    public static AutoAgentExecuteResultEntity createSummaryResult(String content, String sessionId) {
        return AutoAgentExecuteResultEntity.builder()
                .type("summary")
                .step(null)
                .content(content)
                .completed(true)
                .timestamp(System.currentTimeMillis())
                .sessionId(sessionId)
                .build();
    }

    /**
     * 创建完成标识
     */
    public static AutoAgentExecuteResultEntity createCompleteResult(String sessionId) {
        return AutoAgentExecuteResultEntity.builder()
                .type("complete")
                .step(null)
                .content("执行完成")
                .completed(true)
                .timestamp(System.currentTimeMillis())
                .sessionId(sessionId)
                .build();
    }



}
