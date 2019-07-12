package com.alex.erp.basic.baseconfig.utils;

/**
 * @version 0.0.1
 * @Description
 * @Author Alex ZY Liang
 * @Date 2019-07-01 4:20 PM
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {




    public static String[] joinStringArray(String[] ...args){
        String[] tmp = new String[]{};
        for (String[] arg : args) {
            tmp = joinStringArray(tmp,arg);
        }
        return tmp;

    }
    private static String[] joinStringArray(String[] a,String[] b){
        int aLength = a.length;
        int bLength = b.length;

        int size = aLength+b.length;
        String[] tmp = new String[size];

        for (int i = 0; i < aLength; i++){
            tmp[i] = a[i];
        }
        for (int j=0;j<bLength;j++){
            tmp[aLength+j] = b[j];
        }
        return tmp;

    }


    public static String[] append(String[] arr, String str) {
        //获取数组长度
        int size = arr.length;
        //新建临时字符串数组，在原来基础上长度加一
        String[] tmp = new String[size + 1];
        //先遍历将原来的字符串数组数据添加到临时字符串数组
        for (int i = 0; i < size; i++){
            tmp[i] = arr[i];
        }
        //在最后添加上需要追加的数据
        tmp[size] = str;
        //返回拼接完成的字符串数组
        return tmp;
    }

}
