package com.alex.erp.dbutil.um.service;

import com.alex.erp.dbutil.um.entity.EsMember;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Alex ZY Liang
 * @since 2019-07-08
 */
public interface IEsMemberService extends IService<EsMember> {

    /**
     * 根据会员名查找会员
     * @param memberName 会员名
     * @return 会员
     */
    EsMember findByMemberName(String memberName);

}
