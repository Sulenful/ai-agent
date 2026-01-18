package com.lingfeng.infrastructure.dao;

import com.lingfeng.infrastructure.dao.po.AiAgentTaskSchedule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 智能体任务调度配置表 Mapper 接口
 */
@Mapper
public interface IAiAgentTaskScheduleDao {

    /**
     * 插入单条记录
     */
    int insert(AiAgentTaskSchedule record);

    /**
     * 批量插入
     */
    int insertBatch(@Param("list") List<AiAgentTaskSchedule> list);

    /**
     * 根据ID删除
     */
    int deleteById(Long id);

    /**
     * 根据条件删除
     */
    int deleteByCondition(AiAgentTaskSchedule condition);

    /**
     * 更新记录
     */
    int update(AiAgentTaskSchedule record);

    /**
     * 根据ID查询
     */
    AiAgentTaskSchedule selectById(Long id);

    /**
     * 根据条件查询单条记录
     */
    AiAgentTaskSchedule selectByCondition(AiAgentTaskSchedule condition);

    /**
     * 根据条件查询列表
     */
    List<AiAgentTaskSchedule> selectList(AiAgentTaskSchedule condition);

    /**
     * 查询所有记录
     */
    List<AiAgentTaskSchedule> selectAll();

    /**
     * 根据条件统计数量
     */
    int count(AiAgentTaskSchedule condition);
}