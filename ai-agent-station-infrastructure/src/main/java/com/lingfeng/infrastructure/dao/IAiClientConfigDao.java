package com.lingfeng.infrastructure.dao;

import com.lingfeng.infrastructure.dao.po.AiClientConfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * AI客户端统一关联配置表 Mapper 接口
 */
@Mapper
public interface IAiClientConfigDao {

    /**
     * 插入单条记录
     */
    int insert(AiClientConfig record);

    /**
     * 批量插入
     */
    int insertBatch(@Param("list") List<AiClientConfig> list);

    /**
     * 根据ID删除
     */
    int deleteById(Long id);

    /**
     * 根据条件删除
     */
    int deleteByCondition(AiClientConfig condition);

    /**
     * 更新记录
     */
    int update(AiClientConfig record);

    /**
     * 根据ID查询
     */
    AiClientConfig selectById(Long id);

    /**
     * 根据条件查询单条记录
     */
    AiClientConfig selectByCondition(AiClientConfig condition);

    /**
     * 根据条件查询列表
     */
    List<AiClientConfig> selectList(AiClientConfig condition);

    /**
     * 查询所有记录
     */
    List<AiClientConfig> selectAll();

    /**
     * 根据条件统计数量
     */
    int count(AiClientConfig condition);

    List<AiClientConfig> queryBySourceTypeAndId(@Param("sourceType") String sourceType, @Param("sourceId") String clientId);
}