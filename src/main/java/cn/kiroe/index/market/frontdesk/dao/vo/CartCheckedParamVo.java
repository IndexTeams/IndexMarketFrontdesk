package cn.kiroe.index.market.frontdesk.dao.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author Kiro
 * @Date 2024/01/03 21:58
 **/
@NoArgsConstructor
@Data
public class CartCheckedParamVo {

    @JsonProperty("productIds")
    @NotNull
    private List<Integer> productIds;
    @JsonProperty("isChecked")
    @NotNull
    private Integer isChecked;
}
