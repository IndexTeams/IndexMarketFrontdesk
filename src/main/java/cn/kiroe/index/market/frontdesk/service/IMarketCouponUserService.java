package cn.kiroe.index.market.frontdesk.service;

import cn.kiroe.index.market.frontdesk.dao.entity.MarketCouponUser;
import cn.kiroe.index.market.frontdesk.dao.vo.UserCouponRespVo;
import cn.kiroe.index.market.frontdesk.dao.vo.common.CommonData;
import cn.kiroe.index.market.frontdesk.service.impl.MarketCouponUserServiceImpl;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 优惠券用户使用表 服务类
 * </p>
 *
 * @author kiro
 * @since 2023-12-29 20:15:32
 */
public interface IMarketCouponUserService extends IService<MarketCouponUser> {

    CommonData mylist(Integer status, Integer page, Integer limit, Integer userId);

    CommonData<UserCouponRespVo> selectlist(Integer cartId, Integer grouponRulesId, Integer userId);

    int receiveById(Integer couponId,Integer userId);

    int exchangeCoupon(Integer userId, String code);


}
