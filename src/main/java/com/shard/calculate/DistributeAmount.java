package com.shard.calculate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author chen_jinglong
 * @description: 金额分摊
 * @date 2023/8/11 14:33
 */
public class DistributeAmount {
    public static void main(String[] args) {
        // 生成3个商品放入List
        Goods goods1 = new Goods(1,"商品1",new BigDecimal("3000"),new BigDecimal("0.00"));
//        Goods goods2 = new Goods(2,"商品2",new BigDecimal("5"),new BigDecimal("0.00"));
//        Goods goods3 = new Goods(3,"商品3",new BigDecimal("0.01"),new BigDecimal("0.00"));

        List<Goods> goodsList = Arrays.asList(goods1);
        for (Goods goods : goodsList) {
            System.out.println(goods.toString());
        }
//        DistributeResult result = distributeDiscount(goodsList,new BigDecimal("99"));
//        System.out.println("总共使用的折扣金额："+result.getSumDistributeAmount());
//        result.getGoodsList().forEach(System.out::println);
//        DistributeResult result2 = distributeDiscountMustPay(goodsList,new BigDecimal("100"));
//        System.out.println("总共使用的折扣金额："+result2.getSumDistributeAmount());
//        result2.getGoodsList().forEach(System.out::println);
//        DistributeResult result = calculateDistribution(goodsList,new BigDecimal("0.5"));
//        System.out.println("总共使用的折扣金额："+result.getSumDistributeAmount());
//        result.getGoodsList().forEach(System.out::println);
        setDistribution(goodsList,new BigDecimal("0.01"));
        goodsList.forEach(System.out::println);

    }

    /**
     * 正常的金额分摊
     * @param goodsList
     * @param discountAmount
     * @return
     */
    public static DistributeResult distributeDiscount(List<Goods> goodsList, BigDecimal discountAmount){
        DistributeResult result = new DistributeResult();
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (Goods goods : goodsList) {
            totalAmount = totalAmount.add(goods.getPayAmount());
        }

        if (discountAmount.compareTo(totalAmount) > 0){
            // 正常业务场景下，抵扣金额不会大于总金额，如果大于总金额，按总金额处理
            discountAmount = totalAmount;
        }

        List<Goods> distributedAmounts = new ArrayList<>();
        for (Goods goods : goodsList) {
            // 商品的权重 = 商品的实付金额 / 总金额
            BigDecimal goodsWeight = goods.getPayAmount().divide(totalAmount, 10, BigDecimal.ROUND_DOWN);
            // 分摊金额 = 折扣金额 * 商品的权重
            BigDecimal distributedAmount = discountAmount.multiply(goodsWeight);
            Goods temp = new Goods();
            temp.setGoodsId(goods.getGoodsId());
            temp.setGoodsName(goods.getGoodsName());
            temp.setDistributeAmount(distributedAmount);
            // 实付金额 = 实付金额 - 折扣金额
            temp.setPayAmount(goods.getPayAmount().subtract(distributedAmount));
            distributedAmounts.add(temp);
        }

        // 处理分摊金额没有用尽的情况，累加到最后一个商品
        BigDecimal stepCalAmount = distributedAmounts.stream().map(item->item.getDistributeAmount()).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal remainingAmount = discountAmount.subtract(stepCalAmount);
        Goods lastItem = distributedAmounts.get(distributedAmounts.size() - 1);
        lastItem.setDistributeAmount(lastItem.getDistributeAmount().add(remainingAmount));

        // 组装返回结果
        result.setGoodsList(distributedAmounts);
        BigDecimal sumDistributedAmount = distributedAmounts.stream().map(o -> o.getDistributeAmount()).reduce(BigDecimal.ZERO, BigDecimal::add);
        result.setSumDistributeAmount(sumDistributedAmount);
        return result;

    }

    /**
     * 最低必须实付1分钱的订单
     * @param goodsList
     * @param discountAmount
     * @return
     */
    public static DistributeResult distributeDiscountMustPay(List<Goods> goodsList, BigDecimal discountAmount) {
        DistributeResult result = new DistributeResult();
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (Goods goods : goodsList) {
            totalAmount = totalAmount.add(goods.getPayAmount());
        }

        if (discountAmount.compareTo(totalAmount) > 0){
            // 正常业务场景下，抵扣金额不会大于总金额，如果大于总金额，按总金额处理
            discountAmount = totalAmount;
        }

        List<Goods> distributedAmounts = new ArrayList<>();
        for (Goods goods : goodsList) {
            // 商品的权重 = 商品的实付金额 / 总金额
            BigDecimal goodsWeight = goods.getPayAmount().divide(totalAmount, 10, BigDecimal.ROUND_DOWN);
            // 分摊金额 = 折扣金额 * 商品的权重
            BigDecimal distributedAmount = goodsWeight.multiply(discountAmount);
            Goods temp = new Goods();
            temp.setGoodsId(goods.getGoodsId());
            temp.setGoodsName(goods.getGoodsName());
            temp.setDistributeAmount(distributedAmount);
            // 实付金额 = 实付金额 - 折扣金额
            temp.setPayAmount(goods.getPayAmount().subtract(distributedAmount));
            distributedAmounts.add(temp);
        }

        // 处理分摊金额除不尽的情况，累加到最后一个商品
        BigDecimal stepCalAmount = distributedAmounts.stream().map(item -> item.getDistributeAmount()).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal remainingAmount = discountAmount.subtract(stepCalAmount);
        Goods lastItem = distributedAmounts.get(distributedAmounts.size() - 1);
        lastItem.setDistributeAmount(lastItem.getDistributeAmount().add(remainingAmount));

        // 当抵扣总金额大于或等于商品的总实付金额时，调整为最小总实付0.01元，并相应减少抵扣金额
        if (discountAmount.compareTo(totalAmount) >= 0) {
            System.out.println("满足全额抵扣条件，设置最后一件商品实付0.01");
            BigDecimal minPayAmount = new BigDecimal("0.01");
            BigDecimal adjustment = lastItem.getDistributeAmount().subtract(minPayAmount);
            lastItem.setPayAmount(minPayAmount);
            lastItem.setDistributeAmount(adjustment);
        }

        // 组装返回结果
        result.setGoodsList(distributedAmounts);
        BigDecimal sumDistributedAmount = distributedAmounts.stream().map(o -> o.getDistributeAmount()).reduce(BigDecimal.ZERO, BigDecimal::add);
        result.setSumDistributeAmount(sumDistributedAmount);

        return result;
    }

    /**
     * 出现任意分摊金额小于0.01元时，将抵扣金额赋值给最后一个商品
     * @param goodsList
     * @param deductionAmount
     * @return
     */
    public static DistributeResult calculateDistribution(List<Goods> goodsList, BigDecimal deductionAmount) {
        DistributeResult result = new DistributeResult();

        BigDecimal totalPayAmount = BigDecimal.ZERO;

        // 计算总实付金额
        for (Goods goods : goodsList) {
            totalPayAmount = totalPayAmount.add(goods.getPayAmount());
        }
        System.out.println("总实付金额：" + totalPayAmount.doubleValue());

        // 检查抵扣金额是否小于0.1
        if (deductionAmount.compareTo(new BigDecimal("0.1")) < 0) {
            Goods lastGoods = goodsList.get(goodsList.size() - 1);
            lastGoods.setDistributeAmount(deductionAmount);
            result.setGoodsList(goodsList);
            result.setSumDistributeAmount(deductionAmount);
            System.out.println("抵扣金额小于0.1，直接将抵扣金额赋值给最后一个商品");
            return result;
        }

        BigDecimal remainingDeduction = deductionAmount;

        for (int i = 0; i < goodsList.size(); i++) {
            Goods goods = goodsList.get(i);
            BigDecimal ratio = goods.getPayAmount().divide(totalPayAmount, 10, BigDecimal.ROUND_HALF_EVEN);
            BigDecimal distribute = ratio.multiply(deductionAmount);

            // 检查分摊金额是否小于0.01,true=>将前面的商品分摊金额清零，将剩余的抵扣金额赋值给最后一个商品
            if (distribute.compareTo(new BigDecimal("0.01")) < 0) {
                for (int j = 0; j <= i; j++) {
                    goodsList.get(j).setDistributeAmount(BigDecimal.ZERO);
                }

                Goods lastGoods = goodsList.get(goodsList.size() - 1);
                lastGoods.setDistributeAmount(deductionAmount);
                result.setGoodsList(goodsList);
                result.setSumDistributeAmount(deductionAmount);
                System.out.println("商品" + goods.getGoodsId() + "分摊金额:"+distribute.doubleValue()+"小于0.01，直接将抵扣金额赋值给最后一个商品");
                return result;
            }

            goods.setDistributeAmount(distribute);
            remainingDeduction = remainingDeduction.subtract(distribute);
            System.out.println("商品" + goods.getGoodsId() + "分摊金额：" + distribute.doubleValue());
        }

        // 将剩余的抵扣金额赋值给最后一个商品
        // 处理分摊金额除不尽的情况，累加到最后一个商品
        BigDecimal stepCalAmount = goodsList.stream().map(item -> item.getDistributeAmount()).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal remainingAmount = deductionAmount.subtract(stepCalAmount);
        Goods lastItem = goodsList.get(goodsList.size() - 1);
        lastItem.setDistributeAmount(lastItem.getDistributeAmount().add(remainingAmount));

        result.setGoodsList(goodsList);
        BigDecimal sumDistributedAmount = goodsList.stream().map(o -> o.getDistributeAmount()).reduce(BigDecimal.ZERO, BigDecimal::add);
        result.setSumDistributeAmount(sumDistributedAmount);
        System.out.println("正常分摊完成");
        return result;
    }

    /**
     * 出现任意分摊金额小于0.01元时，将抵扣金额赋值给最后一个商品
     * @param goodsList
     * @param deductionAmount
     * @return
     */
    public static List<Goods> setDistribution(List<Goods> goodsList, BigDecimal deductionAmount) {
        BigDecimal totalPayAmount = BigDecimal.ZERO;

        // 计算总实付金额
        for (Goods goods : goodsList) {
            totalPayAmount = totalPayAmount.add(goods.getPayAmount());
        }
        System.out.println("总实付金额：" + totalPayAmount.doubleValue());

        goodsList.sort((goods1,goods2)-> goods1.getPayAmount().compareTo(goods2.getPayAmount()));

        // 检查抵扣金额是否小于0.1
        if (deductionAmount.compareTo(new BigDecimal("0.1")) < 0) {
            Goods lastGoods = goodsList.get(goodsList.size() - 1);
            lastGoods.setDistributeAmount(deductionAmount);
            lastGoods.setPayAmount(lastGoods.getPayAmount().subtract(deductionAmount));
            System.out.println("抵扣金额小于0.1，直接将抵扣金额赋值给最后一个商品");
            return goodsList;
        }

        BigDecimal remainingDeduction = deductionAmount;

        for (int i = 0; i < goodsList.size(); i++) {
            Goods goods = goodsList.get(i);
            BigDecimal ratio = goods.getPayAmount().divide(totalPayAmount, 10, BigDecimal.ROUND_HALF_EVEN);
            BigDecimal distribute = ratio.multiply(deductionAmount);

            // 检查分摊金额是否小于0.01,true=>将前面的商品分摊金额清零，将剩余的抵扣金额赋值给最后一个商品
            if (distribute.compareTo(new BigDecimal("0.01")) < 0) {
                for (int j = 0; j <= i; j++) {
                    goodsList.get(j).setPayAmount(goodsList.get(j).getPayAmount().add(goodsList.get(j).getDistributeAmount()));
                    goodsList.get(j).setDistributeAmount(BigDecimal.ZERO);
                }

                Goods lastGoods = goodsList.get(goodsList.size() - 1);
                lastGoods.setDistributeAmount(deductionAmount);
                lastGoods.setPayAmount(lastGoods.getPayAmount().subtract(deductionAmount));
                System.out.println("商品" + goods.getGoodsId() + "分摊金额:"+distribute.doubleValue()+"小于0.01，直接将抵扣金额赋值给最后一个商品");
                return goodsList;
            }

            goods.setDistributeAmount(distribute);
            goods.setPayAmount(goods.getPayAmount().subtract(distribute));
            remainingDeduction = remainingDeduction.subtract(distribute);
            System.out.println("商品" + goods.getGoodsId() + "分摊金额：" + distribute.doubleValue());
        }

        // 将剩余的抵扣金额赋值给最后一个商品
        // 处理分摊金额除不尽的情况，累加到最后一个商品
        BigDecimal stepCalAmount = goodsList.stream().map(item -> item.getDistributeAmount()).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal remainingAmount = deductionAmount.subtract(stepCalAmount);
        Goods lastItem = goodsList.get(goodsList.size() - 1);
        lastItem.setDistributeAmount(lastItem.getDistributeAmount().add(remainingAmount));
        lastItem.setPayAmount(lastItem.getPayAmount().subtract(remainingAmount));
        BigDecimal sumDistributedAmount = goodsList.stream().map(o -> o.getDistributeAmount()).reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("正常分摊完成,总共使用分摊金额"+sumDistributedAmount+"元");
        return goodsList;
    }
}
