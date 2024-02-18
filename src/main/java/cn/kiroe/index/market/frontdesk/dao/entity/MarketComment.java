package cn.kiroe.index.market.frontdesk.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonAlias;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 评论表
 * </p>
 *
 * @author kiro
 * @since 2023-12-29 20:15:32
 */
@Getter
@Setter
@TableName("market_comment")
@Schema (name ="MarketComment对象", description = "评论表")
public class MarketComment extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Schema (description ="如果type=0，则是商品评论；如果是type=1，则是专题评论。")
    @TableField("value_id")
    @JsonAlias({"orderGoodsId", "valueId"})
    private Integer valueId;

    @Schema (description ="评论类型，如果type=0，则是商品评论；如果是type=1，则是专题评论；")
    @TableField("type")
    private Byte type;

    @Schema (description ="评论内容")
    @TableField("content")
    private String content;

    @Schema (description ="管理员回复内容")
    @TableField("admin_content")
    private String adminContent;

    @Schema (description ="用户表的用户ID")
    @TableField("user_id")
    private Integer userId;

    @Schema (description ="是否含有图片")
    @TableField("has_picture")
    private Boolean hasPicture;

    @Schema (description ="图片地址列表，采用JSON数组格式")
    @TableField("pic_urls")
    private String[] picUrls;

    @Schema (description ="评分， 1-5")
    @TableField("star")
    private Integer star;
}
