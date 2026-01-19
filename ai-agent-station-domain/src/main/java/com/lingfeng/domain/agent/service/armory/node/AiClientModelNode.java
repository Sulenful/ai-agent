package com.lingfeng.domain.agent.service.armory.node;

import cn.bugstack.wrench.design.framework.tree.StrategyHandler;
import com.alibaba.fastjson.JSON;
import com.lingfeng.domain.agent.model.entity.ArmoryCommandEntity;
import com.lingfeng.domain.agent.model.valobj.AiClientModelVO;
import com.lingfeng.domain.agent.model.valobj.enums.AiAgentEnumVO;
import com.lingfeng.domain.agent.service.armory.node.factory.DefaultArmoryStrategyFactory;
import io.modelcontextprotocol.client.McpSyncClient;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.springframework.ai.mcp.SyncMcpToolCallbackProvider;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Lingfeng Su
 * @date 2026/1/19
 * @description
 */
@Service
@Slf4j(topic = "AiClientModelNode")
public class AiClientModelNode extends AbstractArmorySupport{

    @Resource
    private AiClientAdvisorNode aiClientAdvisorNode;

    @Override
    protected String doApply(ArmoryCommandEntity requestParameter, DefaultArmoryStrategyFactory.DynamicContext dynamicContext) throws Exception {
        log.info("【AiClientModelNode】Ai Agent 构建节点，Mode 对话模型: {}", JSON.toJSONString(requestParameter));
        List<AiClientModelVO> aiClientModelList = dynamicContext.getValue(dataName());

        if (aiClientModelList == null || aiClientModelList.isEmpty()) {
            log.warn("没有需要被初始化的 ai client model");
            // 路由到下一个节点
            return router(requestParameter, dynamicContext);
        }

        for (AiClientModelVO modelVO : aiClientModelList) {
            // 获取当前模型open ai api bean对象
            OpenAiApi openAiApi = getBean(AiAgentEnumVO.AI_CLIENT_API.getBeanName(modelVO.getApiId()));
            if (null == openAiApi) {
                throw new RuntimeException("当前模型无对应的 api 配置");
            }

            // 获取当前模型关联的MCP Tool
            List<McpSyncClient> mcpSyncClients = Lists.newArrayList();
            for (String toolMcpId : modelVO.getToolMcpIds()) {
                McpSyncClient mcpSyncClient = getBean(AiAgentEnumVO.AI_CLIENT_TOOL_MCP.getBeanName(toolMcpId));
                mcpSyncClients.add(mcpSyncClient);
            }

            // 实例化对话模型
            OpenAiChatModel openAiChatModel = OpenAiChatModel.builder()
                    .openAiApi(openAiApi)
                    .defaultOptions(
                            OpenAiChatOptions.builder()
                                    .model(modelVO.getModelName())
                                    .toolCallbacks(new SyncMcpToolCallbackProvider(mcpSyncClients).getToolCallbacks())
                                    .build()
                    )
                    .build();
            // 注册对话模型
            registerBean(beanName(modelVO.getModelId()), OpenAiChatModel.class, openAiChatModel);
        }
        // 路由到下一个节点
        return router(requestParameter, dynamicContext);
    }

    @Override
    protected String beanName(String id) {
        return AiAgentEnumVO.AI_CLIENT_MODEL.getBeanName(id);
    }

    @Override
    protected String dataName() {
        return AiAgentEnumVO.AI_CLIENT_MODEL.getDataName();
    }

    @Override
    public StrategyHandler<ArmoryCommandEntity, DefaultArmoryStrategyFactory.DynamicContext, String> get(ArmoryCommandEntity requestParameter, DefaultArmoryStrategyFactory.DynamicContext dynamicContext) throws Exception {
        return aiClientAdvisorNode;
    }
}
