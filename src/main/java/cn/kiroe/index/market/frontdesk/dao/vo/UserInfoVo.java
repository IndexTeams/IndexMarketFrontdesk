package cn.kiroe.index.market.frontdesk.dao.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * ClassName: UserInfoVo
 * Package: cn.kiroe.index.market.frontdesk.dao.vo
 * Description:
 *
 * @Author: 李家合
 * @Create: 2024/1/3 20:23
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class UserInfoVo {
    private String nickName;
    private String avatarUrl;
}
