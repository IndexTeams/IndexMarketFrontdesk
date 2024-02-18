package cn.kiroe.index.market.frontdesk.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 意见反馈表
 * </p>
 *
 * @author kiro
 * @since 2023-12-29 20:15:32
 */
@Getter
@Setter
@TableName("market_feedback")
@Schema (name ="MarketFeedback对象", description = "意见反馈表")
public class MarketFeedback extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Schema (description ="用户表的用户ID")
    @TableField("user_id")
    private Integer userId;

    @Schema (description ="用户名称")
    @TableField("username")
    private String username;

    @Schema (description ="手机号")
    @TableField("mobile")
    private String mobile;

    @Schema (description ="反馈类型")
    @TableField("feed_type")
    private String feedType;

    @Schema (description ="反馈内容")
    @TableField("content")
    private String content;

    @Schema (description ="状态")
    @TableField("status")
    private Integer status;

    @Schema (description ="是否含有图片")
    @TableField("has_picture")
    private Boolean hasPicture;

    @Schema (description ="图片地址列表，采用JSON数组格式")
    @TableField("pic_urls")
    private String[] picUrls;
}
