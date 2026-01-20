package com.lingfeng.domain.agent.model.valobj.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author: LingFeng Su
 * @version: V1.0
 * @date: 2026/1/20 11:46
 * @description:
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum AiExecuteType {

    ANALYSIS("analysis", "分析阶段"),
    EXECUTE("execute", "执行阶段"),
    SUPERVISION("supervision", "监督阶段"),
    SUMMARY("summary", "总结阶段"),
    ERROR("error", "错误阶段"),
    COMPLETE("complete", "完成"),
    ;


    private String type;
    private String desc;
}
