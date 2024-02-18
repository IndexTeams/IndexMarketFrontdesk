package cn.kiroe.index.market.frontdesk.dao.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Vagrant
 * @Date 2024/01/03 22:27
 **/
@NoArgsConstructor
@Data
public class BrandListVo {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("desc")
    private String desc;
    @JsonProperty("picUrl")
    private String picUrl;
    @JsonProperty("floorPrice")
    private Double floorPrice;
}
