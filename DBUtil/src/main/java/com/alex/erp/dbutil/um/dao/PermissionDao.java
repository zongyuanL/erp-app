package com.alex.erp.dbutil.um.dao;


import com.alex.erp.dbutil.um.entity.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 〈权限Dao〉
 *
 */
public interface PermissionDao  {

    /**
     * 根据角色id查找权限列表
     * @param roleId 角色id
     * @return 权限列表
     */
    List<Permission> findByRoleId(@Param("fid") Integer roleId);
}
