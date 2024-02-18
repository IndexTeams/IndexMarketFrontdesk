package cn.kiroe.index.market.frontdesk.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * <p>
 * 购物车商品表
 * </p>
 *
 * @author kiro
 * @since 2023-12-29 20:15:32
 */
@Getter
@Setter
@TableName("market_cart")
@Schema (name ="MarketCart对象", description = "购物车商品表")
public class MarketCart extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Schema (description ="用户表的用户ID")
    @TableField("user_id")
    private Integer userId;

    @Schema (description ="商品表的商品ID")
    @TableField("goods_id")
    private Integer goodsId;

    @Schema (description ="商品编号")
    @TableField("goods_sn")
    private String goodsSn;

    @Schema (description ="商品名称")
    @TableField("goods_name")
    private String goodsName;

    @Schema (description ="商品货品表的货品ID")
    @TableField("product_id")
    private Integer productId;

    @Schema (description ="商品货品的价格")
    @TableField("price")
    private BigDecimal price;

    @Schema (description ="商品货品的数量")
    @TableField("number")
    private Integer number;

    @Schema (description ="商品规格值列表，采用JSON数组格式")
    @TableField("specifications")
    private String specifications;

    @Schema (description ="购物车中商品是否选择状态")
    @TableField("checked")
    private Boolean checked;

    @Schema (description ="商品图片或者商品货品图片")
    @TableField("pic_url")
    private String picUrl;
}
