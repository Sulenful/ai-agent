package com.lingfeng.config;

import com.alibaba.fastjson.JSON;
import com.lingfeng.domain.agent.model.valobj.AiAgentVO;
import com.lingfeng.domain.agent.service.IArmoryService;
import com.lingfeng.domain.agent.service.armory.node.factory.DefaultArmoryStrategyFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Lingfeng Su
 * @date 2026/1/20
 * @description 自动装配
 */
@EnableConfigurationProperties(AiAgentAutoConfigProperties.class)
@Slf4j(topic = "AiAgentAutoConfiguration")
@ConditionalOnProperty(prefix = "spring.ai.agent.auto-config", name = "enabled", havingValue = "true")
@Configuration
public class AiAgentAutoConfiguration implements ApplicationListener<ApplicationReadyEvent> {
    @Resource
    private AiAgentAutoConfigProperties aiAgentAutoConfigProperties;

    @Resource
    private DefaultArmoryStrategyFactory defaultArmoryStrategyFactory;

    @Resource
    private IArmoryService armoryService;

    /**
     * 应用启动完成时执行
     *
     * @param event
     */
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        try {
            log.info("AI Agent 自动装配开始，配置: {}", aiAgentAutoConfigProperties);

            // 检查配置是否有效
            if (!aiAgentAutoConfigProperties.isEnabled()) {
                log.info("AI Agent 自动装配未启用");
                return;
            }

            // 加载所有智能体
            List<AiAgentVO> aiAgentVOS = armoryService.acceptArmoryAllAvailableAgents();

            log.info("AI Agent 自动装配完成 {}", JSON.toJSONString(aiAgentVOS));
        } catch (Exception e) {
            log.error("AI Agent 自动装配失败", e);
        }
    }
}
