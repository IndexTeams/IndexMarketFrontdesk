package cn.kiroe.index.market.frontdesk.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户浏览足迹表
 * </p>
 *
 * @author kiro
 * @since 2023-12-29 20:15:32
 */
@Getter
@Setter
@TableName("market_footprint")
@Schema (name ="MarketFootprint对象", description = "用户浏览足迹表")
public class MarketFootprint extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Schema (description ="用户表的用户ID")
    @TableField("user_id")
    private Integer userId;

    @Schema (description ="浏览商品ID")
    @TableField("goods_id")
    private Integer goodsId;
}
