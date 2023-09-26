package com.shard.convert;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author chen_jinglong
 * @description: 测试转换类型效率
 * @date 2023/8/8 14:03
 */
public class Test {

    public static void main(String[] args) {

        // 随机生成100000个用户信息
        List<UserInfo> userInfoList = new ArrayList<>();
        long start1 = System.currentTimeMillis();
        System.out.println("开始生成用户信息："+start1);
        for (int i = 0; i < 100000; i++) {
            UserInfo userInfo = new UserInfo(i,"name"+i,i);
            userInfoList.add(userInfo);
        }
        long end1 = System.currentTimeMillis();
        System.out.println("生成用户信息结束："+end1);
        System.out.println("生成用户信息长度:"+userInfoList.size());
        System.out.println("生成用户信息耗时："+(end1-start1)+"ms");
        System.out.println("分割线----------------------------------");

        // 将userMapList转换成map
        long start2 = System.currentTimeMillis();
        System.out.println("开始转换成map："+start2);
        List<Map<String, Object>> userMapList = BeanAndMapConvert.convertListToListOfMaps(userInfoList);
        long end2 = System.currentTimeMillis();
        System.out.println("转换成map结束："+end2);
        System.out.println("转换后的map长度:"+userMapList.size());
        System.out.println("转换成map耗时："+(end2-end1)+"ms");
        System.out.println("分割线----------------------------------");

        // 将userMapList转换成UserInfoList
        long start3 = System.currentTimeMillis();
        System.out.println("开始转换成UserInfoList："+start3);
        List<UserInfo> userInfos = BeanAndMapConvert.convertListOfMapsToList(userMapList, UserInfo.class);
        long end3 = System.currentTimeMillis();
        System.out.println("转换成UserInfoList结束："+end3);
        System.out.println("转换后的userInfos长度:"+userInfos.size());
        System.out.println("转换成UserInfoList耗时："+(end3-start3)+"ms");
        System.out.println("分割线----------------------------------");

        // 使用fastJson将userInfoList转换成jsonList
        List<JSONObject> jsonObjects = new ArrayList<>();
        long start4 = System.currentTimeMillis();
        System.out.println("开始转换成jsonList："+start4);
        for (UserInfo userInfo : userInfoList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("userId",userInfo.getUserId());
            jsonObject.put("userName",userInfo.getUserName());
            jsonObject.put("userAge",userInfo.getUserAge());
            jsonObjects.add(jsonObject);
        }
        long end4 = System.currentTimeMillis();
        System.out.println("转换成jsonList结束："+end4);
        System.out.println("转换后的jsonObjects长度:"+jsonObjects.size());
        System.out.println("转换成jsonList耗时："+(end4-start4)+"ms");
        System.out.println("分割线----------------------------------");

        // 使用fastJson将jsonObjects转换成userInfoList
        long start5 = System.currentTimeMillis();
        System.out.println("开始转换成userInfoList："+start5);
        List<UserInfo> userInfos1 = new ArrayList<>();
        for (JSONObject jsonObject : jsonObjects) {
            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(jsonObject.getInteger("userId"));
            userInfo.setUserName(jsonObject.getString("userName"));
            userInfo.setUserAge(jsonObject.getInteger("userAge"));
            userInfos1.add(userInfo);
        }
        long end5 = System.currentTimeMillis();
        System.out.println("转换成userInfoList结束："+end5);
        System.out.println("转换后的userInfos1长度:"+userInfos1.size());
        System.out.println("转换成userInfoList耗时："+(end5-start5)+"ms");
        System.out.println("分割线----------------------------------");
    }
}
