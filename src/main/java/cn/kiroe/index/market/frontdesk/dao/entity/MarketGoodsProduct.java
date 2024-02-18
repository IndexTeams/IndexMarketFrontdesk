package cn.kiroe.index.market.frontdesk.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * <p>
 * 商品货品表
 * </p>
 *
 * @author kiro
 * @since 2023-12-29 20:15:32
 */
@Getter
@Setter
@TableName("market_goods_product")
@Schema (name ="MarketGoodsProduct对象", description = "商品货品表")
public class MarketGoodsProduct extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Schema (description ="商品表的商品ID")
    @TableField("goods_id")
    private Integer goodsId;

    @Schema (description ="商品规格值列表，采用JSON数组格式")
    @TableField("specifications")
    private String specifications;

    @Schema (description ="商品货品价格")
    @TableField("price")
    private BigDecimal price;

    @Schema (description ="商品货品数量")
    @TableField("number")
    private Integer number;

    @Schema (description ="商品货品图片")
    @TableField("url")
    private String url;
}
