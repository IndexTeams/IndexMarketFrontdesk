package cn.kiroe.index.market.frontdesk.dao.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Kiro
 * @Date 2024/01/03 09:29
 **/
@NoArgsConstructor
@Data
public class UserIndexVo {
    @JsonProperty("order")
    private DataDTO order;
    @NoArgsConstructor
    @Data
    public static class DataDTO {
        @JsonProperty("unrecv")
        private Integer unrecv;
        @JsonProperty("uncomment")
        private Integer uncomment;
        @JsonProperty("unpaid")
        private Integer unpaid;
        @JsonProperty("unship")
        private Integer unship;
    }



}
