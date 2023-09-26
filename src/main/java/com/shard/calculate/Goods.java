package com.shard.calculate;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author chen_jinglong
 * @description: 商品
 * @date 2023/8/11 14:33
 */

@Data
public class Goods {

    // 商品ID
    private Integer goodsId;

    // 商品名称
    private String goodsName;

    // 实付金额
    private BigDecimal payAmount;

    // 分摊金额
    private BigDecimal distributeAmount;

    public Goods(){}
    public Goods(Integer goodsId, String goodsName, BigDecimal payAmount, BigDecimal distributeAmount) {
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.payAmount = payAmount;
        this.distributeAmount = distributeAmount;
    }

    @Override
    public String toString(){
        return "Goods{" +
                "goodsId=" + goodsId +
                ", goodsName='" + goodsName + '\'' +
                ", payAmount=" + payAmount.doubleValue() +
                ", distributeAmount=" + distributeAmount.doubleValue() +
                '}';
    }
}
