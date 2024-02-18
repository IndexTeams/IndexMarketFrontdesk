package cn.kiroe.index.market.frontdesk.controller;


import cn.kiroe.index.market.frontdesk.dao.entity.MarketComment;
import cn.kiroe.index.market.frontdesk.dao.entity.MarketOrder;
import cn.kiroe.index.market.frontdesk.dao.entity.MarketOrderGoods;
import cn.kiroe.index.market.frontdesk.dao.vo.OrderDetailVo;
import cn.kiroe.index.market.frontdesk.dao.vo.OrderVo;
import cn.kiroe.index.market.frontdesk.dao.vo.common.BaseRespVo;
import cn.kiroe.index.market.frontdesk.dao.vo.common.CommonData;
import cn.kiroe.index.market.frontdesk.service.IMarketCommentService;
import cn.kiroe.index.market.frontdesk.service.IMarketGoodsProductService;
import cn.kiroe.index.market.frontdesk.service.IMarketGoodsService;
import cn.kiroe.index.market.frontdesk.service.IMarketOrderService;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NoArgsConstructor;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


/**
 * @Author ZDX
 * @Date 2024/01/02 19:48
 **/
@NoArgsConstructor
@RestController
@RequestMapping("wx/order")
@Tag(name = "订单删除接口/wx/order/delete", description = "删除接口")
public class MarketOrderController {

    // tempId用于记录此时评论是market_order_goods表中的id字段
    private Integer tempId;
    private Integer tempGoodsId;
    @Autowired
    private IMarketOrderService orderService;
    @Autowired
    private IMarketCommentService commentService;
    @Autowired
    private IMarketGoodsService goodsService;
    @Autowired
    private IMarketGoodsProductService goodsProductService;

    @JsonProperty("orderId")
    private Integer orderId;

    @PostMapping("delete")
    // {"errno":0,"errmsg":"成功"} 啥都不传
    public BaseRespVo delete(@RequestBody OrderVo orderVo) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            // Map<String,String> map 使用Map封装
            // Integer orderId = NumberUtil.parseInt(map.get("orderId")) ;
            boolean result = orderService.removeById(orderVo.getOrderId());
            if (result) {
                return BaseRespVo.ok();
            }
            return BaseRespVo.fail("删除失败");
        }
        // 认证失败
        return BaseRespVo.fail("请先登录");
    }

    @PostMapping("confirm")
    // 也是data啥也不传
    public BaseRespVo confirm(@RequestBody OrderVo orderVo) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            // 订单状态对应的状态码
            // 101: '未付款', 102: '用户取消',
            // 103: '系统取消', 201: '已付款',
            // 202: '申请退款', 203: '已退款',
            // 301: '已发货', 401: '用户收货', 402: '系统收货'

            // 根据id更新订单状态码
            MarketOrder marketOrder = new MarketOrder();
            marketOrder.setOrderStatus(MarketOrder.OrderStatus.STATUS_CONFIRM.getValue());
            marketOrder.setId(orderVo.getOrderId());
            // MyBatisPlus封装的更新类 传入实体对象就会根据主键Id修改 前提：对象类上主键对应属性有@TableId

            // 2024-01-03 17:41:43.688 DEBUG 8248 --- [nio-8083-exec-1] c.k.i.m.f.d.m.M.updateById
            // : ==>  Preparing: UPDATE market_order SET order_status=?, update_time=?
            // WHERE id=? AND deleted=false
            boolean result = orderService.updateById(marketOrder);
            if (result) {
                return BaseRespVo.ok();
            }
            return BaseRespVo.fail("确认失败");
        }// 认证失败
        return BaseRespVo.fail("请先登录");
    }


    @RequestMapping("/comment")
    public BaseRespVo comment(@RequestBody MarketComment comment) {
        Integer id = tempId;
        Integer goodsId = tempGoodsId;
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            // 认证成功
            Integer userId = (Integer) subject.getPrincipal();
            comment.setUserId(userId);
            comment.setValueId(goodsId);
            Integer affectRows = orderService.insertComment(comment, id);
            if (affectRows != null) {
                // 插入成功
                return BaseRespVo.ok();
            }
            // 插入失败
            return BaseRespVo.fail("评论失败");
        }
        // 认证失败
        return BaseRespVo.fail("请先登录");
    }

    @RequestMapping("/list")
    public BaseRespVo list(Integer showType, Integer page, Integer limit) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            // 认证成功
            Integer userId = (Integer) subject.getPrincipal();
            // 传入userId
            CommonData data = orderService.getOrderList(showType, page, limit, userId);
            return BaseRespVo.ok(data);
        }
        // 认证失败
        return BaseRespVo.fail("请先登录");

    }

    @RequestMapping("/detail")
    public BaseRespVo detail(Integer orderId) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            // 认证成功，根据orderId查，不用userId
            OrderDetailVo data = orderService.getOrderDetail(orderId);
            return BaseRespVo.ok(data);
        }
        // 认证失败
        return BaseRespVo.fail("请先登录");
    }

    @RequestMapping("/cancel")
    public BaseRespVo cancel(@RequestBody Map<String, Integer> map) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            // 认证成功
            Integer affectedRows = orderService.cancelOrder(map.get("orderId"));
            if (affectedRows != null) {
                // 修改订单状态成功
                return BaseRespVo.ok();
            }
            // 修改订单状态失败
            return BaseRespVo.fail("取消订单失败");
        }
        // 认证失败
        return BaseRespVo.fail("请先登录");
    }

    @RequestMapping("/refund")
    public BaseRespVo refund(@RequestBody Map<String, Integer> map) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            // 认证成功
            Integer affectedRows = orderService.refund(map.get("orderId"));
            if (affectedRows != null) {
                // 修改订单状态成功
                return BaseRespVo.ok();
            }
            // 修改订单状态失败
            return BaseRespVo.fail("退款失败");
        }
        // 认证失败
        return BaseRespVo.fail("请先登录");
    }



    @RequestMapping("/goods")
    public BaseRespVo goods(Integer orderId, Integer goodsId, Integer productId) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            // 认证成功
            MarketOrderGoods data = orderService.getGoods(orderId, goodsId, productId);
            tempId = data.getId();
            tempGoodsId = data.getGoodsId();
            return BaseRespVo.ok(data);
        }
        // 认证失败
        return BaseRespVo.fail("请先登录");
    }

}
