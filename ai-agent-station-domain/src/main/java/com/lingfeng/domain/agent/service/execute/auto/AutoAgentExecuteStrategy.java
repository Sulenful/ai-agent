package com.lingfeng.domain.agent.service.execute.auto;

import cn.bugstack.wrench.design.framework.tree.StrategyHandler;
import com.alibaba.fastjson.JSON;
import com.lingfeng.domain.agent.model.entity.AutoAgentExecuteResultEntity;
import com.lingfeng.domain.agent.model.entity.ExecuteCommandEntity;
import com.lingfeng.domain.agent.service.IExecuteStrategy;
import com.lingfeng.domain.agent.service.execute.auto.step.factory.DefaultAutoAgentExecuteStrategyFactory;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

/**
 * @author: LingFeng Su
 * @version: V1.0
 * @date: 2026/1/20 14:19
 * @description:
 */
@Service("autoAgentExecuteStrategy")
@Slf4j(topic = "AutoAgentExecuteStrategy")
public class AutoAgentExecuteStrategy implements IExecuteStrategy {

    @Resource
    private DefaultAutoAgentExecuteStrategyFactory defaultAutoAgentExecuteStrategyFactory;

    @Override
    public void execute(ExecuteCommandEntity requestParameter, ResponseBodyEmitter emitter) throws Exception {
        StrategyHandler<ExecuteCommandEntity, DefaultAutoAgentExecuteStrategyFactory.DynamicContext, String> executeHandler
                = defaultAutoAgentExecuteStrategyFactory.executeStrategyHandler();

        // 创建动态上下文并初始化必要字段
        DefaultAutoAgentExecuteStrategyFactory.DynamicContext dynamicContext = new DefaultAutoAgentExecuteStrategyFactory.DynamicContext();
        dynamicContext.setMaxStep(requestParameter.getMaxStep() != null ? requestParameter.getMaxStep() : 3);
        dynamicContext.setExecutionHistory(new StringBuilder());
        dynamicContext.setCurrentTask(requestParameter.getMessage());
        // 设置流式输出
        dynamicContext.setValue("emitter", emitter);

        // 执行agent策略（apply为模版方法）
        String apply = executeHandler.apply(requestParameter, dynamicContext);
        log.info("测试结果:{}", apply);

        // 收尾：发送完成标识
        try {
            AutoAgentExecuteResultEntity completeResult = AutoAgentExecuteResultEntity.createCompleteResult(requestParameter.getSessionId());
            // 发送SSE格式的数据
            String sseData = "data: " + JSON.toJSONString(completeResult) + "\n\n";
            emitter.send(sseData);
        } catch (Exception e) {
            log.error("发送完成标识失败：{}", e.getMessage(), e);
        }


    }
}
