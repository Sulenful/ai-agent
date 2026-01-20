package com.lingfeng.api;

import com.lingfeng.api.dto.AiAgentResponseDTO;
import com.lingfeng.api.dto.ArmoryAgentRequestDTO;
import com.lingfeng.api.dto.ArmoryApiRequestDTO;
import com.lingfeng.api.dto.AutoAgentRequestDTO;
import com.lingfeng.api.response.Response;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import java.util.List;

/**
 * @author Lingfeng Su
 * @date 2026/1/20
 * @description
 */
public interface IAiAgentService {
    ResponseBodyEmitter autoAgent(AutoAgentRequestDTO request, HttpServletResponse response);

    /**
     * 装配智能体
     */
    Response<Boolean> armoryAgent(ArmoryAgentRequestDTO request);

    /**
     * 查询可用的智能体列表
     */
    Response<List<AiAgentResponseDTO>> queryAvailableAgents();

    /**
     * 装配API
     */
    Response<Boolean> armoryApi(ArmoryApiRequestDTO request);
}
