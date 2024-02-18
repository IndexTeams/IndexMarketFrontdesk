package cn.kiroe.index.market.frontdesk.dao.vo;

import cn.kiroe.index.market.frontdesk.dao.entity.MarketGoods;
import cn.kiroe.index.market.frontdesk.dao.entity.MarketTopic;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: 阿超
 * @Date: 2024年01月03日 20:33
 * @Title: TopicDetailVo
 * @Package cn.kiroe.index.market.frontdesk.dao.vo
 */
@Data
@NoArgsConstructor
public class TopicDetailVo {
    @JsonProperty("topic")
    private MarketTopic topic;
    @JsonProperty("goods")
    private List<MarketGoods> goods;
}
