package com.shard.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chen_jinglong
 * @description: 过滤
 * @date 2023/8/1 15:19
 */
public class FilterExample {

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
        // 需求: 过滤出姓王的用户

        // 传统写法
        List<UserInfo> result1 = new ArrayList<>();
        for (UserInfo userInfo : list) {
            if (userInfo.getName().startsWith("王")){
                result1.add(userInfo);
            }
        }
        result1.forEach(o-> System.out.println(o.getName()));

        // 使用lambda写法
        List<UserInfo> result2 = list.stream().filter(o->o.getName().startsWith("王")).collect(Collectors.toList());
        result2.forEach(o-> System.out.println(o.getName()));

        // 使用lambda过滤大于5的数字
        List<Integer> numList = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        List<Integer> result3 = numList.stream().filter(item-> item>5).collect(Collectors.toList());
        result3.forEach(o-> System.out.println(o));

    }

}
