package com.lingfeng.domain.agent.service.armory.business.data.impl;

import com.lingfeng.domain.agent.adapter.repository.IAgentRepository;
import com.lingfeng.domain.agent.model.entity.ArmoryCommandEntity;
import com.lingfeng.domain.agent.model.valobj.AiClientApiVO;
import com.lingfeng.domain.agent.model.valobj.enums.AiAgentEnumVO;
import com.lingfeng.domain.agent.service.armory.business.data.ILoadDataStrategy;
import com.lingfeng.domain.agent.service.armory.node.factory.DefaultArmoryStrategyFactory;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author Lingfeng Su
 * @date 2026/1/19
 * @description api数据加载策略
 */
@Service("aiClientApiLoadDataStrategy")
@Slf4j(topic = "aiClientApiLoadDataStrategy")
public class AiClientApiLoadDataStrategy implements ILoadDataStrategy {

    @Resource
    private IAgentRepository repository;

    @Resource
    protected ThreadPoolExecutor threadPoolExecutor;

    @Override
    public void loadData(ArmoryCommandEntity armoryCommandEntity, DefaultArmoryStrategyFactory.DynamicContext dynamicContext) {
        List<String> apiIdList = armoryCommandEntity.getCommandIdList();

        // 从数据库中加载数据
        CompletableFuture<List<AiClientApiVO>> aiClientApiListFuture = CompletableFuture.supplyAsync(() -> {
            log.info("查询配置数据(ai_client_api) {}", apiIdList);
            return repository.queryAiClientApiVOListByApiIds(apiIdList);
        }, threadPoolExecutor);

        // 阻塞等待获取结果
        CompletableFuture.allOf(aiClientApiListFuture).thenRun(() ->
                        // 等待 aiClientApiListFuture 获取结果
                        dynamicContext.setValue(AiAgentEnumVO.AI_CLIENT_API.getDataName(), aiClientApiListFuture.join()))
                // 阻塞等待所有任务完成
                .join();

    }
}
