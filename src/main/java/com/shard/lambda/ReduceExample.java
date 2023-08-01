package com.shard.lambda;

import java.util.Arrays;
import java.util.List;

/**
 * @author chen_jinglong
 * @description: 聚合操作
 * @date 2023/8/1 16:30
 */
public class ReduceExample {

    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // 需求：求list中所有元素的和

        // 传统写法
        int sum = 0;
        for (Integer integer : list) {
            sum += integer;
        }
        System.out.println(sum);

        // lambda写法1
        Integer integer = list.stream().reduce(0, Integer::sum);
        System.out.println(integer);
        // lambda写法2 第一个参数为初始值, 后边的参数是每次循环计算得出的结果和下一个元素
        Integer integer2 = list.stream().reduce(0, (currentElement, nextElement) -> currentElement + nextElement);
        System.out.println(integer2);
        // lambda写法3 .sum()求和
        Integer integer3 = list.stream().mapToInt(Integer::intValue).sum();
        System.out.println(integer3);

    }
}
