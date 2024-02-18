package cn.kiroe.index.market.frontdesk.dao.vo;

import cn.kiroe.index.market.frontdesk.dao.entity.MarketCart;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author Kiro
 * @Date 2024/01/03 21:33
 **/
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class CartIndexVo {

        @JsonProperty("cartTotal")
        private CartTotalDTO cartTotal;
        @JsonProperty("cartList")
        private List<MarketCart> cartList;

        @NoArgsConstructor
        @Data
        @AllArgsConstructor
        @Builder
        public static class CartTotalDTO {
            @JsonProperty("goodsCount")
            private Integer goodsCount;
            @JsonProperty("checkedGoodsCount")
            private Integer checkedGoodsCount;
            @JsonProperty("goodsAmount")
            private Integer goodsAmount;
            @JsonProperty("checkedGoodsAmount")
            private Integer checkedGoodsAmount;
        }


}
