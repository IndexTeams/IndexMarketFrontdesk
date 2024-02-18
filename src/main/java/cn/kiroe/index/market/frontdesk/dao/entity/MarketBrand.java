package cn.kiroe.index.market.frontdesk.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * <p>
 * 品牌商表
 * </p>
 *
 * @author kiro
 * @since 2023-12-29 20:15:32
 */
@Getter
@Setter
@TableName("market_brand")
@Schema (name ="MarketBrand对象", description = "品牌商表")
public class MarketBrand extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Schema (description ="品牌商名称")
    @TableField("name")
    private String name;

    @Schema (description ="品牌商简介")
    @TableField("`desc`")
    private String desc;

    @Schema (description ="品牌商页的品牌商图片")
    @TableField("pic_url")
    private String picUrl;

    @TableField("sort_order")
    private Byte sortOrder;

    @Schema (description ="品牌商的商品低价，仅用于页面展示")
    @TableField("floor_price")
    private BigDecimal floorPrice;
}
