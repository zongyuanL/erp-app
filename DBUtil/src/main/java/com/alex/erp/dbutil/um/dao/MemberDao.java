package com.alex.erp.dbutil.um.dao;


import com.alex.erp.dbutil.um.entity.Member;

/**
 * 〈用户Dao〉
 *
 */
public interface MemberDao {

    /**
     * 根据会员名查找会员
     * @param memberName 会员名
     * @return 会员
     */
    Member findByMemberName(String memberName);
}
