package com.alex.erp.dbutil.um.entity;

import java.time.LocalDate;
import java.util.Set;

import com.baomidou.mybatisplus.annotation.TableField;
import com.alex.erp.dbutil.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Alex ZY Liang
 * @since 2019-07-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class EsMember extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 会员名
     */
    private String memberName;

    /**
     * 关联的微信openID
     **/
    @TableField("openID")
    private String openID;

    /**
     * 密码
     */
    private String password;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 性别 1男0女
     */
    private Boolean sex;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 出生日
     */
    private LocalDate birthday;

    /**
     * 注册日期
     */
    @TableField("createTime")
    private LocalDate createTime;

    @TableField(exist = false)
    private Set<EsRole> roles;

    @TableField(exist = false)
    private String accessToken;

    @TableField(exist = false)
    private String refreshToken;


}
