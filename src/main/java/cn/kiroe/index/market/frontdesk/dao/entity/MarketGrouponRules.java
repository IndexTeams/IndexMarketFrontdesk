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
 * 团购规则表
 * </p>
 *
 * @author kiro
 * @since 2023-12-29 20:15:32
 */
@Getter
@Setter
@TableName("market_groupon_rules")
@Schema (name ="MarketGrouponRules对象", description = "团购规则表")
public class MarketGrouponRules extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Schema (description ="商品表的商品ID")
    @TableField("goods_id")
    private Integer goodsId;

    @Schema (description ="商品名称")
    @TableField("goods_name")
    private String goodsName;

    @Schema (description ="商品图片或者商品货品图片")
    @TableField("pic_url")
    private String picUrl;

    @Schema (description ="优惠金额")
    @TableField("discount")
    private BigDecimal discount;

    @Schema (description ="达到优惠条件的人数")
    @TableField("discount_member")
    private Integer discountMember;

    @Schema (description ="团购过期时间")
    @TableField("expire_time")
    private LocalDateTime expireTime;

    @Schema (description ="团购规则状态，正常上线则0，到期自动下线则1，管理手动下线则2")
    @TableField("status")
    private Integer status;
}
