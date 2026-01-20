package com.lingfeng.domain.agent.service;

import com.lingfeng.domain.agent.model.valobj.AiAgentVO;

import java.util.List;

public interface IArmoryService {

    List<AiAgentVO> acceptArmoryAllAvailableAgents();

    void acceptArmoryAgent(String agentId);

    List<AiAgentVO> queryAvailableAgents();

    void acceptArmoryAgentClientModelApi(String apiId);
}
