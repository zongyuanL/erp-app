package com.alex.erp.dbutil.um.entity;

import java.time.LocalDate;
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
public class EsPermission extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 方法类型
     */
    private String method;

    /**
     * 网关前缀
     */
    private String zuulPrefix;

    /**
     * 服务前缀
     */
    private String servicePrefix;

    /**
     * 请求路径
     */
    private String uri;

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


}
