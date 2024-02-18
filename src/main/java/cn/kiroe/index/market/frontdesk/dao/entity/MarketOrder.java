package cn.kiroe.index.market.frontdesk.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author kiro
 * @since 2023-12-29 20:15:32
 */
@Getter
@Setter
@TableName("market_order")
@Schema (name ="MarketOrder对象", description = "订单表")
public class MarketOrder extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Schema (description ="用户表的用户ID")
    @TableField("user_id")
    private Integer userId;

    @Schema (description ="订单编号")
    @TableField("order_sn")
    private String orderSn;

    @Schema (description ="订单状态")
    @TableField("order_status")
    private Integer orderStatus;

    @Schema (description ="售后状态，0是可申请，1是用户已申请，2是管理员审核通过，3是管理员退款成功，4是管理员审核拒绝，5是用户已取消")
    @TableField("aftersale_status")
    private Integer aftersaleStatus;

    @Schema (description ="收货人名称")
    @TableField("consignee")
    private String consignee;

    @Schema (description ="收货人手机号")
    @TableField("mobile")
    private String mobile;

    @Schema (description ="收货具体地址")
    @TableField("address")
    private String address;

    @Schema (description ="用户订单留言")
    @TableField("message")
    private String message;

    @Schema (description ="商品总费用")
    @TableField("goods_price")
    private BigDecimal goodsPrice;

    @Schema (description ="配送费用")
    @TableField("freight_price")
    private BigDecimal freightPrice;

    @Schema (description ="优惠券减免")
    @TableField("coupon_price")
    private BigDecimal couponPrice;

    @Schema (description ="用户积分减免")
    @TableField("integral_price")
    private BigDecimal integralPrice;

    @Schema (description ="团购优惠价减免")
    @TableField("groupon_price")
    private BigDecimal grouponPrice;

    @Schema (description ="订单费用， = goods_price + freight_price - coupon_price")
    @TableField("order_price")
    private BigDecimal orderPrice;

    @Schema (description ="实付费用， = order_price - integral_price")
    @TableField("actual_price")
    private BigDecimal actualPrice;

    @Schema (description ="微信付款编号")
    @TableField("pay_id")
    private String payId;

    @Schema (description ="微信付款时间")
    @TableField("pay_time")
    private LocalDateTime payTime;

    @Schema (description ="发货编号")
    @TableField("ship_sn")
    private String shipSn;

    @Schema (description ="发货快递公司")
    @TableField("ship_channel")
    private String shipChannel;

    @Schema (description ="发货开始时间")
    @TableField("ship_time")
    private LocalDateTime shipTime;

    @Schema (description ="实际退款金额，（有可能退款金额小于实际支付金额）")
    @TableField("refund_amount")
    private BigDecimal refundAmount;

    @Schema (description ="退款方式")
    @TableField("refund_type")
    private String refundType;

    @Schema (description ="退款备注")
    @TableField("refund_content")
    private String refundContent;

    @Schema (description ="退款时间")
    @TableField("refund_time")
    private LocalDateTime refundTime;

    @Schema (description ="用户确认收货时间")
    @TableField("confirm_time")
    private LocalDateTime confirmTime;

    @Schema (description ="待评价订单商品数量")
    @TableField("comments")
    private Integer comments;

    @Schema (description ="订单关闭时间")
    @TableField("end_time")
    private LocalDateTime endTime;

    public enum OrderStatus {
        STATUS_CREATE(101, "未付款"),
        STATUS_PAY(201, "已付款"),
        STATUS_SHIP(301, "已发货"),
        STATUS_CONFIRM( 401, "用户收货"),
        STATUS_CANCEL( 102, "用户取消"),
        STATUS_AUTO_CANCEL( 103, "系统取消"),
        STATUS_ADMIN_CANCEL( 104, "系统取消"),
        STATUS_REFUND( 202, "申请退款"),
        STATUS_REFUND_CONFIRM( 203, "已退款"),
        STATUS_AUTO_CONFIRM( 402, "系统收货");

        private final Integer value;
        private final String description;

        OrderStatus(Integer value, String description) {
            this.value = value;
            this.description = description;
        }

        public Integer getValue() {
            return value;
        }

        public String getDescription() {
            return description;
        }
    }
}
