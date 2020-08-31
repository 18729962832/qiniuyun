package com.example.qiniu.qiniuyun.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListUtil {

    /**
     * 从list中取固定条数的数据放入新的list里
     * @param resList
     * @param count
     * @param <T>
     * @return
     */
    public static <T> List<List<T>> split(List<T> resList, int count) {
        if (resList == null || count < 1)
            return null;
        List<List<T>> ret = new ArrayList<List<T>>();
        int size = resList.size();
        if (size <= count) {
            // 数据量不足count指定的大小
            ret.add(resList);
        } else {
            int pre = size / count;
            int last = size % count;
            // 前面pre个集合，每个大小都是count个元素
            for (int i = 0; i < pre; i++) {
                List<T> itemList = new ArrayList<T>();
                for (int j = 0; j < count; j++) {
                    itemList.add(resList.get(i * count + j));
                }
                ret.add(itemList);
            }
            // last的进行处理
            if (last > 0) {
                List<T> itemList = new ArrayList<T>();
                for (int i = 0; i < last; i++) {
                    itemList.add(resList.get(pre * count + i));
                }
                ret.add(itemList);
            }
        }
        return ret;
    }

    @SuppressWarnings("rawtypes")
    public static boolean isValidateList(List list){
        if (list != null && list.size() != 0) {
            return true;
        }else{
            return false;
        }
    }

    /**
     * list集合去重复
     * @param list
     * @param <T>
     * @return
     */
    public static <T> List<T> listDuplicateRemoval(List<T> list){
        List<T> newList = new ArrayList<>();
        if(!isValidateList(list)){
            return newList;
        }
        return newList = list.stream().distinct().collect(Collectors.toList());
    }

}
