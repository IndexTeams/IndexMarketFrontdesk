package cn.kiroe.index.market.frontdesk.service;

import cn.kiroe.index.market.frontdesk.dao.entity.MarketComment;
import cn.kiroe.index.market.frontdesk.dao.entity.MarketOrder;
import cn.kiroe.index.market.frontdesk.dao.entity.MarketOrderGoods;
import cn.kiroe.index.market.frontdesk.dao.vo.OrderDetailVo;
import cn.kiroe.index.market.frontdesk.dao.vo.common.CommonData;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author kiro
 * @since 2023-12-29 20:15:32
 */
public interface IMarketOrderService extends IService<MarketOrder> {

    Integer insertComment(MarketComment comment, Integer id);

    CommonData getOrderList(Integer showType, Integer page, Integer limit, Integer userId);

    OrderDetailVo getOrderDetail(Integer orderId);

    Integer cancelOrder(Integer orderId);

    Integer refund(Integer orderId);

    MarketOrderGoods getGoods(Integer orderId, Integer goodsId, Integer productId);
}
