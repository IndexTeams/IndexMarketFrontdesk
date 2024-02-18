package cn.kiroe.index.market.frontdesk.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author kiro
 * @since 2023-12-29 20:15:32
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("market_user")
@Schema (name ="MarketUser对象", description = "用户表")
public class MarketUser extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Schema (description ="用户名称")
    @TableField("username")
    private String username;

    @Schema (description ="用户密码")
    @TableField("password")
    private String password;

    @Schema (description ="性别：0 未知， 1男， 1 女")
    @TableField("gender")
    private Byte gender;

    @Schema (description ="生日")
    @TableField("birthday")
    private LocalDate birthday;

    @Schema (description ="最近一次登录时间")
    @TableField("last_login_time")
    private LocalDateTime lastLoginTime;

    @Schema (description ="最近一次登录IP地址")
    @TableField("last_login_ip")
    private String lastLoginIp;

    @Schema (description ="0 普通用户，1 VIP用户，2 高级VIP用户")
    @TableField("user_level")
    private Byte userLevel;

    @Schema (description ="用户昵称或网络名称")
    @TableField("nickname")
    private String nickname;

    @Schema (description ="用户手机号码")
    @TableField("mobile")
    private String mobile;

    @Schema (description ="用户头像图片")
    @TableField("avatar")
    private String avatar;

    @Schema (description ="微信登录openid")
    @TableField("weixin_openid")
    private String weixinOpenid;

    @Schema (description ="微信登录会话KEY")
    @TableField("session_key")
    private String sessionKey;

    @Schema (description ="0 可用, 1 禁用, 2 注销")
    @TableField("status")
    private Byte status;
}
