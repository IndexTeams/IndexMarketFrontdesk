package cn.kiroe.index.market.frontdesk.dao.vo;

import cn.kiroe.index.market.frontdesk.dao.entity.MarketOrderGoods;
import cn.kiroe.index.market.frontdesk.util.OrderHandleOption;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;

/**
 * ClassName: OrderListVo
 * Package: cn.kiroe.index.market.frontdesk.dao.vo
 * Description:
 *
 * @Author: 李家合
 * @Create: 2024/1/3 23:05
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class OrderListVo {

    @JsonProperty("orderStatusText")
    private String orderStatusText;
    @JsonProperty("aftersaleStatus")
    private Integer aftersaleStatus;
    @JsonProperty("isGroupin")
    private Boolean isGroupin = false;
    @JsonProperty("orderSn")
    private String orderSn;
    @JsonProperty("actualPrice")
    private BigDecimal actualPrice;
    @JsonProperty("goodsList")
    private List<MarketOrderGoods> goodsList;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("handleOption")
    private OrderHandleOption handleOption;


}
