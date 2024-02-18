package cn.kiroe.index.market.frontdesk.dao.vo;

import cn.kiroe.index.market.frontdesk.dao.vo.common.BasePageInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * ClassName: CommentVo
 * Package: cn.kiroe.index.market.frontdesk.dao.vo
 * Description:
 *
 * @Author: 李家合
 * @Create: 2024/1/2 20:38
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Builder
public class CommentVo extends BasePageInfo {
    private Integer valueId;
    private Byte type;
    private Integer showType;
}
