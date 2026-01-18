package com.lingfeng.infrastructure.dao;

import com.lingfeng.infrastructure.dao.po.AiClientRagOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 知识库配置表 Mapper 接口
 */
@Mapper
public interface IAiClientRagOrderDao {

    /**
     * 插入单条记录
     */
    int insert(AiClientRagOrder record);

    /**
     * 批量插入
     */
    int insertBatch(@Param("list") List<AiClientRagOrder> list);

    /**
     * 根据ID删除
     */
    int deleteById(Long id);

    /**
     * 根据条件删除
     */
    int deleteByCondition(AiClientRagOrder condition);

    /**
     * 更新记录
     */
    int update(AiClientRagOrder record);

    /**
     * 根据ID查询
     */
    AiClientRagOrder selectById(Long id);

    /**
     * 根据条件查询单条记录
     */
    AiClientRagOrder selectByCondition(AiClientRagOrder condition);

    /**
     * 根据条件查询列表
     */
    List<AiClientRagOrder> selectList(AiClientRagOrder condition);

    /**
     * 查询所有记录
     */
    List<AiClientRagOrder> selectAll();

    /**
     * 根据条件统计数量
     */
    int count(AiClientRagOrder condition);
}