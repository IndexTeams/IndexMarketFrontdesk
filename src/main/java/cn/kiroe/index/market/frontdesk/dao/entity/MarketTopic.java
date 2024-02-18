package cn.kiroe.index.market.frontdesk.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * <p>
 * 专题表
 * </p>
 *
 * @author kiro
 * @since 2023-12-29 20:15:32
 */
@Getter
@Setter
@TableName("market_topic")
@Schema (name ="MarketTopic对象", description = "专题表")
public class MarketTopic extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Schema (description ="专题标题")
    @TableField("title")
    private String title;

    @Schema (description ="专题子标题")
    @TableField("subtitle")
    private String subtitle;

    @Schema (description ="专题内容，富文本格式")
    @TableField("content")
    private String content;

    @Schema (description ="专题相关商品最低价")
    @TableField("price")
    private BigDecimal price;

    @Schema (description ="专题阅读量")
    @TableField("read_count")
    private String readCount;

    @Schema (description ="专题图片")
    @TableField("pic_url")
    private String picUrl;

    @Schema (description ="排序")
    @TableField("sort_order")
    private Integer sortOrder;

    @Schema (description ="专题相关商品，采用JSON数组格式")
    @TableField("goods")
    private String[] goods;
}
