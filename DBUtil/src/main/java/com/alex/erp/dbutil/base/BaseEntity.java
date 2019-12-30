package com.alex.erp.dbutil.base;

//import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @version 0.0.1
 * @Description
 * @Author Alex ZY Liang
 * @Date 2019-07-04 3:28 PM
 */
@Data
@ApiModel("基础实体类")
public abstract class BaseEntity implements Serializable {


    @TableId(type = IdType.UUID)
    private String id;

    private String status;


    @ApiModelProperty(value = "创建人")
    @TableField("createBy")
    private String createBy;

    @ApiModelProperty(value = "更新人")
    @TableField("updateBy")
    private String updateBy;

    @ApiModelProperty(value = "更新时间")
    @JsonSerialize(using = DateToLongSerializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("dateUpdated")
    private Date dateUpdated;

    @ApiModelProperty(value = "创建时间")
    @JsonSerialize(using = DateToLongSerializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("dateCreated")
    private Date dateCreated;


}