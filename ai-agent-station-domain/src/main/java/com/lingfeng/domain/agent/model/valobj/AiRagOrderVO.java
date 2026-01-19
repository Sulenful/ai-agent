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
public class AiRagOrderVO {

    /**
     * 知识库名称
     */
    private String ragName;

    /**
     * 知识标签
     */
    private String knowledgeTag;

}
