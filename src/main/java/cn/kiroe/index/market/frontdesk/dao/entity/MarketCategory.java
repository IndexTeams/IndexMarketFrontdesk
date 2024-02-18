package cn.kiroe.index.market.frontdesk.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 类目表
 * </p>
 *
 * @author kiro
 * @since 2023-12-29 20:15:32
 */
@Getter
@Setter
@TableName("market_category")
@Schema (name ="MarketCategory对象", description = "类目表")
@ToString
public class MarketCategory extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Schema (description ="类目名称")
    @TableField("name")
    private String name;

    @Schema (description ="类目关键字，以JSON数组格式")
    @TableField("keywords")
    private String[] keywords;

    @Schema (description ="类目广告语介绍")
    @TableField("`desc`")
    private String desc;

    @Schema (description ="父类目ID")
    @TableField("pid")
    private Integer pid;

    @Schema (description ="类目图标")
    @TableField("icon_url")
    private String iconUrl;

    @Schema (description ="类目图片")
    @TableField("pic_url")
    private String picUrl;

    @TableField("level")
    private String level;

    @Schema (description ="排序")
    @TableField("sort_order")
    private Byte sortOrder;
}
