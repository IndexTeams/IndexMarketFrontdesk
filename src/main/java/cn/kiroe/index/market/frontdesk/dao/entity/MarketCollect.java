package cn.kiroe.index.market.frontdesk.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * <p>
 * 收藏表
 * </p>
 *
 * @author kiro
 * @since 2023-12-29 20:15:32
 */
@Getter
@Setter
@TableName("market_collect")
@Schema (name ="MarketCollect对象", description = "收藏表")
public class MarketCollect extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Schema (description ="用户表的用户ID")
    @TableField("user_id")
    private Integer userId;

    @Schema (description ="如果type=0，则是商品ID；如果type=1，则是专题ID")
    @TableField("value_id")
    private Integer valueId;

    @Schema (description ="收藏类型，如果type=0，则是商品ID；如果type=1，则是专题ID")
    @TableField("type")
    private Byte type;
}
