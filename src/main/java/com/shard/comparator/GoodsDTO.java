package com.shard.comparator;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author chen_jinglong
 * @description: 商品信息
 * @date 2023/8/18 16:56
 */

@Data
public class GoodsDTO {

    /**
     * 商品ID
     */
    private Integer goodsId;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品金额
     */
    private BigDecimal goodsAmount;

    /**
     * 商品排序值
     */
    private Integer sort;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


    public GoodsDTO(Integer goodsId, String goodsName, BigDecimal goodsAmount) {
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.goodsAmount = goodsAmount;
    }

    public GoodsDTO(Integer goodsId, String goodsName, BigDecimal goodsAmount, Integer sort, LocalDateTime createTime) {
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.goodsAmount = goodsAmount;
        this.sort = sort;
        this.createTime = createTime;
    }
}
