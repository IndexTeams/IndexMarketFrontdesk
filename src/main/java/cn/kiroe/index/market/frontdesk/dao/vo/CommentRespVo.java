package cn.kiroe.index.market.frontdesk.dao.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * ClassName: CommentRespVo
 * Package: cn.kiroe.index.market.frontdesk.dao.vo
 * Description:
 *
 * @Author: 李家合
 * @Create: 2024/1/2 21:04
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Builder
public class CommentRespVo {
    private UserInfoVo userInfo;
    private LocalDateTime addTime;
    private String[] picList;
    private String adminContent;
    private String content;
}
