package cn.kiroe.index.market.frontdesk.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 优惠券用户使用表
 * </p>
 *
 * @author kiro
 * @since 2023-12-29 20:15:32
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("market_coupon_user")
@Schema (name ="MarketCouponUser对象", description = "优惠券用户使用表")
public class MarketCouponUser extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Schema (description ="用户ID")
    @TableField("user_id")
    private Integer userId;

    @Schema (description ="优惠券ID")
    @TableField("coupon_id")
    private Integer couponId;

    @Schema (description ="使用状态, 如果是0则未使用；如果是1则已使用；如果是2则已过期；如果是3则已经下架；")
    @TableField("status")
    private Integer status;

    @Schema (description ="使用时间")
    @TableField("used_time")
    private LocalDateTime usedTime;

    @Schema (description ="有效期开始时间")
    @TableField("start_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    @Schema (description ="有效期截至时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("end_time")
    private LocalDateTime endTime;

    @Schema (description ="订单ID")
    @TableField("order_id")
    private Integer orderId;



}
