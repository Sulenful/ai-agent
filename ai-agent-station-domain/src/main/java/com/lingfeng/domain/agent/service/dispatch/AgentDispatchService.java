package com.lingfeng.domain.agent.service.dispatch;

import com.lingfeng.domain.agent.adapter.repository.IAgentRepository;
import com.lingfeng.domain.agent.model.entity.ExecuteCommandEntity;
import com.lingfeng.domain.agent.model.valobj.AiAgentVO;
import com.lingfeng.domain.agent.service.IAgentDispatchService;
import com.lingfeng.domain.agent.service.IExecuteStrategy;
import com.lingfeng.types.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author Lingfeng Su
 * @date 2026/1/20
 * @description 智能体分发接口
 */
@Service
@Slf4j(topic = "AgentDispatchService")
public class AgentDispatchService implements IAgentDispatchService {
    // 自动注入机制：key为beanName，value为实现类
    @Resource
    private Map<String, IExecuteStrategy> executeStrategyMap;

    @Resource
    private IAgentRepository repository;

    @Resource
    private ThreadPoolExecutor threadPoolExecutor;
    @Override
    public void dispatch(ExecuteCommandEntity requestParameter, ResponseBodyEmitter emitter) throws Exception {
        // 查询智能体
        AiAgentVO aiAgentVO = repository.queryAiAgentByAgentId(requestParameter.getAiAgentId());
        // 获取执行策略
        IExecuteStrategy executeStrategy = executeStrategyMap.get(aiAgentVO.getStrategy());
        if (Objects.isNull(executeStrategy)) {
            throw new BizException("不存在的执行策略类型 strategy:" + executeStrategy);
        }

        // 异步执行
        threadPoolExecutor.execute(() -> {
            try {
                executeStrategy.execute(requestParameter, emitter);
            } catch (Exception e) {
                log.error("AutoAgent执行异常：{}", e.getMessage(), e);
                try {
                    emitter.send("执行异常：" + e.getMessage());
                } catch (Exception ex) {
                    log.error("发送异常信息失败：{}", ex.getMessage(), ex);
                }
            } finally {
                try {
                    emitter.complete();
                } catch (Exception e) {
                    log.error("完成流式输出失败：{}", e.getMessage(), e);
                }
            }
        });
    }
}
