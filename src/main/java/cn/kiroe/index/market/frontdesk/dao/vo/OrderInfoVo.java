package cn.kiroe.index.market.frontdesk.dao.vo;

import cn.kiroe.index.market.frontdesk.util.OrderHandleOption;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * ClassName: OrderInfoVo
 * Package: cn.kiroe.index.market.frontdesk.dao.vo
 * Description:
 *
 * @Author: 李家合
 * @Create: 2024/1/4 16:14
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class OrderInfoVo {
    @JsonProperty("expName")
    private String expName;
    @JsonProperty("consignee")
    private String consignee;
    @JsonProperty("address")
    private String address;
    @JsonProperty("addTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime addTime;
    @JsonProperty("orderSn")
    private String orderSn;
    @JsonProperty("actualPrice")
    private BigDecimal actualPrice;
    @JsonProperty("mobile")
    private String mobile;
    @JsonProperty("message")
    private String message;
    @JsonProperty("expCode")
    private String expCode;
    @JsonProperty("orderStatusText")
    private String orderStatusText;
    @JsonProperty("aftersaleStatus")
    private Integer aftersaleStatus;
    @JsonProperty("goodsPrice")
    private BigDecimal goodsPrice;
    @JsonProperty("expNo")
    private String expNo;
    @JsonProperty("couponPrice")
    private BigDecimal couponPrice;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("freightPrice")
    private BigDecimal freightPrice;
    @JsonProperty("handleOption")
    private OrderHandleOption handleOption;
}
