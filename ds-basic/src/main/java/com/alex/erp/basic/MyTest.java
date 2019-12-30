package com.alex.erp.basic;

import com.alex.erp.basic.vo.Menu;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @version 0.0.1
 * @Description
 * @Author Alex ZY Liang
 * @Date 2019-07-29 10:08 AM
 */
public class MyTest {
    public static void main(String[]args){
        Menu menu = new Menu();
        menu.setId("Home");
        menu.setName("Dashboard");
        menu.setIcon("fa fa-tachometer");
        menu.setPath("/");
        menu.setTitle("首页");
        menu.setComponent("Index");

//
//        Menu home = new Menu();
//
//        home.setId("Home");
//        home.setName("首页");
//        home.setIcon("fa fa-tachometer");
//        home.setPath("/");
//        home.setTitle("首页");
//        home.setComponent("App");



        Menu children1 = new Menu();
        children1.setId("font_icon");
        children1.setName("字体图标");
        children1.setIcon("fa fa-th");
//        children1.setPath("/font_awesome");
//        children1.setTitle("FontAwesome 图标");
//        children1.setComponent("FontAwesome");

        Menu children2= new Menu();
        children2.setId("FontAwesome");
        children2.setName("FontAwesome 图标");
//        children2.setIcon("fa fa-th");
        children2.setPath("/font_awesome");
        children2.setTitle("FontAwesome 图标");
        children2.setComponent("FontAwesome");

        Menu children3= new Menu();
        children3.setId("ElementIcon");
        children3.setName("Element 图标");
//        children3.setIcon("fa fa-th");
        children3.setPath("/element_icon");
        children3.setTitle("Element 图标");
        children3.setComponent("ElementIcon");

        children1.addChildren(children2);
        children1.addChildren(children3);
        menu.addChildren(children1);


        String strJson=JSONObject.toJSONString(menu);
        String strArray=JSONArray.toJSONString(menu);

        System.out.println("strJson:"+strJson);
        System.out.println("strArray:"+strArray);
    }
}
