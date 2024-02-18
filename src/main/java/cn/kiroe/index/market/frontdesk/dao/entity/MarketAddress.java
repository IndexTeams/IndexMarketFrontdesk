package cn.kiroe.index.market.frontdesk.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 收货地址表
 * </p>
 *
 * @author kiro
 * @since 2023-12-29 20:15:32
 */
@Getter
@Setter
@TableName("market_address")
@Schema (name ="MarketAddress对象", description = "收货地址表")
public class MarketAddress extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Schema (description ="收货人名称")
    @TableField("name")
    private String name;

    @Schema (description ="用户表的用户ID")
    @TableField("user_id")
    private Integer userId;

    @Schema (description ="行政区域表的省ID")
    @TableField("province")
    private String province;

    @Schema (description ="行政区域表的市ID")
    @TableField("city")
    private String city;

    @Schema (description ="行政区域表的区县ID")
    @TableField("county")
    private String county;

    @Schema (description ="详细收货地址")
    @TableField("address_detail")
    private String addressDetail;

    @Schema (description ="地区编码")
    @TableField("area_code")
    private String areaCode;

    @Schema (description ="邮政编码")
    @TableField("postal_code")
    private String postalCode;

    @Schema (description ="手机号码")
    @TableField("tel")
    private String tel;

    @Schema (description ="是否默认地址")
    @TableField("is_default")
    private Boolean isDefault;
}
