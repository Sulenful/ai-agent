package com.lingfeng.infrastructure.dao;

import com.lingfeng.infrastructure.dao.po.AiClientAdvisor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 顾问配置表 Mapper 接口
 */
@Mapper
public interface IAiClientAdvisorDao {

    /**
     * 插入单条记录
     */
    int insert(AiClientAdvisor record);

    /**
     * 批量插入
     */
    int insertBatch(@Param("list") List<AiClientAdvisor> list);

    /**
     * 根据ID删除
     */
    int deleteById(Long id);

    /**
     * 根据条件删除
     */
    int deleteByCondition(AiClientAdvisor condition);

    /**
     * 更新记录
     */
    int update(AiClientAdvisor record);

    /**
     * 根据ID查询
     */
    AiClientAdvisor selectById(Long id);

    /**
     * 根据条件查询单条记录
     */
    AiClientAdvisor selectByCondition(AiClientAdvisor condition);

    /**
     * 根据条件查询列表
     */
    List<AiClientAdvisor> selectList(AiClientAdvisor condition);

    /**
     * 查询所有记录
     */
    List<AiClientAdvisor> selectAll();

    /**
     * 根据条件统计数量
     */
    int count(AiClientAdvisor condition);
}