package com.lingfeng.domain.agent.service.armory.node;

import cn.bugstack.wrench.design.framework.tree.StrategyHandler;
import com.alibaba.fastjson.JSON;
import com.lingfeng.domain.agent.model.entity.ArmoryCommandEntity;
import com.lingfeng.domain.agent.model.valobj.AiClientApiVO;
import com.lingfeng.domain.agent.model.valobj.enums.AiAgentEnumVO;
import com.lingfeng.domain.agent.service.armory.node.factory.DefaultArmoryStrategyFactory;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Lingfeng Su
 * @date 2026/1/19
 * @description
 */
@Slf4j(topic = "AiClientApiNode")
@Service
public class AiClientApiNode extends AbstractArmorySupport {

    @Resource
    private AiClientToolMcpNode aiClientToolMcpNode;

    @Override
    protected String doApply(ArmoryCommandEntity requestParameter, DefaultArmoryStrategyFactory.DynamicContext dynamicContext) throws Exception {
        log.info("【AiClientApiNode】Ai Agent 构建节点，API 接口请求{}", JSON.toJSONString(requestParameter));

        List<AiClientApiVO> aiClientApiList = dynamicContext.getValue(dataName());
        for (AiClientApiVO apiVO : aiClientApiList) {
            // 构建Open AI API
            OpenAiApi openAiApi = OpenAiApi.builder()
                    .baseUrl(apiVO.getBaseUrl())
                    .apiKey(apiVO.getApiKey())
                    .completionsPath(apiVO.getCompletionsPath())
                    .embeddingsPath(apiVO.getEmbeddingsPath())
                    .build();

            // 手动注册 OpenAiApi bean对象到IOC容器
            registerBean(beanName(apiVO.getApiId()), OpenAiApi.class, openAiApi);
        }
        // 路由到下一个执行节点
        return router(requestParameter, dynamicContext);
    }

    /**
     * 获取下一个执行节点
     * @param requestParameter 入参
     * @param dynamicContext   上下文
     * @return
     * @throws Exception
     */
    @Override
    public StrategyHandler<ArmoryCommandEntity, DefaultArmoryStrategyFactory.DynamicContext, String> get(ArmoryCommandEntity requestParameter, DefaultArmoryStrategyFactory.DynamicContext dynamicContext) throws Exception {
        return aiClientToolMcpNode;
    }

    /**
     *
     * @param id api id
     * @return
     */
    @Override
    protected String beanName(String id) {
        return AiAgentEnumVO.AI_CLIENT_API.getBeanName(id);
    }

    @Override
    protected String dataName() {
        return AiAgentEnumVO.AI_CLIENT_API.getDataName();
    }
}
