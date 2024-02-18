package cn.kiroe.index.market.frontdesk.dao.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Author:  abin
 * Date:  2024/01/04 09:45
 *
 * 足迹关联的商品列表属性
 */
@Data
@NoArgsConstructor
public class MarketFootprintGoods extends MarketFootprint{

    String brief;
    String picUrl;
    String name;
    BigDecimal retailPrice;
    public MarketFootprintGoods(MarketFootprint marketFootprint, MarketGoods marketGoods) {
        // 设置足迹信息属性
        setId(marketFootprint.getId());
        setAddTime(marketFootprint.getAddTime());
        setGoodsId(marketFootprint.getGoodsId());
        // 设置商品信息属性
        this.brief = marketGoods.getBrief();
        this.picUrl = marketGoods.getPicUrl();
        this.name = marketGoods.getName();
        this.retailPrice = marketGoods.getRetailPrice();
    }
}
