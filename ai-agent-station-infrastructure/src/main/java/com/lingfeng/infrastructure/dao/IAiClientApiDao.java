package com.lingfeng.infrastructure.dao;

import com.lingfeng.infrastructure.dao.po.AiClientApi;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * OpenAI API配置表 Mapper 接口
 */
@Mapper
public interface IAiClientApiDao {

    /**
     * 插入单条记录
     */
    int insert(AiClientApi record);

    /**
     * 批量插入
     */
    int insertBatch(@Param("list") List<AiClientApi> list);

    /**
     * 根据ID删除
     */
    int deleteById(Long id);

    /**
     * 根据条件删除
     */
    int deleteByCondition(AiClientApi condition);

    /**
     * 更新记录
     */
    int update(AiClientApi record);

    /**
     * 根据ID查询
     */
    AiClientApi selectById(Long id);

    /**
     * 根据条件查询单条记录
     */
    AiClientApi selectByCondition(AiClientApi condition);

    /**
     * 根据条件查询列表
     */
    List<AiClientApi> selectList(AiClientApi condition);

    /**
     * 查询所有记录
     */
    List<AiClientApi> selectAll();

    /**
     * 根据条件统计数量
     */
    int count(AiClientApi condition);
}