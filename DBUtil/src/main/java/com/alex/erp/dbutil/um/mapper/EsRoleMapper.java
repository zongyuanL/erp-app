package com.alex.erp.dbutil.um.mapper;

import com.alex.erp.dbutil.um.entity.EsRole;
import com.alex.erp.dbutil.um.entity.Role;
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
public interface EsRoleMapper extends BaseMapper<EsRole> {

    /**
     * 根据用户id查找角色列表
     * @param memberId 用户id
     * @return 角色列表
     */
    List<EsRole> findByMemberId(@Param("id") Integer memberId);

}
