package cn.kiroe.index.market.frontdesk.controller;

import cn.kiroe.index.market.frontdesk.dao.vo.common.BaseRespVo;
import cn.kiroe.index.market.frontdesk.dao.vo.common.CommonData;
import cn.kiroe.index.market.frontdesk.service.IMarketCouponService;
import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author:  abin
 * Date:  2024/01/04 14:23
 *
 * 优惠券功能
 */
@RestController
@RequestMapping("/wx/coupon")
public class WxCouponController {

    @Autowired
    IMarketCouponService couponService;

    @RequestMapping("list")
    public BaseRespVo list(Integer page,Integer limit){
        /*Subject subject = SecurityUtils.getSubject();
        if(subject.isAuthenticated()){
             Integer userId = (Integer) subject.getPrincipal();
            CommonData data = couponService.list(page, limit, userId);
            return BaseRespVo.ok(data);
        }else{
            return BaseRespVo.fail("请先登录");
        }*/
        CommonData data = couponService.list(page, limit);
        return BaseRespVo.ok(data);
    }
}
