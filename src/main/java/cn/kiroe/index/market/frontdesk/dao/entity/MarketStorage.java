package cn.kiroe.index.market.frontdesk.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * <p>
 * 文件存储表
 * </p>
 *
 * @author kiro
 * @since 2023-12-29 20:15:32
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("market_storage")
@Schema(name = "MarketStorage对象", description = "文件存储表")
public class MarketStorage extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Schema(description = "文件的唯一索引")
    @TableField("`key`")
    // 对应MyBatisPlus中的sql拼接名
    private String key;

    @Schema(description = "文件名")
    @TableField("name")
    private String name;

    @Schema(description = "文件类型")
    @TableField("type")
    private String type;

    @Schema(description = "文件大小")
    @TableField("size")
    private Integer size;

    @Schema(description = "文件访问链接")
    @TableField("url")
    private String url;
}
