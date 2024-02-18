package cn.kiroe.index.market.frontdesk.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 行政区域表
 * </p>
 *
 * @author kiro
 * @since 2023-12-29 20:15:32
 */
@Getter
@Setter
@TableName("market_region")
@Schema (name ="MarketRegion对象", description = "行政区域表")
public class MarketRegion extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Schema (description ="行政区域父ID，例如区县的pid指向市，市的pid指向省，省的pid则是0")
    @TableField("pid")
    private Integer pid;

    @Schema (description ="行政区域名称")
    @TableField("name")
    private String name;

    @Schema (description ="行政区域类型，如如1则是省， 如果是2则是市，如果是3则是区县")
    @TableField("type")
    private Byte type;

    @Schema (description ="行政区域编码")
    @TableField("code")
    private Integer code;
}
