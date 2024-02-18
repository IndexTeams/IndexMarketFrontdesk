package cn.kiroe.index.market.frontdesk.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 优惠券信息及规则表
 * </p>
 *
 * @author kiro
 * @since 2023-12-29 20:15:32
 */
@Getter
@Setter
@TableName("market_coupon")
@Schema (name ="MarketCoupon对象", description = "优惠券信息及规则表")
public class MarketCoupon extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Schema (description ="优惠券名称")
    @TableField("name")
    private String name;

    @Schema (description ="优惠券介绍，通常是显示优惠券使用限制文字")
    @TableField("`desc`")
    private String desc;

    @Schema (description ="优惠券标签，例如新人专用")
    @TableField("tag")
    private String tag;

    @Schema (description ="优惠券数量，如果是0，则是无限量")
    @TableField("total")
    private Integer total;

    @Schema (description ="优惠金额，")
    @TableField("discount")
    private BigDecimal discount;

    @Schema (description ="最少消费金额才能使用优惠券。")
    @TableField("min")
    private BigDecimal min;

    @Schema (description ="用户领券限制数量，如果是0，则是不限制；默认是1，限领一张.")
    @TableField("`limit`")
    private Integer limit;

    @Schema (description ="优惠券赠送类型，如果是0则通用券，用户领取；如果是1，则是注册赠券；如果是2，则是优惠券码兑换；")
    @TableField("type")
    private Integer type;

    @Schema (description ="优惠券状态，如果是0则是正常可用；如果是1则是过期; 如果是2则是下架。")
    @TableField("status")
    private Integer status;

    @Schema (description ="商品限制类型，如果0则全商品，如果是1则是类目限制，如果是2则是商品限制。")
    @TableField("goods_type")
    private Integer goodsType;

    @Schema (description ="商品限制值，goods_type如果是0则空集合，如果是1则是类目集合，如果是2则是商品集合。")
    @TableField("goods_value")
    private String goodsValue;

    @Schema (description ="优惠券兑换码")
    @TableField("code")
    private String code;

    @Schema (description ="有效时间限制，如果是0，则基于领取时间的有效天数days；如果是1，则start_time和end_time是优惠券有效期；")
    @TableField("time_type")
    private Integer timeType;

    @Schema (description ="基于领取时间的有效天数days。")
    @TableField("days")
    private Integer days;

    @Schema (description ="使用券开始时间")
    @TableField("start_time")
    private LocalDateTime startTime;

    @Schema (description ="使用券截至时间")
    @TableField("end_time")
    private LocalDateTime endTime;
}
