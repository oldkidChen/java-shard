package com.shard.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chen_jinglong
 * @description: 映射
 * @date 2023/8/1 15:36
 */
public class MapExample {

    static class UserInfo{
        private String name;

        public UserInfo(String name){
            this.name = name;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }

    }

    public static void main(String[] args) {
        UserInfo a = new UserInfo("张三");
        UserInfo b = new UserInfo("李四");
        UserInfo c = new UserInfo("王五");
        UserInfo d = new UserInfo("王六");

        List<UserInfo> list = Arrays.asList(a,b,c,d);
        
        // 需求: 取出list中用户信息的名称
        
        // 传统写法
        List<String> nameList = new ArrayList<>();
        for (UserInfo userInfo : list) {
            nameList.add(userInfo.getName());
        }
        nameList.forEach(System.out::println);

        // lambda写法
        // List<?>需要定义什么类型， map(对象::属性)就返回对应的类型值
        List<String> nameList2 = list.stream().map(UserInfo::getName).collect(Collectors.toList());
        nameList2.forEach(System.out::println);
    }

}
