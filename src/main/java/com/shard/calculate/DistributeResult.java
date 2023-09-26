package com.shard.calculate;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author chen_jinglong
 * @description: 计算分摊之后的返回结果
 * @date 2023/8/11 16:51
 */

@Data
public class DistributeResult {

    // 使用的总分摊金额
    private BigDecimal sumDistributeAmount;

    // 计算分摊之后的商品列表
    private List<Goods> goodsList;

    @Override
    public String toString(){
        return "DistributeResult{" +
                "remainingDistributeAmount=" + sumDistributeAmount.doubleValue() +
                ", goodsList=" + goodsList +
                '}';
    }
}
