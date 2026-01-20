package com.lingfeng.domain.agent.service.execute.flow.step;

import cn.bugstack.wrench.design.framework.tree.StrategyHandler;
import com.lingfeng.domain.agent.model.entity.ExecuteCommandEntity;
import com.lingfeng.domain.agent.model.valobj.AiAgentClientFlowConfigVO;
import com.lingfeng.domain.agent.service.execute.flow.step.factory.DefaultFlowAgentExecuteStrategyFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author: LingFeng Su
 * @version: V1.0
 * @date: 2026/1/20 13:51
 * @description:
 */
@Service("flowRootNode")
@Slf4j(topic = "FlowRootNode")
public class RootNode extends AbstractExecuteSupport{
    @Override
    protected String doApply(ExecuteCommandEntity requestParameter, DefaultFlowAgentExecuteStrategyFactory.DynamicContext dynamicContext) throws Exception {
        log.info("======== 流程执行开始 ========");
        log.info("用户输入: {}", requestParameter.getMessage());
        log.info("最大执行步数: {}", requestParameter.getMaxStep());
        log.info("会话ID: {}", requestParameter.getSessionId());

        Map<String, AiAgentClientFlowConfigVO> aiAgentClientFlowConfigVOMap = repository.queryAiAgentClientFlowConfig(requestParameter.getAiAgentId());

        // 客户端对话组
        dynamicContext.setAiAgentClientFlowConfigVOMap(aiAgentClientFlowConfigVOMap);
        // 上下文信息
        dynamicContext.setExecutionHistory(new StringBuilder());
        // 当前任务信息
        dynamicContext.setCurrentTask(requestParameter.getMessage());
        // 最大任务步骤
        dynamicContext.setMaxStep(requestParameter.getMaxStep());

        return router(requestParameter, dynamicContext);

    }

    @Override
    public StrategyHandler<ExecuteCommandEntity, DefaultFlowAgentExecuteStrategyFactory.DynamicContext, String> get(ExecuteCommandEntity requestParameter, DefaultFlowAgentExecuteStrategyFactory.DynamicContext dynamicContext) throws Exception {
        return null;
    }
}
