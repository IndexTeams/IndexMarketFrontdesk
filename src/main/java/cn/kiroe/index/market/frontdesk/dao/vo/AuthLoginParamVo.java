package cn.kiroe.index.market.frontdesk.dao.vo;

import lombok.*;

import javax.validation.constraints.NotNull;

/**
 * @Author Kiro
 * @Date 2024/01/03 18:01
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthLoginParamVo {
    @NotNull
    String username;
    @NotNull
    String password;
}
