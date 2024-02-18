package cn.kiroe.index.market.frontdesk.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 搜索历史表
 * </p>
 *
 * @author kiro
 * @since 2023-12-29 20:15:32
 */
@Getter
@Setter
@TableName("market_search_history")
@Schema (name ="MarketSearchHistory对象", description = "搜索历史表")
public class MarketSearchHistory extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Schema (description ="用户表的用户ID")
    @TableField("user_id")
    private Integer userId;

    @Schema (description ="搜索关键字")
    @TableField("keyword")
    private String keyword;

    @Schema (description ="搜索来源，如pc、wx、app")
    @TableField("`from`")
    private String from;
}
