package cn.kiroe.index.market.frontdesk.dao.vo;

import cn.kiroe.index.market.frontdesk.dao.entity.MarketCategory;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author Kiro
 * @Date 2024/01/03 20:11
 **/
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class GoodsCategoryVo {

        @JsonProperty("currentCategory")
        private MarketCategory currentCategory;
        @JsonProperty("brotherCategory")
        private List<MarketCategory> brotherCategory;
        @JsonProperty("parentCategory")
        private MarketCategory parentCategory;

}
