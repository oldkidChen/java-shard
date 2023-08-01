package com.shard.lambda;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author chen_jinglong
 * @description: 分组
 * @date 2023/8/1 16:46
 */
public class GroupExample {

    static class UserInfo{
        private String name;
        private Integer gender;

        public UserInfo(String name, Integer gender) {
            this.name = name;
            this.gender = gender;
        }
        public Integer getGender() {
            return gender;
        }
        public void setGender(Integer gender) {
            this.gender = gender;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }

    }

    public static void main(String[] args) {
        UserInfo a = new UserInfo("张三",1);
        UserInfo b = new UserInfo("李四",2);
        UserInfo c = new UserInfo("王五",1);
        UserInfo d = new UserInfo("王六",0);
        List<UserInfo> list = Arrays.asList(a,b,c,d);

        // 需求: 按性别分组存入不同的集合

        // 传统写法
        Map<Integer,List<UserInfo>> result1 = new HashMap<>();
        for (UserInfo userInfo : list) {
            Integer key = userInfo.getGender();
            if (!result1.containsKey(key)){
                result1.put(key,new ArrayList<>());
            }
            result1.get(key).add(userInfo);
        }
        result1.forEach((k,v)->{
            System.out.println("group:" +k);
            v.forEach(GroupExample::printUserName);
        });

        // lambda写法
        Map<Integer,List<UserInfo>> result2 = list.stream().collect(Collectors.groupingBy((UserInfo::getGender)));
        result2.forEach((k,v)->{
            System.out.println("group:" +k);
            v.forEach(GroupExample::printUserName);
        });
    }

    // 定义一个静态方法用于打印UserInfo对象信息
    public static void printUserName(UserInfo userInfo) {
        System.out.println(userInfo.getName());
    }
}
