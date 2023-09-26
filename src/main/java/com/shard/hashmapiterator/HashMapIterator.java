package com.shard.hashmapiterator;

import com.shard.convert.BeanAndMapConvert;
import com.shard.convert.UserInfo;

import java.util.*;

/**
 * @author chen_jinglong
 * @description: HashMap遍历的各种方式
 * @date 2023/8/8 16:14
 */
public class HashMapIterator {

    public static void main(String[] args) {
        // 生成500w个UserInfo
        HashMap<Integer, UserInfo> userInfoHashMap = new HashMap<>();
        for (int i = 0; i < 5000000; i++) {
            userInfoHashMap.put(i,new UserInfo(i,"name"+i,i));
        }
        // 将100w个UserInfo转换成HashMap,准备测试遍历效率
        long time1 = entrySetIterator(userInfoHashMap);
        long time2 = onlyKeysOrValues(userInfoHashMap);
        long time3 = iteratorInType(userInfoHashMap);
        long time4 = iteratorNoType(userInfoHashMap);
        long time5 = iteratorByKeySet(userInfoHashMap);
        System.out.println("time1耗时："+time1+"ms");
        System.out.println("time2耗时："+time2+"ms");
        System.out.println("time3耗时："+time3+"ms");
        System.out.println("time4耗时："+time4+"ms");
        System.out.println("time5耗时："+time5+"ms");
    }

    /**
     * 使用entrySet遍历
     * @param param
     */
    public static long entrySetIterator(HashMap<Integer, UserInfo> param){
        long start = System.currentTimeMillis();
        for (Map.Entry<Integer, UserInfo> entry : param.entrySet()) {
            System.out.println(entry.getKey()+"--"+entry.getValue());
        }
        long end = System.currentTimeMillis();
        System.out.println("使用entrySet遍历耗时："+(end-start)+"ms");
        return end-start;
    }


    /**
     * 只获取key或者value的遍历方式
     * @param param
     */
    public static long onlyKeysOrValues(HashMap<Integer, UserInfo> param){
        //遍历map中的键
        long keyStart = System.currentTimeMillis();
        // param.keySet()返回所有key集合，param.values()返回所有value集合
        for (Integer key : param.keySet()) {
            System.out.println("Key = " + key);
        }
        long keyEnd = System.currentTimeMillis();
        System.out.println("只需要key遍历耗时："+(keyEnd-keyStart)+"ms");
        return keyEnd-keyStart;
    }

    /**
     * 根据key值获取value信息的遍历方式
     * @param param
     */
    public static long iteratorByKeySet(HashMap<Integer,UserInfo> param){
        long start = System.currentTimeMillis();
        for (Integer key : param.keySet()) {
            UserInfo value = param.get(key);
            System.out.println("Key = " + key + ", Value = " + value);
        }
        long end = System.currentTimeMillis();
        System.out.println("根据key值获取value方式遍历耗时："+(end-start)+"ms");
        return end-start;
    }

    /**
     * 使用迭代器遍历(使用泛型)
     * @param param
     */
    public static long iteratorInType(HashMap<Integer,UserInfo> param){
        long start = System.currentTimeMillis();
        // 获取迭代器
        Iterator<Map.Entry<Integer, UserInfo>> entries = param.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<Integer, UserInfo> entry = entries.next();
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }
        long end = System.currentTimeMillis();
        System.out.println("迭代器(使用泛型)遍历耗时："+(end-start)+"ms");
        return end-start;
    }

    /**
     * 使用迭代器遍历(不使用泛型)
     * @param param
     */
    public static long iteratorNoType(HashMap<Integer,UserInfo> param){
        long start = System.currentTimeMillis();
        // 获取迭代器
        Iterator entries = param.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry entry = (Map.Entry) entries.next();
            Integer key = (Integer)entry.getKey();
            UserInfo value = (UserInfo) entry.getValue();
            System.out.println("Key = " + key + ", Value = " + value);
        }
        long end = System.currentTimeMillis();
        System.out.println("迭代器(不适用泛型)遍历耗时："+(end-start)+"ms");
        return end-start;
    }
}