package com.lingfeng.domain.agent.service.armory.business.data;

import com.lingfeng.domain.agent.model.entity.ArmoryCommandEntity;
import com.lingfeng.domain.agent.service.armory.node.factory.DefaultArmoryStrategyFactory;

/**
 * 加载数据策略
 *
 */
public interface ILoadDataStrategy {
    /**
     * 加载数据
     *
     * @param armoryCommandEntity
     * @param dynamicContext
     */
    void loadData(ArmoryCommandEntity armoryCommandEntity, DefaultArmoryStrategyFactory.DynamicContext dynamicContext);
}
