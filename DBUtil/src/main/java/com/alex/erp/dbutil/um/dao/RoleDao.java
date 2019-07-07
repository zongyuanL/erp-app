package com.alex.erp.dbutil.um.dao;




import com.alex.erp.dbutil.um.entity.Role;

import java.util.List;

/**
 * 〈角色Dao〉
 *
 */
public interface RoleDao  {

    /**
     * 根据用户id查找角色列表
     * @param memberId 用户id
     * @return 角色列表
     */
    List<Role> findByMemberId(Integer memberId);
}
