package cn.kiroe.index.market.frontdesk.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * <p>
 * 广告表
 * </p>
 *
 * @author kiro
 * @since 2023-12-29 20:15:32
 */
@Getter
@Setter
@TableName("market_ad")
@Schema (name ="MarketAd对象", description = "广告表")
public class MarketAd extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Schema (description ="广告标题")
    @TableField("name")
    private String name;

    @Schema (description = "所广告的商品页面或者活动页面链接地址")
    @TableField("link")
    private String link;

    @Schema (description ="广告宣传图片")
    @TableField("url")
    private String url;

    @Schema (description ="广告位置：1则是首页")
    @TableField("position")
    private Byte position;

    @Schema (description ="活动内容")
    @TableField("content")
    private String content;

    @Schema (description ="广告开始时间")
    @TableField("start_time")
    private LocalDateTime startTime;

    @Schema (description ="广告结束时间")
    @TableField("end_time")
    private LocalDateTime endTime;

    @Schema (description ="是否启动")
    @TableField("enabled")
    private Boolean enabled;
}
