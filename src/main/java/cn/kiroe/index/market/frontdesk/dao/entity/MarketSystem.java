package cn.kiroe.index.market.frontdesk.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 系统配置表
 * </p>
 *
 * @author kiro
 * @since 2023-12-29 20:15:32
 */
@Getter
@Setter
@TableName("market_system")
@Schema (name ="MarketSystem对象", description = "系统配置表")
public class MarketSystem extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Schema (description ="系统配置名")
    @TableField("key_name")
    private String keyName;

    @Schema (description ="系统配置值")
    @TableField("key_value")
    private String keyValue;
}
