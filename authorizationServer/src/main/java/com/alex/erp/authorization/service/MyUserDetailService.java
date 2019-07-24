package com.alex.erp.authorization.service;


import com.alex.erp.dbutil.config.db.DataSourceEnum;
import com.alex.erp.dbutil.config.db.annotation.DataSource;
import com.alex.erp.dbutil.um.dao.MemberDao;
import com.alex.erp.dbutil.um.entity.*;
import com.alex.erp.dbutil.um.mapper.EsMemberMapper;
import com.alex.erp.dbutil.um.service.IEsMemberService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * 〈自定义UserDetailService〉
 * 自定义认证逻辑
 * @author wangmx
 * @since 1.0.0
 */

@Service//("userDetailService")
//@DataSource(DataSourceEnum.DB_UM)
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private IEsMemberService memberService;

//    @Autowired
//    private EsMemberMapper esMemberMapper;

    @Override
    public UserDetails loadUserByUsername(String memberName) throws UsernameNotFoundException {
//        Member member = memberDao.findByMemberName(memberName);
//        EsMember member = memberService.getOne(Wrappers.<EsMember>lambdaQuery().eq(EsMember::getMemberName,memberName),false);

        EsMember member =  memberService.findByMemberName(memberName);
        if (member == null) {
            throw new UsernameNotFoundException(memberName);
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        // 可用性 :true:可用 false:不可用
        boolean enabled = true;
        // 过期性 :true:没过期 false:过期
        boolean accountNonExpired = true;
        // 有效性 :true:凭证有效 false:凭证无效
        boolean credentialsNonExpired = true;
        // 锁定性 :true:未锁定 false:已锁定
        boolean accountNonLocked = true;
        for (EsRole role : member.getRoles()) {
            //角色必须是ROLE_开头，可以在数据库中设置
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRoleName());
            grantedAuthorities.add(grantedAuthority);
            //获取权限
            for (EsPermission permission : role.getPermissions()) {
                GrantedAuthority authority = new SimpleGrantedAuthority(permission.getUri());
                grantedAuthorities.add(authority);
            }
        }
        User user = new User(member.getMemberName(), member.getPassword(),
                enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuthorities);
        return user;
    }

}

