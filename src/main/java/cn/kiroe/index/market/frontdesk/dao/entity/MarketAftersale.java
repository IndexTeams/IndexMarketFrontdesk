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
 * 售后表
 * </p>
 *
 * @author kiro
 * @since 2023-12-29 20:15:32
 */
@Getter
@Setter
@TableName("market_aftersale")
@Schema (name ="MarketAftersale对象", description = "售后表")
public class MarketAftersale extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Schema (description ="售后编号")
    @TableField("aftersale_sn")
    private String aftersaleSn;

    @Schema (description ="订单ID")
    @TableField("order_id")
    private Integer orderId;

    @Schema (description ="用户ID")
    @TableField("user_id")
    private Integer userId;

    @Schema (description ="售后类型，0是未收货退款，1是已收货（无需退货）退款，2用户退货退款")
    @TableField("type")
    private Integer type;

    @Schema (description ="退款原因")
    @TableField("reason")
    private String reason;

    @Schema (description ="退款金额")
    @TableField("amount")
    private BigDecimal amount;

    @Schema (description ="退款凭证图片链接数组")
    @TableField("pictures")
    private String pictures;

    @Schema (description ="退款说明")
    @TableField("comment")
    private String comment;

    @Schema (description ="售后状态，0是可申请，1是用户已申请，2是管理员审核通过，3是管理员退款成功，4是管理员审核拒绝，5是用户已取消")
    @TableField("status")
    private Integer status;

    @Schema (description ="管理员操作时间")
    @TableField("handle_time")
    private LocalDateTime handleTime;
}
