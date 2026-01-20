package com.lingfeng.domain.agent.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: LingFeng Su
 * @version: V1.0
 * @date: 2026/1/20 11:40
 * @description: 执行命令实体
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExecuteCommandEntity {
    /**
     * 智能体id
     */
    private String aiAgentId;

    /**
     * 用户输入
     */
    private String message;

    /**
     * 会话id
     */
    private String sessionId;

    /**
     * 最大步数
     */
    private Integer maxStep;
}
