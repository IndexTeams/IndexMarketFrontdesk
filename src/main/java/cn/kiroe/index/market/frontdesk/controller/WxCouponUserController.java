package cn.kiroe.index.market.frontdesk.controller;

import cn.kiroe.index.market.frontdesk.dao.vo.UserCouponRespVo;
import cn.kiroe.index.market.frontdesk.dao.vo.common.BaseRespVo;
import cn.kiroe.index.market.frontdesk.dao.vo.common.CommonData;
import cn.kiroe.index.market.frontdesk.service.IMarketCouponUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Author:  abin
 * Date:  2024/01/04 15:23
 * <p>
 * 登录用户优惠券功能
 */
@RestController
@RequestMapping("wx/coupon")
public class WxCouponUserController {

    @Autowired
    IMarketCouponUserService couponUserService;

    @RequestMapping("mylist")
    public BaseRespVo mylist(Integer status, Integer page, Integer limit) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            Integer userId = (Integer) subject.getPrincipal();
            CommonData data = couponUserService.mylist(status, page, limit, userId);
            return BaseRespVo.ok(data);
        } else {
            return BaseRespVo.fail("请先登录");
        }
    }

    @RequestMapping("selectlist")
    public BaseRespVo selectlist(Integer cartId, Integer grouponRulesId) {
        Subject subject = SecurityUtils.getSubject();
        if(subject.isAuthenticated()){
            Integer userId = (Integer) subject.getPrincipal();
            CommonData<UserCouponRespVo> data = couponUserService.selectlist(cartId, grouponRulesId, userId);
            return BaseRespVo.ok(data);
        }else{
            return BaseRespVo.fail("请先登录");
        }
    }
    @RequestMapping("receive")
    public BaseRespVo receive(@RequestBody Map map){
        Subject subject = SecurityUtils.getSubject();
        if(subject.isAuthenticated()) {
            Integer userId = (Integer) subject.getPrincipal();
            Integer couponId = (Integer) map.get("couponId");
            int affectRows = couponUserService.receiveById(couponId,userId);
            if (affectRows == 0) {
                return BaseRespVo.fail(740,"优惠券已经领完");
            }
            return BaseRespVo.ok();
        }else {
            return BaseRespVo.fail("请先登录");
        }
    }

    // 兑换优惠券
    @RequestMapping("exchange")
    public BaseRespVo exchange(@RequestBody Map map){
        Subject subject = SecurityUtils.getSubject();
        if(subject.isAuthenticated()){
            Integer userId = (Integer) subject.getPrincipal();
            // 获取优惠券兑换码
            String code = (String) map.get("code");
            int affectRows = couponUserService.exchangeCoupon(userId, code);
            if(affectRows == 0) {
                return BaseRespVo.fail(742,"优惠券不正确");
            }
            return BaseRespVo.ok();
        }else{
            return BaseRespVo.fail("请先登录");
        }
    }
}
