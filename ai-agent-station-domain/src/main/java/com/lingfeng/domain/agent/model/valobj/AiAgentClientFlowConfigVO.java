package com.lingfeng.domain.agent.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Lingfeng Su
 * @date 2026/1/19
 * @description
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AiAgentClientFlowConfigVO {
    /**
     * 客户端ID
     */
    private String clientId;

    /**
     * 客户端名称
     */
    private String clientName;

    /**
     * 客户端枚举
     */
    private String clientType;

    /**
     * 序列号(执行顺序)
     */
    private Integer sequence;

    /**
     * 执行步骤提示词
     */
    private String stepPrompt;
}
