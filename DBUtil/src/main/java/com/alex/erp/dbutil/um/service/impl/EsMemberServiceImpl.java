package com.alex.erp.dbutil.um.service.impl;

import com.alex.erp.dbutil.um.entity.EsMember;
import com.alex.erp.dbutil.um.mapper.EsMemberMapper;
import com.alex.erp.dbutil.um.service.IEsMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Alex ZY Liang
 * @since 2019-07-08
 */
@Service
public class EsMemberServiceImpl extends ServiceImpl<EsMemberMapper, EsMember> implements IEsMemberService {

    @Autowired
    private EsMemberMapper esMemberMapper;

    /**
     * 根据会员名查找会员
     * @param memberName 会员名
     * @return 会员
     */
    @Override
    public EsMember findByMemberName(String memberName){
      return esMemberMapper.findByMemberName(memberName);
    };

}
