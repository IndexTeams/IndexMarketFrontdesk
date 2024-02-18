package cn.kiroe.index.market.frontdesk.dao.vo;

import cn.kiroe.index.market.frontdesk.dao.entity.MarketCategory;
import cn.kiroe.index.market.frontdesk.dao.entity.MarketGoods;
import cn.kiroe.index.market.frontdesk.dao.vo.common.CommonData;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author Kiro
 * @Date 2024/01/03 20:33
 **/
@NoArgsConstructor
@Data
public class GoodsListVo extends CommonData<MarketGoods> {
        @JsonProperty("filterCategoryList")
        private List<MarketCategory> filterCategoryList;
}
