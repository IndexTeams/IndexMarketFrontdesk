package cn.kiroe.index.market.frontdesk.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 操作日志表
 * </p>
 *
 * @author kiro
 * @since 2023-12-29 20:15:32
 */
@Getter
@Setter
@TableName("market_log")
@Schema (name ="MarketLog对象", description = "操作日志表")
public class MarketLog extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Schema (description ="管理员")
    @TableField("admin")
    private String admin;

    @Schema (description ="管理员地址")
    @TableField("ip")
    private String ip;

    @Schema (description ="操作分类")
    @TableField("type")
    private Integer type;

    @Schema (description ="操作动作")
    @TableField("action")
    private String action;

    @Schema (description ="操作状态")
    @TableField("status")
    private Boolean status;

    @Schema (description ="操作结果，或者成功消息，或者失败消息")
    @TableField("result")
    private String result;

    @Schema (description ="补充信息")
    @TableField("comment")
    private String comment;
}
