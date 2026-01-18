package com.lingfeng.infrastructure.dao;

import com.lingfeng.infrastructure.dao.po.AdminUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 管理员用户表 Mapper 接口
 */
@Mapper
public interface IAdminUserDao {

    /**
     * 插入单条记录
     */
    int insert(AdminUser record);

    /**
     * 批量插入
     */
    int insertBatch(@Param("list") List<AdminUser> list);

    /**
     * 根据ID删除
     */
    int deleteById(Long id);

    /**
     * 根据条件删除
     */
    int deleteByCondition(AdminUser condition);

    /**
     * 更新记录
     */
    int update(AdminUser record);

    /**
     * 根据ID查询
     */
    AdminUser selectById(Long id);

    /**
     * 根据条件查询单条记录
     */
    AdminUser selectByCondition(AdminUser condition);

    /**
     * 根据条件查询列表
     */
    List<AdminUser> selectList(AdminUser condition);

    /**
     * 查询所有记录
     */
    List<AdminUser> selectAll();

    /**
     * 根据条件统计数量
     */
    int count(AdminUser condition);
}