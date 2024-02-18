package cn.kiroe.index.market.frontdesk.dao.vo;

import cn.kiroe.index.market.frontdesk.dao.entity.MarketCategory;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author Kiro
 * @Date 2023/12/29 21:24
 **/
@NoArgsConstructor
@Data
@Schema(description = "分类视图")
public class CatalogIndexVo {

        @JsonProperty("currentCategory")
        @Schema(name = "当前选择分类")
        private MarketCategory currentCategory;
        @JsonProperty("categoryList")
        @Schema(name = "根分类列表")
        private List<MarketCategory> categoryList;
        @JsonProperty("currentSubCategory")
        @Schema(name = "当前分类的子分类")
        private List<MarketCategory> currentSubCategory;
}
