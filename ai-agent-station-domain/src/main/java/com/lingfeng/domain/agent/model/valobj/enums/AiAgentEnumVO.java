package com.lingfeng.domain.agent.model.valobj.enums;

import com.lingfeng.domain.agent.model.valobj.AiClientApiVO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Lingfeng Su
 * @date 2026/1/19
 * @description
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum AiAgentEnumVO {
    AI_CLIENT_API("大模型API", "api", "ai_client_api_", "ai_client_api_data_list", "aiClientApiLoadDataStrategy"),
    AI_CLIENT_MODEL("对话模型", "model", "ai_client_model_", "ai_client_model_data_list", "aiClientModelLoadDataStrategy"),
    AI_CLIENT_SYSTEM_PROMPT("提示词", "prompt", "ai_client_system_prompt_", "ai_client_system_prompt_data_list", "aiClientSystemPromptLoadDataStrategy"),
    AI_CLIENT_TOOL_MCP("mcp工具", "tool_mcp", "ai_client_tool_mcp_", "ai_client_tool_mcp_data_list", "aiClientToolMCPLoadDataStrategy"),
    AI_CLIENT_ADVISOR("顾问角色", "advisor", "ai_client_advisor_", "ai_client_advisor_data_list", "aiClientAdvisorLoadDataStrategy"),
    AI_CLIENT("客户端", "client", "ai_client_", "ai_client_data_list", "aiClientLoadDataStrategy"),

    ;

    private String name;
    private String code;
    private String beanNameTag;
    private String dataName;
    /**
     * 装配数据策略，由策略bean去执行加载
     */
    private String loadDataStrategy;

    // 静态Map用于O(1)时间复杂度查找
    private static final Map<String, AiAgentEnumVO> CODE_MAP;

    static {
        CODE_MAP = Arrays.stream(AiAgentEnumVO.values())
                .collect(Collectors.toMap(AiAgentEnumVO::getCode, Function.identity()));
    }

    /**
     * 根据code获取对应的枚举 - O(1)时间复杂度
     *
     * @param code 枚举code值
     * @return 对应的枚举，如果未找到则抛出异常
     */
    public static AiAgentEnumVO getByCode(String code) {
        if (code == null) {
            return null;
        }

        AiAgentEnumVO result = CODE_MAP.get(code);
        if (result == null) {
            throw new RuntimeException("code value " + code + " not exist!");
        }
        return result;
    }

    /**
     * 获取Bean名称
     *
     * @param id 传入的参数
     * @return beanNameTag + id 拼接的Bean名称
     */
    public String getBeanName(String id) {
        return this.beanNameTag + id;
    }
}
