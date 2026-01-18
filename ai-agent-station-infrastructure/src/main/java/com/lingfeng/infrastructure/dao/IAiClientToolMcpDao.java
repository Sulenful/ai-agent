package com.lingfeng.infrastructure.dao;

import com.lingfeng.infrastructure.dao.po.AiClientToolMcp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * MCP客户端配置表 Mapper 接口
 */
@Mapper
public interface IAiClientToolMcpDao {

    /**
     * 插入单条记录
     */
    int insert(AiClientToolMcp record);

    /**
     * 批量插入
     */
    int insertBatch(@Param("list") List<AiClientToolMcp> list);

    /**
     * 根据ID删除
     */
    int deleteById(Long id);

    /**
     * 根据条件删除
     */
    int deleteByCondition(AiClientToolMcp condition);

    /**
     * 更新记录
     */
    int update(AiClientToolMcp record);

    /**
     * 根据ID查询
     */
    AiClientToolMcp selectById(Long id);

    /**
     * 根据条件查询单条记录
     */
    AiClientToolMcp selectByCondition(AiClientToolMcp condition);

    /**
     * 根据条件查询列表
     */
    List<AiClientToolMcp> selectList(AiClientToolMcp condition);

    /**
     * 查询所有记录
     */
    List<AiClientToolMcp> selectAll();

    /**
     * 根据条件统计数量
     */
    int count(AiClientToolMcp condition);
}