package cn.kiroe.index.market.frontdesk.service;

import cn.kiroe.index.market.frontdesk.dao.entity.MarketCart;
import cn.kiroe.index.market.frontdesk.dao.vo.CartCheckedParamVo;
import cn.kiroe.index.market.frontdesk.dao.vo.CartIndexVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 购物车商品表 服务类
 * </p>
 *
 * @author kiro
 * @since 2023-12-29 20:15:32
 */
public interface IMarketCartService extends IService<MarketCart> {

    CartIndexVo index(Integer userId);

    void updateChecked(Integer userId,CartCheckedParamVo cartCheckedParamVo);

    void delete(Integer userId,Integer... productIds);
}
