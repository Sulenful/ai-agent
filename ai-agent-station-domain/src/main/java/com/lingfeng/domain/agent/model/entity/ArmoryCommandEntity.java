package com.lingfeng.domain.agent.model.entity;

import com.lingfeng.domain.agent.model.valobj.enums.AiAgentEnumVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Lingfeng Su
 * @date 2026/1/19
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArmoryCommandEntity {
    private String commandType;

    private List<String> commandIdList;

    public String getLoadDataStrategy() {
        return AiAgentEnumVO.getByCode(commandType).getLoadDataStrategy();
    }

}
