package com.lingfeng.infrastructure.dao;

import com.lingfeng.infrastructure.dao.po.AiAgentDrawConfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * AI智能体拖拉拽配置主表 Mapper 接口
 */
@Mapper
public interface IAiAgentDrawConfigDao {

    /**
     * 插入单条记录
     */
    int insert(AiAgentDrawConfig record);

    /**
     * 批量插入
     */
    int insertBatch(@Param("list") List<AiAgentDrawConfig> list);

    /**
     * 根据ID删除
     */
    int deleteById(Long id);

    /**
     * 根据条件删除
     */
    int deleteByCondition(AiAgentDrawConfig condition);

    /**
     * 更新记录
     */
    int update(AiAgentDrawConfig record);

    /**
     * 根据ID查询
     */
    AiAgentDrawConfig selectById(Long id);

    /**
     * 根据条件查询单条记录
     */
    AiAgentDrawConfig selectByCondition(AiAgentDrawConfig condition);

    /**
     * 根据条件查询列表
     */
    List<AiAgentDrawConfig> selectList(AiAgentDrawConfig condition);

    /**
     * 查询所有记录
     */
    List<AiAgentDrawConfig> selectAll();

    /**
     * 根据条件统计数量
     */
    int count(AiAgentDrawConfig condition);
}