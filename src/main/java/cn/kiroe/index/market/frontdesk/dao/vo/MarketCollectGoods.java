package cn.kiroe.index.market.frontdesk.dao.vo;

import cn.kiroe.index.market.frontdesk.dao.entity.MarketCollect;
import cn.kiroe.index.market.frontdesk.dao.entity.MarketGoods;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @Author Vagrant
 * @Date 2024/01/03 17:27
 **/
@Data
@NoArgsConstructor
public class MarketCollectGoods extends MarketCollect {
    String brief;
    String name;
    String picUrl;
    BigDecimal retailPrice;

    public MarketCollectGoods(MarketCollect collect, MarketGoods marketGoods) {
        setId(collect.getId());
        setType(collect.getType());
        setUserId(collect.getUserId());
        setValueId(collect.getValueId());

        this.brief = marketGoods.getBrief();
        this.name = marketGoods.getName();
        this.picUrl = marketGoods.getPicUrl();
        this.retailPrice = marketGoods.getRetailPrice();
    }
}
