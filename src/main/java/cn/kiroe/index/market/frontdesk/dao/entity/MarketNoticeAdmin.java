package cn.kiroe.index.market.frontdesk.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * <p>
 * 通知管理员表
 * </p>
 *
 * @author kiro
 * @since 2023-12-29 20:15:32
 */
@Getter
@Setter
@TableName("market_notice_admin")
@Schema (name ="MarketNoticeAdmin对象", description = "通知管理员表")
public class MarketNoticeAdmin extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Schema (description ="通知ID")
    @TableField("notice_id")
    private Integer noticeId;

    @Schema (description ="通知标题")
    @TableField("notice_title")
    private String noticeTitle;

    @Schema (description ="接收通知的管理员ID")
    @TableField("admin_id")
    private Integer adminId;

    @Schema (description ="阅读时间，如果是NULL则是未读状态")
    @TableField("read_time")
    private LocalDateTime readTime;
}
