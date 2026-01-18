package com.lingfeng.infrastructure.dao;

import com.lingfeng.infrastructure.dao.po.AiClientModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 聊天模型配置表 Mapper 接口
 */
@Mapper
public interface IAiClientModelDao {

    /**
     * 插入单条记录
     */
    int insert(AiClientModel record);

    /**
     * 批量插入
     */
    int insertBatch(@Param("list") List<AiClientModel> list);

    /**
     * 根据ID删除
     */
    int deleteById(Long id);

    /**
     * 根据条件删除
     */
    int deleteByCondition(AiClientModel condition);

    /**
     * 更新记录
     */
    int update(AiClientModel record);

    /**
     * 根据ID查询
     */
    AiClientModel selectById(Long id);

    /**
     * 根据条件查询单条记录
     */
    AiClientModel selectByCondition(AiClientModel condition);

    /**
     * 根据条件查询列表
     */
    List<AiClientModel> selectList(AiClientModel condition);

    /**
     * 查询所有记录
     */
    List<AiClientModel> selectAll();

    /**
     * 根据条件统计数量
     */
    int count(AiClientModel condition);
}