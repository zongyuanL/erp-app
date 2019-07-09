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
public class EsRole extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 是否有效 1是 0否
     */
    private Boolean valid;

    /**
     * 创建日期
     */
    @TableField("createTime")
    private LocalDate createTime;

    /**
     * 更新日期
     */
    @TableField("updateTime")
    private LocalDate updateTime;

    @TableField(exist = false)
    private Set<EsPermission> permissions;

}
