package cn.kiroe.index.market.frontdesk.dao.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Kiro
 * @Date 2024/01/03 22:55
 **/
@NoArgsConstructor
@Data
public class CartUpdateParamVo {

    @JsonProperty("productId")
    private Integer productId;
    @JsonProperty("goodsId")
    private Integer goodsId;
    @JsonProperty("number")
    private Integer number;
    @JsonProperty("id")
    private Integer id;
}
