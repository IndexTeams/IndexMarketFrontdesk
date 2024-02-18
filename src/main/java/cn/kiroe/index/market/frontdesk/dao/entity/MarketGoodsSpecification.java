package cn.kiroe.index.market.frontdesk.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 商品规格表
 * </p>
 *
 * @author kiro
 * @since 2023-12-29 20:15:32
 */
@Getter
@Setter
@TableName("market_goods_specification")
@Schema (name ="MarketGoodsSpecification对象", description = "商品规格表")
public class MarketGoodsSpecification extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Schema (description ="商品表的商品ID")
    @TableField("goods_id")
    private Integer goodsId;

    @Schema (description ="商品规格名称")
    @TableField("specification")
    private String specification;

    @Schema (description ="商品规格值")
    @TableField("value")
    private String value;

    @Schema (description ="商品规格图片")
    @TableField("pic_url")
    private String picUrl;
}
