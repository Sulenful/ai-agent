package com.lingfeng.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Lingfeng Su
 * @date 2026/1/20
 * @description
 */
@Data
@ConfigurationProperties(prefix = "spring.ai.agent.auto-config")
public class AiAgentAutoConfigProperties {
    /**
     * 是否启用AI Agent Client自动装配
     */
    private boolean enabled = false;
}
