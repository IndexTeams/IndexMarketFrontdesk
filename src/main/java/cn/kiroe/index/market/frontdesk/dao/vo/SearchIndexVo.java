package cn.kiroe.index.market.frontdesk.dao.vo;

import cn.kiroe.index.market.frontdesk.dao.entity.MarketKeyword;
import cn.kiroe.index.market.frontdesk.dao.entity.MarketSearchHistory;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @Author: 阿超
 * @Date: 2024年01月02日 20:56
 * @Title: SearchIndexVo
 * @Package cn.kiroe.index.market.frontdesk.dao.vo
 */
@Data
@NoArgsConstructor
public class SearchIndexVo {
    @JsonProperty("defaultKeyword")
    private MarketKeyword defaultKeyword;
    @JsonProperty("hotKeywordList")
    private  List<MarketKeyword> hotKeywordList;
    @JsonProperty("historyKeywordList")
    private List<Map<String,String>> historyKeywordList;
}
