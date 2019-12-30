package com.alex.erp.dbutil.um.entity;

import com.alex.erp.dbutil.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Alex ZY Liang
 * @since 2019-08-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class EsMemberRefreshToken extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String refreshToken;

    private String memberId;


}
