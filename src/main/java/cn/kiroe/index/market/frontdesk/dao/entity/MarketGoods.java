package cn.kiroe.index.market.frontdesk.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * <p>
 * 商品基本信息表
 * </p>
 *
 * @author kiro
 * @since 2023-12-29 20:15:32
 */
@Getter
@Setter
@TableName("market_goods")
@Schema (name ="MarketGoods对象", description = "商品基本信息表")
public class MarketGoods extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Schema(description = "商品编号")
    @TableField("goods_sn")
    private String goodsSn;

    @Schema (description ="商品名称")
    @TableField("name")
    private String name;

    @Schema (description ="商品所属类目ID")
    @TableField("category_id")
    private Integer categoryId;

    @TableField("brand_id")
    private Integer brandId;

    @Schema (description ="商品宣传图片列表，采用JSON数组格式")
    @TableField("gallery")
    private String gallery;

    @Schema (description ="商品关键字，采用逗号间隔")
    @TableField("keywords")
    private String keywords;

    @Schema (description ="商品简介")
    @TableField("brief")
    private String brief;

    @Schema (description ="是否上架")
    @TableField("is_on_sale")
    private Boolean isOnSale;

    @TableField("sort_order")
    private Integer sortOrder;

    @Schema (description ="商品页面商品图片")
    @TableField("pic_url")
    private String picUrl;

    @Schema (description ="商品分享海报")
    @TableField("share_url")
    private String shareUrl;

    @Schema (description ="是否新品首发，如果设置则可以在新品首发页面展示")
    @TableField("is_new")
    private Boolean isNew;

    @Schema (description ="是否人气推荐，如果设置则可以在人气推荐页面展示")
    @TableField("is_hot")
    private Boolean isHot;

    @Schema (description ="商品单位，例如件、盒")
    @TableField("unit")
    private String unit;

    @Schema (description ="专柜价格")
    @TableField("counter_price")
    private BigDecimal counterPrice;

    @Schema (description ="零售价格")
    @TableField("retail_price")
    private BigDecimal retailPrice;

    @Schema (description ="商品详细介绍，是富文本格式")
    @TableField("detail")
    private String detail;
}
