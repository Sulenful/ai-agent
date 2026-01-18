package com.lingfeng.infrastructure.dao;

import com.lingfeng.infrastructure.dao.po.AiClient;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * AI客户端配置表 Mapper 接口
 */
@Mapper
public interface IAiClientDao {

    /**
     * 插入单条记录
     */
    int insert(AiClient record);

    /**
     * 批量插入
     */
    int insertBatch(@Param("list") List<AiClient> list);

    /**
     * 根据ID删除
     */
    int deleteById(Long id);

    /**
     * 根据条件删除
     */
    int deleteByCondition(AiClient condition);

    /**
     * 更新记录
     */
    int update(AiClient record);

    /**
     * 根据ID查询
     */
    AiClient selectById(Long id);

    /**
     * 根据条件查询单条记录
     */
    AiClient selectByCondition(AiClient condition);

    /**
     * 根据条件查询列表
     */
    List<AiClient> selectList(AiClient condition);

    /**
     * 查询所有记录
     */
    List<AiClient> selectAll();

    /**
     * 根据条件统计数量
     */
    int count(AiClient condition);
}