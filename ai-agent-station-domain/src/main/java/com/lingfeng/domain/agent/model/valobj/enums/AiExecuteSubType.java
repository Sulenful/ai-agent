package com.lingfeng.domain.agent.model.valobj.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author: LingFeng Su
 * @version: V1.0
 * @date: 2026/1/20 11:50
 * @description:
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum AiExecuteSubType {
    ANALYSIS_STATUS("analysis_status", "任务状态"),
    ANALYSIS_TASK_STATUS("analysis_task_status", "任务状态"),
    ANALYSIS_HISTORY("analysis_history", "历史评估"),
    ANALYSIS_PROGRESS("analysis_progress", "完成度"),
    ANALYSIS_STRATEGY("analysis_strategy", "下一步执行策略"),
    EXECUTION_TARGET("execution_target", "执行目标"),
    EXECUTION_PROCESS("execute_process", "执行过程"),
    EXECUTION_RESULT("execute_result", "执行结果"),
    EXECUTION_QUALITY("execution_quality", "质量检查"),
    ASSESSMENT("assessment", "质量评估"),
    ISSUES("issues", "问题识别"),
    SUGGESTIONS("suggestions", "改进建议"),
    SCORE("score", "质量评分"),
    PASS("pass", "检查结果"),
    COMPLETED_WORK("completed_work", "已完成工作"),
    INCOMPLETED_REASONS("incompleted_reasons", "未完成原因"),
    SUMMARY_OVERVIEW("summary_overview", "总结概览"),

    ;

    private static final Map<String, AiExecuteSubType> CACHE;

    static {
        CACHE = Arrays.stream(AiExecuteSubType.values())
                .collect(Collectors.toMap(AiExecuteSubType::getSubType, Function.identity()));
    }

    public static AiExecuteSubType getBySubType(String subType) {

        AiExecuteSubType aiExecuteSubType = CACHE.get(subType);
        if (Objects.isNull(aiExecuteSubType)) {
            throw new RuntimeException("未定义的子类型：" + subType);
        }
        return aiExecuteSubType;
    }

    private String subType;
    private String desc;
}
