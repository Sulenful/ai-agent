package com.lingfeng.domain.agent.service.armory.node;

import cn.bugstack.wrench.design.framework.tree.StrategyHandler;
import com.alibaba.fastjson.JSON;
import com.lingfeng.domain.agent.model.entity.ArmoryCommandEntity;
import com.lingfeng.domain.agent.model.valobj.AiClientAdvisorVO;
import com.lingfeng.domain.agent.model.valobj.enums.AiAgentEnumVO;
import com.lingfeng.domain.agent.model.valobj.enums.AiClientAdvisorTypeEnumVO;
import com.lingfeng.domain.agent.service.armory.node.factory.DefaultArmoryStrategyFactory;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.advisor.api.Advisor;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Lingfeng Su
 * @date 2026/1/19
 * @description
 */
@Service
@Slf4j(topic = "AiClientAdvisorNode")
public class AiClientAdvisorNode extends AbstractArmorySupport {

    @Resource
    private AiClientNode aiClientNode;

    @Resource
    private VectorStore vectorStore;

    @Override
    protected String doApply(ArmoryCommandEntity requestParameter, DefaultArmoryStrategyFactory.DynamicContext dynamicContext) throws Exception {
        log.info("【AiClientAdvisorNode】Ai Agent 构建节点，Advisor 顾问角色{}", JSON.toJSONString(requestParameter));
        List<AiClientAdvisorVO> aiClientAdvisorList = dynamicContext.getValue(dataName());
        if (aiClientAdvisorList == null || aiClientAdvisorList.isEmpty()) {
            log.warn("没有需要被初始化的 ai client advisor");
            return router(requestParameter, dynamicContext);
        }

        for (AiClientAdvisorVO aiClientAdvisorVO : aiClientAdvisorList) {
            // 构建顾问访问对象
            Advisor advisor = createAdvisor(aiClientAdvisorVO);
            // 注册Bean对象
            registerBean(beanName(aiClientAdvisorVO.getAdvisorId()), Advisor.class, advisor);
        }

        return router(requestParameter, dynamicContext);
    }

    private Advisor createAdvisor(AiClientAdvisorVO aiClientAdvisorVO) {
        String advisorType = aiClientAdvisorVO.getAdvisorType();
        AiClientAdvisorTypeEnumVO advisorTypeEnum = AiClientAdvisorTypeEnumVO.getByCode(advisorType);
        return advisorTypeEnum.createAdvisor(aiClientAdvisorVO, vectorStore);
    }

    @Override
    public StrategyHandler<ArmoryCommandEntity, DefaultArmoryStrategyFactory.DynamicContext, String> get(ArmoryCommandEntity requestParameter, DefaultArmoryStrategyFactory.DynamicContext dynamicContext) throws Exception {
        return aiClientNode;
    }

    @Override
    protected String beanName(String id) {
        return AiAgentEnumVO.AI_CLIENT_ADVISOR.getBeanName(id);
    }

    @Override
    protected String dataName() {
        return AiAgentEnumVO.AI_CLIENT_ADVISOR.getDataName();
    }
}
