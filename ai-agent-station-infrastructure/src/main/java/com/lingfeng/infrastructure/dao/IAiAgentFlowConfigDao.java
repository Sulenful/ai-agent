package com.lingfeng.infrastructure.dao;

import com.lingfeng.infrastructure.dao.po.AiAgentFlowConfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 智能体-客户端关联表 Mapper 接口
 */
@Mapper
public interface IAiAgentFlowConfigDao {

    /**
     * 插入单条记录
     */
    int insert(AiAgentFlowConfig record);

    /**
     * 批量插入
     */
    int insertBatch(@Param("list") List<AiAgentFlowConfig> list);

    /**
     * 根据ID删除
     */
    int deleteById(Long id);

    /**
     * 根据条件删除
     */
    int deleteByCondition(AiAgentFlowConfig condition);

    /**
     * 更新记录
     */
    int update(AiAgentFlowConfig record);

    /**
     * 根据ID查询
     */
    AiAgentFlowConfig selectById(Long id);

    /**
     * 根据条件查询单条记录
     */
    AiAgentFlowConfig selectByCondition(AiAgentFlowConfig condition);

    /**
     * 根据条件查询列表
     */
    List<AiAgentFlowConfig> selectList(AiAgentFlowConfig condition);

    /**
     * 查询所有记录
     */
    List<AiAgentFlowConfig> selectAll();

    /**
     * 根据条件统计数量
     */
    int count(AiAgentFlowConfig condition);

    List<AiAgentFlowConfig> queryByAgentId(String aiAgentId);

}