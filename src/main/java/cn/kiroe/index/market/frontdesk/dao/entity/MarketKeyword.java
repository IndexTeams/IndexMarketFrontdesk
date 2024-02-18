package cn.kiroe.index.market.frontdesk.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 关键字表
 * </p>
 *
 * @author kiro
 * @since 2023-12-29 20:15:32
 */
@Getter
@Setter
@TableName("market_keyword")
@Schema (name ="MarketKeyword对象", description = "关键字表")
public class MarketKeyword extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Schema (description ="关键字")
    @TableField("keyword")
    private String keyword;

    @Schema (description ="关键字的跳转链接")
    @TableField("url")
    private String url;

    @Schema (description ="是否是热门关键字")
    @TableField("is_hot")
    private Boolean isHot;

    @Schema (description ="是否是默认关键字")
    @TableField("is_default")
    private Boolean isDefault;

    @Schema (description ="排序")
    @TableField("sort_order")
    private Integer sortOrder;
}
