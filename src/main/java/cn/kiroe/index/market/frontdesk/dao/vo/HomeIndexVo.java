package cn.kiroe.index.market.frontdesk.dao.vo;

import cn.kiroe.index.market.frontdesk.dao.entity.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author Kiro
 * @Date 2023/12/29 23:18
 **/
@NoArgsConstructor
@Data
public class HomeIndexVo {

        @JsonProperty("newGoodsList")
        private List<MarketGoods> newGoodsList;
        @JsonProperty("couponList")
        private List<MarketCoupon> couponList;
        @JsonProperty("channel")
        private List<MarketCategory> channel;
        @JsonProperty("banner")
        private List<MarketAd> banner;
        @JsonProperty("brandList")
        private List<MarketBrand> brandList;
        @JsonProperty("hotGoodsList")
        private List<MarketGoods> hotGoodsList;
        @JsonProperty("topicList")
        private List<MarketTopic> topicList;
        @JsonProperty("floorGoodsList")
        private List<FloorGoodsListDTO> floorGoodsList;

        @NoArgsConstructor
        @Data
        public static class FloorGoodsListDTO {
            @JsonProperty("name")
            private String name;
            @JsonProperty("goodsList")
            private List<MarketGoods> goodsList;
            @JsonProperty("id")
            private Integer id;
    }
}
