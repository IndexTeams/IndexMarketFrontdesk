package cn.kiroe.index.market.frontdesk.service.impl;

import cn.kiroe.index.market.frontdesk.dao.entity.MarketComment;
import cn.kiroe.index.market.frontdesk.dao.entity.MarketOrder;
import cn.kiroe.index.market.frontdesk.dao.entity.MarketOrderGoods;
import cn.kiroe.index.market.frontdesk.dao.mapper.MarketCommentMapper;
import cn.kiroe.index.market.frontdesk.dao.mapper.MarketOrderGoodsMapper;
import cn.kiroe.index.market.frontdesk.dao.mapper.MarketOrderMapper;
import cn.kiroe.index.market.frontdesk.dao.vo.OrderDetailVo;
import cn.kiroe.index.market.frontdesk.dao.vo.OrderInfoVo;
import cn.kiroe.index.market.frontdesk.dao.vo.OrderListVo;
import cn.kiroe.index.market.frontdesk.dao.vo.common.CommonData;
import cn.kiroe.index.market.frontdesk.service.IMarketOrderService;
import cn.kiroe.index.market.frontdesk.util.OrderHandleOption;
import cn.kiroe.index.market.frontdesk.util.OrderUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author kiro
 * @since 2023-12-29 20:15:32
 */
@Service
public class MarketOrderServiceImpl extends ServiceImpl<MarketOrderMapper, MarketOrder> implements IMarketOrderService {

    @Autowired
    MarketCommentMapper commentMapper;
    @Autowired
    MarketOrderMapper orderMapper;
    @Autowired
    MarketOrderGoodsMapper orderGoodsMapper;

    @Transactional
    @Override
    public Integer insertComment(MarketComment comment, Integer id) {

        // 往market_comment表中插入评论
        Integer commentAffectRows = commentMapper.insert(comment);

        // 更新market_order_goods表中的comment字段，是market_comment表中对应的id
        Integer commentId = comment.getId();
        MarketOrderGoods orderGoods = new MarketOrderGoods();
        orderGoods.setId(id);
        orderGoods.setComment(commentId);

        int orderGoodsAffectRows = orderGoodsMapper.updateById(orderGoods);

        if (commentAffectRows != 1 && orderGoodsAffectRows != 1) {
            // 回滚事务
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

        return commentAffectRows == 1 && orderGoodsAffectRows == 1 ? 1 : null;

    }

    @Override
    public CommonData getOrderList(Integer showType, Integer page, Integer limit, Integer userId) {
        // 设置分页插件
        PageHelper.startPage(page, limit);

        List<MarketOrder> orderList = null;

        // 根据showType查询market_order表中指定的部分字段
        List<Short> orderStatus = OrderUtil.orderStatus(showType);
        if (orderStatus == null) {
            // 查询全部订单，根据userId查
            orderList = orderMapper.selectList(new LambdaQueryWrapper<MarketOrder>()
                    .eq(MarketOrder::getUserId, userId)
                    .select(MarketOrder::getId, MarketOrder::getOrderStatus, MarketOrder::getAftersaleStatus,
                            MarketOrder::getOrderSn, MarketOrder::getActualPrice));
        } else if (orderStatus.get(0) == 101) {
            // 自动拆箱
            // 待付款订单
            orderList = orderMapper.selectList(new LambdaQueryWrapper<MarketOrder>()
                    .eq(MarketOrder::getUserId, userId)
                    .eq(MarketOrder::getOrderStatus, orderStatus.get(0))
                    .select(MarketOrder::getId, MarketOrder::getOrderStatus, MarketOrder::getAftersaleStatus,
                            MarketOrder::getOrderSn, MarketOrder::getActualPrice));

        } else if (orderStatus.get(0) == 201) {
            // 待发货订单
            orderList = orderMapper.selectList(new LambdaQueryWrapper<MarketOrder>()
                    .eq(MarketOrder::getUserId, userId)
                    .eq(MarketOrder::getOrderStatus, orderStatus.get(0))
                    .select(MarketOrder::getId, MarketOrder::getOrderStatus, MarketOrder::getAftersaleStatus,
                            MarketOrder::getOrderSn, MarketOrder::getActualPrice));

        } else if (orderStatus.get(0) == 301) {
            // 待收货订单
            orderList = orderMapper.selectList(new LambdaQueryWrapper<MarketOrder>()
                    .eq(MarketOrder::getUserId, userId)
                    .eq(MarketOrder::getOrderStatus, orderStatus.get(0))
                    .select(MarketOrder::getId, MarketOrder::getOrderStatus, MarketOrder::getAftersaleStatus,
                            MarketOrder::getOrderSn, MarketOrder::getActualPrice));

        } else if (orderStatus.get(0) == 401) {
            // 待评价订单
            orderList = orderMapper.selectList(new LambdaQueryWrapper<MarketOrder>()
                    .eq(MarketOrder::getUserId, userId)
                    .eq(MarketOrder::getOrderStatus, orderStatus.get(0))
                    .select(MarketOrder::getId, MarketOrder::getOrderStatus, MarketOrder::getAftersaleStatus,
                            MarketOrder::getOrderSn, MarketOrder::getActualPrice));
        }


        PageInfo<MarketOrder> pageInfo = new PageInfo<>(orderList);
        CommonData data = CommonData.data(pageInfo);

        // 流处理，返回OrderListVo类型的List对象
        List<OrderListVo> respList = orderList.stream().map(order -> {
            // 查询market_order_goods表中指定的字段，关联是order_id
            List<MarketOrderGoods> goodsList = orderGoodsMapper.selectList(new LambdaQueryWrapper<MarketOrderGoods>()
                    .eq(MarketOrderGoods::getOrderId, order.getId())
                    .select(MarketOrderGoods::getNumber, MarketOrderGoods::getPicUrl, MarketOrderGoods::getPrice
                    ,MarketOrderGoods::getId, MarketOrderGoods::getGoodsName, MarketOrderGoods::getSpecifications));

            // 根据order对象封装订单具有的操作权限
            OrderHandleOption handleOption = OrderUtil.build(order);

            // 为新对象赋值
            return new OrderListVo().setOrderStatusText(OrderUtil.orderStatusText(order))
                    .setId(order.getId())
                    .setAftersaleStatus(order.getAftersaleStatus())
                    .setOrderSn(order.getOrderSn())
                    .setActualPrice(order.getActualPrice())
                    .setGoodsList(goodsList)
                    .setHandleOption(handleOption);
        }).collect(Collectors.toList());


        data.setList(respList);
        return data;
    }

    @Override
    public OrderDetailVo getOrderDetail(Integer orderId) {
        // 根据指定的userId查询order信息
        MarketOrder order = orderMapper.selectById(orderId);

        // 根据orderId查询orderGoods
        List<MarketOrderGoods> goodsList = orderGoodsMapper.selectList(new LambdaQueryWrapper<MarketOrderGoods>()
                .eq(MarketOrderGoods::getOrderId, orderId));

        // 查询handleOption
        OrderHandleOption handleOption = OrderUtil.build(order);

        // 封装成OrderDetailVo
        OrderDetailVo data = new OrderDetailVo()
                .setOrderInfo(new OrderInfoVo()
                        .setConsignee(order.getConsignee())
                        .setAddress(order.getAddress())
                        .setAddTime(order.getAddTime())
                        .setOrderSn(order.getOrderSn())
                        .setActualPrice(order.getActualPrice())
                        .setMobile(order.getMobile())
                        .setMessage(order.getMessage())
//                        .setExpCode(order.getShipChannel())
                        .setOrderStatusText(OrderUtil.orderStatusText(order))
                        .setAftersaleStatus(order.getAftersaleStatus())
                        .setGoodsPrice(order.getGoodsPrice())
//                        .setExpNo(order.getShipSn())
                        .setCouponPrice(order.getCouponPrice())
                        .setId(orderId)
                        .setFreightPrice(order.getFreightPrice())
                        .setHandleOption(handleOption))
                .setOrderGoods(goodsList);

        // 订单状态为401，即待评价时才设置expName,expCode,expNo
        if (order.getOrderStatus() == 401) {
            data.getOrderInfo().setExpName(OrderUtil.getExpName(order));
            data.getOrderInfo().setExpCode(order.getShipChannel());
            data.getOrderInfo().setExpNo(order.getShipSn());
        }

        return data;
    }

    @Override
    public Integer cancelOrder(Integer orderId) {
        // 修改订单状态
        MarketOrder order = new MarketOrder();
        order.setId(orderId);
        order.setOrderStatus(102);
        int affectedRows = orderMapper.updateById(order);

        return affectedRows == 1 ? affectedRows : null;
    }

    @Override
    public Integer refund(Integer orderId) {
        // 修改订单状态
        MarketOrder order = new MarketOrder();
        order.setId(orderId);
        order.setOrderStatus(203);

        int affectedRows = orderMapper.updateById(order);
        return affectedRows == 1 ? affectedRows : null;
    }

    @Override
    public MarketOrderGoods getGoods(Integer orderId, Integer goodsId, Integer productId) {
        // 查询market_order_goods表所有字段信息
        return orderGoodsMapper.selectOne(new LambdaQueryWrapper<MarketOrderGoods>()
                .eq(MarketOrderGoods::getOrderId, orderId)
                .eq(MarketOrderGoods::getGoodsId, goodsId)
                .eq(MarketOrderGoods::getProductId, productId));
    }
}
