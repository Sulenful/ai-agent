package com.lingfeng.domain.agent.service.execute.auto.step;

import cn.bugstack.wrench.design.framework.tree.StrategyHandler;
import com.lingfeng.domain.agent.model.entity.AutoAgentExecuteResultEntity;
import com.lingfeng.domain.agent.model.entity.ExecuteCommandEntity;
import com.lingfeng.domain.agent.model.valobj.AiAgentClientFlowConfigVO;
import com.lingfeng.domain.agent.model.valobj.enums.AiClientTypeEnumVO;
import com.lingfeng.domain.agent.model.valobj.enums.AiExecuteSubType;
import com.lingfeng.domain.agent.service.execute.auto.step.factory.DefaultAutoAgentExecuteStrategyFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

/**
 * @author: LingFeng Su
 * @version: V1.0
 * @date: 2026/1/20 14:26
 * @description: 任务拆解智能体
 */
@Service
@Slf4j(topic = "Step1AnalyzerNode")
public class Step1AnalyzerNode extends AbstractExecuteSupport {
    @Override
    protected String doApply(ExecuteCommandEntity requestParameter, DefaultAutoAgentExecuteStrategyFactory.DynamicContext dynamicContext) throws Exception {
        log.info("\n🎯 ====== 【任务分析Agent】开始执行第 {} 步 ======", dynamicContext.getStep());

        // 获取配置信息
        AiAgentClientFlowConfigVO configVO = dynamicContext.getAiAgentClientFlowConfigVOMap().get(AiClientTypeEnumVO.TASK_ANALYZER_CLIENT.getCode());

        // 第一阶段：任务分析
        log.info("\n📊 阶段1: 任务状态分析");
        String analysisPrompt = String.format(configVO.getStepPrompt(),
                // 原始用户需求
                requestParameter.getMessage(),
                // 当前执行步长
                dynamicContext.getStep(),
                // 历史执行记录
                dynamicContext.getExecutionHistory().isEmpty() ? "【首次执行】" : dynamicContext.getExecutionHistory().toString(),
                // 当前任务
                dynamicContext.getCurrentTask()
        );

        // 从容器中获取对应的智能体
        ChatClient chatClient = getChatClientByClientId(configVO.getClientId());
        // 调用大模型
        String analysisResult = chatClient.prompt(analysisPrompt)
                .advisors(a -> a.param(CHAT_MEMORY_CONVERSATION_ID_KEY, requestParameter.getSessionId())
                        .param(CHAT_MEMORY_RETRIEVE_SIZE_KEY, 1024))
                .call().content();

        assert analysisResult != null;
        parseAnalysisResult(dynamicContext, analysisResult, requestParameter.getSessionId());

        // 将分析结果保存到动态上下文中，供下一步使用
        dynamicContext.setValue("analysisResult", analysisResult);

        // 检查是否已完成
        if (analysisResult.contains("任务状态: COMPLETED") ||
                analysisResult.contains("完成度评估: 100%")) {
            dynamicContext.setCompleted(true);
            log.info("✅ 任务分析显示已完成！");
            return router(requestParameter, dynamicContext);
        }
        return router(requestParameter, dynamicContext);
    }

    /**
     * 解析分析结果
     *
     * @param dynamicContext
     * @param analysisResult
     * @param sessionId
     */
    private void parseAnalysisResult(DefaultAutoAgentExecuteStrategyFactory.DynamicContext dynamicContext, String analysisResult, String sessionId) {
        int step = dynamicContext.getStep();
        log.info("\n📊 ====== 【任务分析Agent】第 {} 步分析结果 ======", step);
        // 拆分结果
        String[] lines = analysisResult.split("\n");
        // 当前章节
        String currentSection = "";
        // 当前section内容
        StringBuilder sectionContent = new StringBuilder();
        for (String line : lines) {
            line = line.trim();
            if (line.isEmpty()) {
                // 忽略空行
                continue;
            }
            // 枚举当前状态
            if (line.contains("任务状态分析")) {
                // 发送上一个section的内容
                sendAnalysisSubResult(dynamicContext, currentSection, sectionContent.toString(), sessionId);
                currentSection = AiExecuteSubType.ANALYSIS_STATUS.getSubType();
                sectionContent = new StringBuilder();
                log.info("\n🎯 任务状态分析:");
                continue;
            } else if (line.contains("执行历史评估")) {
                // 发送上一个section的内容
                sendAnalysisSubResult(dynamicContext, currentSection, sectionContent.toString(), sessionId);
                currentSection = AiExecuteSubType.ANALYSIS_HISTORY.getSubType();
                sectionContent = new StringBuilder();
                log.info("\n📈 执行历史评估:");
                continue;
            } else if (line.contains("下一步策略")) {
                // 发送上一个section的内容
                sendAnalysisSubResult(dynamicContext, currentSection, sectionContent.toString(), sessionId);
                currentSection = AiExecuteSubType.ANALYSIS_STRATEGY.getSubType();
                sectionContent = new StringBuilder();
                log.info("\n🚀 下一步策略:");
                continue;
            } else if (line.contains("完成度评估")) {
                // 发送上一个section的内容
                sendAnalysisSubResult(dynamicContext, currentSection, sectionContent.toString(), sessionId);
                currentSection = AiExecuteSubType.ANALYSIS_PROGRESS.getSubType();
                sectionContent = new StringBuilder();
                String progress = line.substring(line.indexOf(":") + 1).trim();
                log.info("\n📊 完成度评估: {}", progress);
                sectionContent.append(line).append("\n");
                continue;
            } else if (line.contains("任务状态")) {
                // 发送上一个section的内容
                sendAnalysisSubResult(dynamicContext, currentSection, sectionContent.toString(), sessionId);
                currentSection = AiExecuteSubType.ANALYSIS_TASK_STATUS.getSubType();
                sectionContent = new StringBuilder();
                String status = line.substring(line.indexOf(":") + 1).trim();
                if ("COMPLETED".equals(status)) {
                    log.info("\n✅ 任务状态: 已完成");
                } else {
                    log.info("\n🔄 任务状态: 继续执行");
                }
                sectionContent.append(line).append("\n");
                continue;
            }

            // 收集当前section的内容
            if (!currentSection.isEmpty()) {
                // 拼接当前行内容
                sectionContent.append(line).append("\n");
                AiExecuteSubType bySubType = AiExecuteSubType.getBySubType(currentSection);

                switch (bySubType) {
                    case ANALYSIS_STATUS:
                        log.info("   📋 {}", line);
                        break;
                    case ANALYSIS_HISTORY:
                        log.info("   📊 {}", line);
                        break;
                    case ANALYSIS_STRATEGY:
                        log.info("   🎯 {}", line);
                        break;
                    default:
                        log.info("   📝 {}", line);
                        break;
                }
            }
        }
        // 发送最后一个section的内容
        sendAnalysisSubResult(dynamicContext, currentSection, sectionContent.toString(), sessionId);
    }

    @Override
    public StrategyHandler<ExecuteCommandEntity, DefaultAutoAgentExecuteStrategyFactory.DynamicContext, String> get(ExecuteCommandEntity requestParameter, DefaultAutoAgentExecuteStrategyFactory.DynamicContext dynamicContext) throws Exception {
        // 如果任务已完成或达到最大步数，进入总结阶段
        if (dynamicContext.isCompleted() || dynamicContext.getStep() > dynamicContext.getMaxStep()) {
            // 路由到总结节点
            return getBean("step4LogExecutionSummaryNode");
        }

        // 否则继续执行下一步
        return getBean("step2PrecisionExecutorNode");
    }

    /**
     * 发送分析阶段细分结果到流式输出
     */
    private void sendAnalysisSubResult(DefaultAutoAgentExecuteStrategyFactory.DynamicContext dynamicContext,
                                       String subType, String content, String sessionId) {
        if (!subType.isEmpty() && !content.isEmpty()) {
            AutoAgentExecuteResultEntity result = AutoAgentExecuteResultEntity.createAnalysisSubResult(
                    dynamicContext.getStep(), subType, content, sessionId);
            sendSseResult(dynamicContext, result);
        }
    }
}
