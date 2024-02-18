package cn.kiroe.index.market.frontdesk.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 通知表
 * </p>
 *
 * @author kiro
 * @since 2023-12-29 20:15:32
 */
@Getter
@Setter
@TableName("market_notice")
@Schema (name ="MarketNotice对象", description = "通知表")
public class MarketNotice extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Schema (description ="通知标题")
    @TableField("title")
    private String title;

    @Schema (description ="通知内容")
    @TableField("content")
    private String content;

    @Schema (description ="创建通知的管理员ID，如果是系统内置通知则是0.")
    @TableField("admin_id")
    private Integer adminId;
}
