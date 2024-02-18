package cn.kiroe.index.market.frontdesk.service.impl;

import cn.kiroe.index.market.frontdesk.dao.entity.MarketCoupon;
import cn.kiroe.index.market.frontdesk.dao.entity.MarketCouponUser;
import cn.kiroe.index.market.frontdesk.dao.entity.MarketUserCoupon;
import cn.kiroe.index.market.frontdesk.dao.mapper.MarketCouponMapper;
import cn.kiroe.index.market.frontdesk.dao.mapper.MarketCouponUserMapper;
import cn.kiroe.index.market.frontdesk.dao.vo.UserCouponRespVo;
import cn.kiroe.index.market.frontdesk.dao.vo.common.CommonData;
import cn.kiroe.index.market.frontdesk.service.IMarketCouponUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * <p>
 * 优惠券用户使用表 服务实现类
 * </p>
 *
 * @author kiro
 * @since 2023-12-29 20:15:32
 */
@Service
public class MarketCouponUserServiceImpl extends ServiceImpl<MarketCouponUserMapper, MarketCouponUser> implements IMarketCouponUserService {

    @Autowired
    MarketCouponUserMapper couponUserMapper;

    @Autowired
    MarketCouponMapper couponMapper;

    @Override
    public CommonData mylist(Integer status, Integer page, Integer limit, Integer userId) {
        // 开启分页
        PageHelper.startPage(page,limit);
        List<MarketCouponUser> userCouponList = couponUserMapper.selectList(new LambdaQueryWrapper<MarketCouponUser>()
                .eq(MarketCouponUser::getUserId,userId)
                .eq(MarketCouponUser::getStatus,status));
        PageInfo<MarketCouponUser> couponUserPageInfo = new PageInfo<>(userCouponList);
        CommonData data = CommonData.data(couponUserPageInfo);

        //
        List<MarketUserCoupon> userCouponList1 = userCouponList.stream().map(marketCouponUser -> {
            Integer couponId = marketCouponUser.getCouponId();
            MarketCoupon marketCoupon = couponMapper.selectById(couponId);
            MarketUserCoupon marketUserCoupon = new MarketUserCoupon(marketCouponUser, marketCoupon);
            return marketUserCoupon;
        }).collect(Collectors.toList());
        data.setList(userCouponList1);
        return data;
    }

    // 根据用户id获取优惠券列表
    private List<UserCouponRespVo> getUserCouponList(Integer userId){
        // 查询用户优惠券列表
        List<MarketCouponUser> userCouponList = couponUserMapper.selectList(new LambdaQueryWrapper<MarketCouponUser>()
                .eq(MarketCouponUser::getUserId, userId));
        // 查询关联优惠券信息
        List<MarketCoupon> coupons = userCouponList.stream()
                .map(userCoupon -> couponMapper.selectById(userCoupon.getCouponId()))
                .collect(Collectors.toList());
        // 整合要响应的信息
        List<UserCouponRespVo> userCouponRespVoList = IntStream.range(0, userCouponList.size())
                .mapToObj(i -> {
                    MarketCouponUser couponUser = userCouponList.get(i);
                    MarketCoupon marketCoupon = coupons.get(i);
                    return new UserCouponRespVo(
                            couponUser.getId(),
                            couponUser.getCouponId(),
                            marketCoupon.getName(),
                            marketCoupon.getDesc(),
                            marketCoupon.getTag(),
                            marketCoupon.getMin(),
                            marketCoupon.getDiscount(),
                            couponUser.getStartTime(),
                            couponUser.getEndTime(),
                            // available status=0 为可用
                            couponUser.getStatus() == 0

                    );
                }).collect(Collectors.toList());
        return userCouponRespVoList;

    }
    // 下单时查询优惠券
    @Override
    public CommonData<UserCouponRespVo> selectlist(Integer cartId, Integer grouponRulesId, Integer userId) {
        //
        List<UserCouponRespVo> userCouponList = getUserCouponList(userId);
        CommonData data = CommonData.data(userCouponList);
        return data;
    }

    @Override
    public int receiveById(Integer couponId,Integer userId) {
        // 根据优惠券id查询对应优惠券信息
        MarketCoupon marketCoupon = couponMapper.selectById(couponId);
        if(marketCoupon == null){
            return 0;
        }
        // 判断当前已领取数量和总数量相比，是否已经领完
        QueryWrapper<MarketCouponUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("coupon_id",couponId).eq("user_id",userId);

        List<MarketCouponUser> marketCouponUserList = couponUserMapper.selectList(queryWrapper);
        Integer limit = marketCoupon.getLimit();
        if(limit == 1 && marketCouponUserList != null){
            return 0;
        }
        MarketCouponUser couponUser = new MarketCouponUser();
        couponUser.setCouponId(couponId);
        couponUser.setUserId(userId);
        couponUser.setStatus(marketCoupon.getStatus());
        couponUser.setStartTime(marketCoupon.getStartTime());
        couponUser.setEndTime(marketCoupon.getEndTime());
        couponUser.setUpdateTime(marketCoupon.getUpdateTime());
        couponUser.setDeleted(false);

        int affectRows = couponUserMapper.insert(couponUser);

        return affectRows;
    }

    // 兑换优惠券
    @Override
    public int exchangeCoupon(Integer userId, String code) {
        // 根据优惠券兑换码code查询是否有优惠券
        QueryWrapper<MarketCoupon> couponQueryWrapper = new QueryWrapper<>();
        couponQueryWrapper.eq("code",code);
        List<MarketCoupon> couponList = couponMapper.selectList(couponQueryWrapper);
        // 判断是否找到优惠券
        if (couponList == null || couponList.size() == 0){
            return 0;// 未找到优惠券，返回0表示失败
        }
        // 获取优惠券领取限制
        Integer limit = couponList.get(0).getLimit();
        // 查询当前用户是否领取过优惠券
        Integer couponId = couponList.get(0).getId();
        QueryWrapper<MarketCouponUser> couponUserQueryWrapper = new QueryWrapper<>();
        couponUserQueryWrapper.eq("coupon_id",couponId)
                .eq("user_id",userId);
        List<MarketCouponUser> marketCouponUserList = couponUserMapper.selectList(couponUserQueryWrapper);

        //判断领取限制是否达到
        if(limit == 1 && (marketCouponUserList != null && marketCouponUserList.size() > 0)){
            // 已经领过，且限制为1，返回0表示失败
            return 0;
        }

        // 遍历找到优惠券列表，插入coupon_user领取记录
        for (MarketCoupon marketCoupon : couponList) {
            // 开始时间--->领取优惠券的当前时间
            LocalDateTime startTime = LocalDateTime.now();
            // 结束时间--->开始时间+优惠券有效天数
            LocalDateTime endTime = startTime.plusDays(marketCoupon.getDays());

            MarketCouponUser couponUser = MarketCouponUser.builder()
                    .couponId(marketCoupon.getId())
                    .userId(userId)
                    .status(marketCoupon.getStatus())
                    .startTime(startTime)
                    .endTime(endTime)
                    .build();
            couponUserMapper.insert(couponUser);
        }
        // 兑换成功，返回1
        return 1;
    }
}
