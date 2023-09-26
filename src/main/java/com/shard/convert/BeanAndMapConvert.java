package com.shard.convert;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author chen_jinglong
 * @description: Map和Bean的互相转化
 * @date 2023/8/4 11:30
 */
public class BeanAndMapConvert {

    /**
     * 将对象转换为HashMap
     *
     * @param obj
     * @return
     */
    public static <T> Map<String, Object> convertObjectToHashMap(T obj) {
        Map<String, Object> hashMap = new HashMap<>();

        // 使用反射来访问对象的字段
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            // 设置字段的可访问性为true，反射允许我们通过字段名称来访问或设置对象的私有字段
            field.setAccessible(true);
            try {
                // 获取字段的值，并将字段名和对应的值放入HashMap中
                hashMap.put(field.getName(), field.get(obj));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return hashMap;
    }

    /**
     * 将HashMap转换为对象
     *
     * @param map
     * @param clazz
     * @return
     */
    public static <T> T convertHashMapToObject(Map<String, Object> map, Class<T> clazz) {
        T obj = null;

        try {
            // 通过反射机制根据入参clazz创建一个给定类
            obj = clazz.getDeclaredConstructor().newInstance();

            // 遍历HashMap中的键值对
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                String fieldName = entry.getKey();
                Object value = entry.getValue();

                try {
                    // 通过反射机制获取给定类的字段
                    java.lang.reflect.Field field = clazz.getDeclaredField(fieldName);
                    // 设置字段的可访问性为true，反射允许我们通过字段名称来访问或设置对象的私有字段
                    field.setAccessible(true);
                    // 将字段的值设置到新创建的给定类对象中
                    field.set(obj, value);
                } catch (NoSuchFieldException e) {
                    // 当给定类中没有找到对应的字段时，忽略该字段，打印错误信息
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            // 当创建给定类对象失败时，打印错误信息
            e.printStackTrace();
        }

        return obj;
    }


    /**
     * 将List<T>转换为List<Map<String, Object>>
     *
     * @param objectList
     * @return
     */
    public static <T> List<Map<String, Object>> convertListToListOfMaps(List<T> objectList) {
        List<Map<String, Object>> listOfMaps = new ArrayList<>();

        for (T obj : objectList) {
            Map<String, Object> map = convertObjectToHashMap(obj);
            listOfMaps.add(map);
        }

        return listOfMaps;
    }

    /**
     * 将List<Map<String, Object>>转换为List<T>
     *
     * @param listOfMaps
     * @param clazz
     * @return
     */
    public static <T> List<T> convertListOfMapsToList(List<Map<String, Object>> listOfMaps, Class<T> clazz) {
        List<T> objectList = new ArrayList<>();

        for (Map<String, Object> map : listOfMaps) {
            T obj = convertHashMapToObject(map, clazz);
            objectList.add(obj);
        }

        return objectList;
    }

    public static void main(String[] args) {
        // 将对象转换为HashMap
        UserInfo u1 = new UserInfo(1, "张三", 21);
        UserInfo u2 = new UserInfo(2, "张三", 23);
        UserInfo u3 = new UserInfo(3, "张三", 22);

        // 测试对象转换为HashMap
        Map<String, Object> hashMap = convertObjectToHashMap(u1);
        System.out.println("测试对象转换为HashMap:"+hashMap);
        // 测试HashMap转回对象
        UserInfo userInfo = convertHashMapToObject(hashMap, UserInfo.class);
        System.out.println("测试HashMap转回对象:"+userInfo.toString());

        // 测试List<T>转换为List<Map<String, Object>>
        List<UserInfo> listBean = Arrays.asList(u1, u2, u3);
        List<Map<String, Object>> listMap = convertListToListOfMaps(listBean);
        System.out.println("测试List<T>转换为List<Map<String, Object>>:"+listMap);

        // 测试List<Map<String, Object>>转换为List<T>
        List<UserInfo> listBean2 = convertListOfMapsToList(listMap, UserInfo.class);
        System.out.println("测试List<Map<String, Object>>转换为List<T>:"+listBean2);
    }


}
