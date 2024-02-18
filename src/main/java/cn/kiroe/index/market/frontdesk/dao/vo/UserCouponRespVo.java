package cn.kiroe.index.market.frontdesk.dao.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.ws.handler.PortInfo;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Author:  abin
 * Date:  2024/01/05 10:21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCouponRespVo {

    private Integer id;
    private Integer cid;
    private String name;
    private String desc;
    private String tag;
    private BigDecimal min;
    private BigDecimal discount;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;
    private Boolean available = true;

}
