package cn.kiroe.index.market.frontdesk.dao.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Kiro
 * @Date 2024/01/02 17:54
 **/
@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class AuthLoginVo {

        @JsonProperty("userInfo")
        private UserInfoDTO userInfo;
        @JsonProperty("token")
        private String token;

        @NoArgsConstructor
        @Data
        @Builder
        @AllArgsConstructor
        public static class UserInfoDTO {
            @JsonProperty("nickName")
            private String nickName;
            @JsonProperty("avatarUrl")
            private String avatarUrl;
        }
}
