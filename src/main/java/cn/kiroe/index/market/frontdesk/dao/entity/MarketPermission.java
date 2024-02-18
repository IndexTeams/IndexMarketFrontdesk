package cn.kiroe.index.market.frontdesk.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author kiro
 * @since 2023-12-29 20:15:32
 */
@Getter
@Setter
@TableName("market_permission")
@Schema (name ="MarketPermission对象", description = "权限表")
public class MarketPermission extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Schema (description ="角色ID")
    @TableField("role_id")
    private Integer roleId;

    @Schema (description ="权限")
    @TableField("permission")
    private String permission;
}
