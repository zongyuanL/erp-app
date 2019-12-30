package com.alex.erp.basic.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 0.0.1
 * @Description
 * @Author Alex ZY Liang
 * @Date 2019-07-29 9:59 AM
 */
@Data
@ApiModel(value="菜单")
public class Menu {
    private String id;

    @ApiModelProperty( value = "菜单名称")
    private String name;
    private String icon;
    private String path;
    private String component;
    private String title;
    private boolean keepAlive;
    private List<Menu> children;

    public void addChildren (Menu menu){
        if(null == children ){
            children = new ArrayList<>();
        }
        this.children.add(menu);
    }
}
