package com.lingfeng.domain.agent.service.armory.node;

import cn.bugstack.wrench.design.framework.tree.StrategyHandler;
import com.alibaba.fastjson.JSON;
import com.lingfeng.domain.agent.model.entity.ArmoryCommandEntity;
import com.lingfeng.domain.agent.service.armory.business.data.ILoadDataStrategy;
import com.lingfeng.domain.agent.service.armory.node.factory.DefaultArmoryStrategyFactory;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * @author Lingfeng Su
 * @date 2026/1/19
 * @description
 */
@Service
@Slf4j(topic = "RootNode")
public class RootNode extends AbstractArmorySupport {

    @Resource
    private AiClientApiNode aiClientApiNode;

    private final Map<String, ILoadDataStrategy> loadDataStrategyMap;

    @Override
    protected void multiThread(ArmoryCommandEntity requestParameter,
                               DefaultArmoryStrategyFactory.DynamicContext dynamicContext) throws ExecutionException, InterruptedException, TimeoutException {
        ILoadDataStrategy loadDataStrategy = loadDataStrategyMap.get(requestParameter.getLoadDataStrategy());
        loadDataStrategy.loadData(requestParameter, dynamicContext);
    }

    public RootNode(Map<String, ILoadDataStrategy> loadDataStrategyMap) {
        this.loadDataStrategyMap = loadDataStrategyMap;
    }


    /**
     * 加载数据
     *
     * @param requestParameter
     * @param dynamicContext
     * @return
     * @throws Exception
     */
    @Override
    protected String doApply(ArmoryCommandEntity requestParameter, DefaultArmoryStrategyFactory.DynamicContext dynamicContext) throws Exception {
        log.info("Ai Agent 构建，RootNode数据加载节点{}", JSON.toJSONString(requestParameter));
        return router(requestParameter, dynamicContext);
    }

    /**
     * 下一个执行节点
     *
     * @param requestParameter 入参
     * @param dynamicContext   上下文
     * @return
     * @throws Exception
     */
    @Override
    public StrategyHandler<ArmoryCommandEntity, DefaultArmoryStrategyFactory.DynamicContext, String> get(ArmoryCommandEntity requestParameter, DefaultArmoryStrategyFactory.DynamicContext dynamicContext) throws Exception {
        return aiClientApiNode;
    }
}
