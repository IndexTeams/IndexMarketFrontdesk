package cn.kiroe.index.market.frontdesk.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * <p>
 * 订单商品表
 * </p>
 *
 * @author kiro
 * @since 2023-12-29 20:15:32
 */
@Getter
@Setter
@TableName("market_order_goods")
@Schema (name ="MarketOrderGoods对象", description = "订单商品表")
public class MarketOrderGoods extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Schema (description ="订单表的订单ID")
    @TableField("order_id")
    private Integer orderId;

    @Schema (description ="商品表的商品ID")
    @TableField("goods_id")
    private Integer goodsId;

    @Schema (description ="商品名称")
    @TableField("goods_name")
    private String goodsName;

    @Schema (description ="商品编号")
    @TableField("goods_sn")
    private String goodsSn;

    @Schema (description ="商品货品表的货品ID")
    @TableField("product_id")
    private Integer productId;

    @Schema (description ="商品货品的购买数量")
    @TableField("number")
    private Integer number;

    @Schema (description ="商品货品的售价")
    @TableField("price")
    private BigDecimal price;

    @Schema (description ="商品货品的规格列表")
    @TableField("specifications")
    private String[] specifications;

    @Schema (description ="商品货品图片或者商品图片")
    @TableField("pic_url")
    private String picUrl;

    @Schema (description ="订单商品评论，如果是-1，则超期不能评价；如果是0，则可以评价；如果其他值，则是comment表里面的评论ID。")
    @TableField("comment")
    private Integer comment;
}
