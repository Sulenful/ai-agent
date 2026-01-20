package com.lingfeng.infrastructure.dao;

import com.lingfeng.infrastructure.dao.po.AiAgent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * AI智能体配置表 Mapper 接口
 */
@Mapper
public interface IAiAgentDao {

    /**
     * 插入单条记录
     */
    int insert(AiAgent record);

    /**
     * 批量插入
     */
    int insertBatch(@Param("list") List<AiAgent> list);

    /**
     * 根据ID删除
     */
    int deleteById(Long id);

    /**
     * 根据条件删除
     */
    int deleteByCondition(AiAgent condition);

    /**
     * 更新记录
     */
    int update(AiAgent record);

    /**
     * 根据ID查询
     */
    AiAgent selectById(Long id);

    /**
     * 根据条件查询单条记录
     */
    AiAgent selectByCondition(AiAgent condition);

    /**
     * 根据条件查询列表
     */
    List<AiAgent> selectList(AiAgent condition);

    /**
     * 查询所有记录
     */
    List<AiAgent> selectAll();

    /**
     * 根据条件统计数量
     */
    int count(AiAgent condition);

    AiAgent queryByAgentId(@Param("agentId") String agentId);

    List<AiAgent> queryEnabledAgents();


}