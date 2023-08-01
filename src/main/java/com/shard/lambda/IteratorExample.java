package com.shard.lambda;

import java.util.Arrays;
import java.util.List;

/**
 * @author chen_jinglong
 * @description: 遍历
 * @date 2023/8/1 14:50
 */
public class IteratorExample {

    public static void main(String[] args) {

        // 先定义一个List, 注意此方法声明的列表不可变，不能进行添加或删除操作
        List<Integer> numList = Arrays.asList(213,34,1,213);

        // 不用lambda方式的遍历
        for (Integer integer : numList) {
            System.out.println(integer);
        }

        // 用lambda方式的遍历
        numList.forEach(System.out::println);

        // lambda完整调用代码。 o代表numList中的一个元素 -> {} 是lambda的标准用法
        numList.forEach(o -> {
            System.out.println(o);
        });
        // 当lambda调用的方法参数和传参一致时，就可以用上方一行的简易写法



    }

}
