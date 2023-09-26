package com.shard.comparator;

import lombok.Data;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * @author chen_jinglong
 * @description: 金额排序示例
 * @date 2023/8/18 16:54
 */

public class GoodsSortExample {

    public static void main(String[] args) {
        // 模拟商品信息
        GoodsDTO goods1 = new GoodsDTO(1, "商品1", new BigDecimal("10.79"), 1, LocalDateTime.now());
        GoodsDTO goods2 = new GoodsDTO(2, "商品2", new BigDecimal("5.66"), 4, LocalDateTime.now().minus(2, ChronoUnit.DAYS));
        GoodsDTO goods3 = new GoodsDTO(3, "商品3", new BigDecimal("9.99"), 2, LocalDateTime.now().minus(4, ChronoUnit.DAYS));
        GoodsDTO goods4 = new GoodsDTO(4, "商品4", new BigDecimal("8.70"), 5, LocalDateTime.now().minus(1, ChronoUnit.DAYS));
        GoodsDTO goods5 = new GoodsDTO(5, "商品5", new BigDecimal("5.66"), 4, LocalDateTime.now().minus(3, ChronoUnit.DAYS));
        List<GoodsDTO> goodsDTOS = Arrays.asList(goods1, goods2, goods3, goods4, goods5);
        System.out.println("初始顺序：");
        goodsDTOS.forEach(goodsDTO -> System.out.println(goodsDTO.toString()));


        // 假设这是从配置中读取到业务配置的商品ID列表
        List<Integer> configIds = Arrays.asList(4,3,5,1,2);
        // 定义排序规则
        Comparator<GoodsDTO> customComparator = Comparator.comparingInt(o ->configIds.indexOf(o.getGoodsId()));
        // 使用自定义排序规则进行排序
        Collections.sort(goodsDTOS, customComparator);
        System.out.println("使用自定义排序规则进行排序：");
        goodsDTOS.forEach(goodsDTO -> System.out.println(goodsDTO.toString()));

        /*
         * 解释一下这段代码的意思
         * 1.首先，我们定义了一个List<Integer>，用于存放商品ID列表，这个列表是业务按他预想的展示规则配置的商品ID
         * 2.然后我们通过创建一个自定义的排序规则，这一行函数式接口的含义如下：
         *   o ->configIds.indexOf(o.getGoodsId()) 获取商品ID在configIds列表中的索引位置
         *   Comparator.comparingInt(索引位置) 表明按照索引位置进行排序
         * 3.使用Collections.sort()方法对goodsDTOS进行排序。
         */

    }

}
