package cn.kiroe.index.market.frontdesk.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * <p>
 * 团购活动表
 * </p>
 *
 * @author kiro
 * @since 2023-12-29 20:15:32
 */
@Getter
@Setter
@TableName("market_groupon")
@Schema (name ="MarketGroupon对象", description = "团购活动表")
public class MarketGroupon extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Schema (description ="关联的订单ID")
    @TableField("order_id")
    private Integer orderId;

    @Schema (description ="如果是开团用户，则groupon_id是0；如果是参团用户，则groupon_id是团购活动ID")
    @TableField("groupon_id")
    private Integer grouponId;

    @Schema (description ="团购规则ID，关联market_groupon_rules表ID字段")
    @TableField("rules_id")
    private Integer rulesId;

    @Schema (description ="用户ID")
    @TableField("user_id")
    private Integer userId;

    @Schema (description ="团购分享图片地址")
    @TableField("share_url")
    private String shareUrl;

    @Schema (description ="开团用户ID")
    @TableField("creator_user_id")
    private Integer creatorUserId;

    @Schema (description ="开团时间")
    @TableField("creator_user_time")
    private LocalDateTime creatorUserTime;

    @Schema (description ="团购活动状态，开团未支付则0，开团中则1，开团失败则2")
    @TableField("status")
    private Integer status;
}
