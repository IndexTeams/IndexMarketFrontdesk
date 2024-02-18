package cn.kiroe.index.market.frontdesk.dao.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author ZDX
 * @Date 2024/01/02 22:25
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "订单基本视图类")
public class OrderVo {

    @JsonProperty("orderId")
    @Schema(name = "订单ID")
    private Integer orderId;
}
