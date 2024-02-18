package cn.kiroe.index.market.frontdesk.service;

import cn.kiroe.index.market.frontdesk.dao.entity.MarketGoods;
import cn.kiroe.index.market.frontdesk.dao.vo.GoodsCategoryVo;
import cn.kiroe.index.market.frontdesk.dao.vo.GoodsListVo;
import cn.kiroe.index.market.frontdesk.dao.vo.common.BasePage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 商品基本信息表 服务类
 * </p>
 *
 * @author kiro
 * @since 2023-12-29 20:15:32
 */
public interface IMarketGoodsService extends IService<MarketGoods> {

    GoodsCategoryVo category(Integer id);

    GoodsListVo list(BasePage basePage, Integer categoryId);

    Boolean insert(Integer userId, String keyword,String searchHistoryFrom);
}
