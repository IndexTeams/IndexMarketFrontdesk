package cn.kiroe.index.market.frontdesk.dao.vo;

import cn.kiroe.index.market.frontdesk.dao.entity.MarketOrderGoods;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: OrderDetailVo
 * Package: cn.kiroe.index.market.frontdesk.dao.vo
 * Description:
 *
 * @Author: 李家合
 * @Create: 2024/1/4 16:02
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class OrderDetailVo {

    @JsonProperty("expressInfo")
    private List<?> expressInfo = new ArrayList<>(0);
    @JsonProperty("orderInfo")
    private OrderInfoVo orderInfo;
    @JsonProperty("orderGoods")
    private List<MarketOrderGoods> orderGoods;

}
