package com.lingfeng.infrastructure.dao;

import com.lingfeng.infrastructure.dao.po.AiClientSystemPrompt;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统提示词配置表 Mapper 接口
 */
@Mapper
public interface IAiClientSystemPromptDao {

    /**
     * 插入单条记录
     */
    int insert(AiClientSystemPrompt record);

    /**
     * 批量插入
     */
    int insertBatch(@Param("list") List<AiClientSystemPrompt> list);

    /**
     * 根据ID删除
     */
    int deleteById(Long id);

    /**
     * 根据条件删除
     */
    int deleteByCondition(AiClientSystemPrompt condition);

    /**
     * 更新记录
     */
    int update(AiClientSystemPrompt record);

    /**
     * 根据ID查询
     */
    AiClientSystemPrompt selectById(Long id);

    /**
     * 根据条件查询单条记录
     */
    AiClientSystemPrompt selectByCondition(AiClientSystemPrompt condition);

    /**
     * 根据条件查询列表
     */
    List<AiClientSystemPrompt> selectList(AiClientSystemPrompt condition);

    /**
     * 查询所有记录
     */
    List<AiClientSystemPrompt> selectAll();

    /**
     * 根据条件统计数量
     */
    int count(AiClientSystemPrompt condition);
}