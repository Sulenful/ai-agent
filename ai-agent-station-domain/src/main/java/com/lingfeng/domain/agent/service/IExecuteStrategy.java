package com.lingfeng.domain.agent.service;

import com.lingfeng.domain.agent.model.entity.ExecuteCommandEntity;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

public interface IExecuteStrategy {
    void execute(ExecuteCommandEntity requestParameter, ResponseBodyEmitter emitter) throws Exception;
}
