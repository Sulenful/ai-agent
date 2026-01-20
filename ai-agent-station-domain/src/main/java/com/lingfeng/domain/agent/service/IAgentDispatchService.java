package com.lingfeng.domain.agent.service;

import com.lingfeng.domain.agent.model.entity.ExecuteCommandEntity;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

/**
 * @author Lingfeng Su
 * @date 2026/1/20
 * @description
 */
public interface IAgentDispatchService {
    void dispatch(ExecuteCommandEntity requestParameter, ResponseBodyEmitter emitter) throws Exception;
}
