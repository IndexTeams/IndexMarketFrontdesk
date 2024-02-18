package cn.kiroe.index.market.frontdesk.service.impl;

import cn.kiroe.index.market.frontdesk.dao.entity.MarketCoupon;
import cn.kiroe.index.market.frontdesk.dao.mapper.MarketCouponMapper;
import cn.kiroe.index.market.frontdesk.dao.vo.common.CommonData;
import cn.kiroe.index.market.frontdesk.service.IMarketCouponService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 优惠券信息及规则表 服务实现类
 * </p>
 *
 * @author kiro
 * @since 2023-12-29 20:15:32
 */
@Service
public class MarketCouponServiceImpl extends ServiceImpl<MarketCouponMapper, MarketCoupon> implements IMarketCouponService {

    @Autowired
    MarketCouponMapper couponMapper;

    @Override
    public CommonData list(Integer page, Integer limit) {
        // 开启分页
        PageHelper.startPage(page,limit);
        //查询market_coupon表，根据响应选择所需要的字段
        List<MarketCoupon> couponList = couponMapper.selectList(new LambdaQueryWrapper<MarketCoupon>()
                .select(
                        MarketCoupon::getId,
                        MarketCoupon::getName,
                        MarketCoupon::getDesc,
                        MarketCoupon::getTag,
                        MarketCoupon::getDiscount,
                        MarketCoupon::getMin,
                        MarketCoupon::getDays
                )
        );
        PageInfo<MarketCoupon> couponPageInfo = new PageInfo<>(couponList);
        CommonData data = CommonData.data(couponPageInfo);
        data.setList(couponList);

        return data;
    }
}
