package com.shard.lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author chen_jinglong
 * @description: 排序
 * @date 2023/8/1 15:11
 */
public class SortExample {

    public static void main(String[] args) {
        // 先定义一个List, 注意此方法声明的列表不可变，不能进行添加或删除操作
        List<Integer> numList = Arrays.asList(213,34,1,213);

        // 不用lambda方式的排序
        Collections.sort(numList, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        System.out.println(numList);

        // 使用lambda方式的排序,为了看到排序变化，这里改成倒序
        Collections.sort(numList, (o1, o2) -> o2.compareTo(o1));
        System.out.println(numList);

    }

}
