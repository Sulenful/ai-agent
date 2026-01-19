package com.lingfeng.domain.agent.service.armory.node.factory;

import cn.bugstack.wrench.design.framework.tree.StrategyHandler;
import com.lingfeng.domain.agent.model.entity.ArmoryCommandEntity;
import com.lingfeng.domain.agent.service.armory.node.RootNode;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Lingfeng Su
 * @date 2026/1/19
 * @description
 */
@Service
@RequiredArgsConstructor
public class DefaultArmoryStrategyFactory {
    private final RootNode rootNode;

    public StrategyHandler<ArmoryCommandEntity, DefaultArmoryStrategyFactory.DynamicContext, String> armoryStrategyHandler() {
        return rootNode;
    }

    public static class DynamicContext {
        private Map<String, Object> dataObject = new HashMap<>();

        public <T> void setValue(String key, T value) {
            dataObject.put(key, value);
        }

        public <T> T getValue(String key) {
            return (T) dataObject.get(key);
        }
    }

}
