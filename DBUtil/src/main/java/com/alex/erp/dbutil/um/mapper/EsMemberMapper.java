package com.alex.erp.dbutil.um.mapper;

import com.alex.erp.dbutil.um.entity.EsMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Alex ZY Liang
 * @since 2019-07-08
 */
public interface EsMemberMapper extends BaseMapper<EsMember> {

    /**
     * 根据会员名查找会员
     * @param memberName 会员名
     * @return 会员
     */
    EsMember findByMemberName(String memberName);

}
