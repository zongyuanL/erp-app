package com.alex.erp.lesson.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @version 0.0.1
 * @Description
 * @Author Alex ZY Liang
 * @Date 2019-10-23 2:58 PM
 */
public class CommonUtils {
    /**
     *
     * @Description 去掉一个list中的重复元素
     * @param list
     * @return java.util.List
     * @Throw
     * @Author Alex ZY Liang
     * @Date 2019/10/24 9:08 AM
     * @Version 0.0.1
     **/
    public static List listRemoveDuplicate(List list) {
        HashSet h = new HashSet(list);
        list.clear();
        list.addAll(h);
        return list;
    }

    /**
     *
     * @Description 取多个list的交集
     * @param lists
     * @return java.util.List
     * @Throw
     * @Author Alex ZY Liang
     * @Date 2019/10/24 9:08 AM
     * @Version 0.0.1
     **/
    public static <T> List<T> getIntersectedList(List... lists) {
        List returnList = null;
        for (List list : lists) {
            if (null == returnList) {
                returnList = list;
                continue;
            }
            returnList = (List) returnList.stream().filter(item -> list.contains(item)).collect(Collectors.toList());
            if (returnList.size() == 0) {
                break;
            }
        }
        return returnList;
    }

    /**
     *
     * @Description 取list的差集
     * @param mainList
     * @param lists
     * @return java.util.List
     * @Throw
     * @Author Alex ZY Liang
     * @Date 2019/10/24 9:12 AM
     * @Version 0.0.1
     **/
    public static <T> List<T> getDifferenceList(List mainList, List... lists){
        List returnList = mainList;
        for (List list : lists) {
            if (returnList.size() == 0) {
                break;
            }
            returnList = (List) returnList.stream().filter(item -> !list.contains(item)).collect(Collectors.toList());

        }

        return returnList;
    }


    public static <T> List<T> deepCopyList(List<T> src) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(src);

        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream in = new ObjectInputStream(byteIn);
        @SuppressWarnings("unchecked")
        List<T> dest = (List<T>) in.readObject();
        return dest;
    }
}
