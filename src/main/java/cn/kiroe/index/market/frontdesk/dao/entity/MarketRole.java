package cn.kiroe.index.market.frontdesk.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author kiro
 * @since 2023-12-29 20:15:32
 */
@Getter
@Setter
@TableName("market_role")
@Schema (name ="MarketRole对象", description = "角色表")
public class MarketRole extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Schema (description ="角色名称")
    @TableField("name")
    private String name;

    @Schema (description ="角色描述")
    @TableField("desc")
    private String desc;

    @Schema (description ="是否启用")
    @TableField("enabled")
    private Boolean enabled;
}
