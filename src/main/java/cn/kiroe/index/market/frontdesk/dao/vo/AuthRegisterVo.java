package cn.kiroe.index.market.frontdesk.dao.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @Author ZDX
 * @Date 2024/01/05 14:40
 **/
@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class AuthRegisterVo extends AuthLoginParamVo {
    @NotNull
    // 验证码
    private String code;
    @NotNull
    private String mobile;
    // 李姐万岁 没做微信登录
    private String WxCode;
}
