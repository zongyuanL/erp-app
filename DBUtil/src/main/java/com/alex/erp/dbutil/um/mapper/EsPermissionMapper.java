package com.alex.erp.dbutil.um.mapper;

import com.alex.erp.dbutil.um.entity.EsPermission;
import com.alex.erp.dbutil.um.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Alex ZY Liang
 * @since 2019-07-08
 */
public interface EsPermissionMapper extends BaseMapper<EsPermission> {

    /**
     * 根据角色id查找权限列表
     * @param roleId 角色id
     * @return 权限列表
     */
    List<EsPermission> findByRoleId(@Param("id") Integer roleId);

}
