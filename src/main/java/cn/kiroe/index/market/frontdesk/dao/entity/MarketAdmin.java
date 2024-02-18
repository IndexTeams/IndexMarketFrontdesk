package cn.kiroe.index.market.frontdesk.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * <p>
 * 管理员表
 * </p>
 *
 * @author kiro
 * @since 2023-12-29 20:15:32
 */
@Getter
@Setter
@TableName("market_admin")
@Schema (name ="MarketAdmin对象", description = "管理员表")
public class MarketAdmin extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Schema (description ="管理员名称")
    @TableField("username")
    private String username;

    @Schema (description ="管理员密码")
    @TableField("password")
    private String password;

    @Schema (description ="最近一次登录IP地址")
    @TableField("last_login_ip")
    private String lastLoginIp;

    @Schema (description ="最近一次登录时间")
    @TableField("last_login_time")
    private LocalDateTime lastLoginTime;

    @Schema (description ="头像图片")
    @TableField("avatar")
    private String avatar;

    @Schema (description ="角色列表")
    @TableField("role_ids")
    private String roleIds;
}
