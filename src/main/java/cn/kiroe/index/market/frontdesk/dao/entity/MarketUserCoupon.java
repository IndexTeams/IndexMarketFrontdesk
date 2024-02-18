package cn.kiroe.index.market.frontdesk.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Author:  abin
 * Date:  2024/01/04 15:56
 *
 * 登录用户的优惠券关联全部优惠券
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MarketUserCoupon extends MarketCouponUser{

    Integer cid;
    String name;
    String desc;
    String tag;
    BigDecimal min;
    BigDecimal discount;

    Boolean available;
    private void setAvailability(LocalDateTime startTime, LocalDateTime endTime) {
        if (startTime != null && endTime != null
                && startTime.isBefore(LocalDateTime.now())
                && endTime.isAfter(LocalDateTime.now())) {
            this.setAvailable(true);
        } else {
            this.setAvailable(false);
        }
    }

    public MarketUserCoupon(MarketCouponUser marketCouponUser, MarketCoupon marketCoupon) {
        setId(marketCouponUser.getId());
        setStartTime(marketCouponUser.getStartTime());
        setEndTime(marketCouponUser.getEndTime());

        this.cid = marketCoupon.getId();
        this.name = marketCoupon.getName();
        this.desc = marketCoupon.getDesc();
        this.tag = marketCoupon.getTag();
        this.min = marketCoupon.getMin();
        this.discount = marketCoupon.getDiscount();

        setAvailability(marketCoupon.getStartTime(), marketCoupon.getEndTime());
    }

}
