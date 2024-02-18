package cn.kiroe.index.market.frontdesk.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.kiroe.index.market.frontdesk.dao.entity.*;
import cn.kiroe.index.market.frontdesk.dao.vo.HomeIndexVo;
import cn.kiroe.index.market.frontdesk.dao.mapper.*;
import cn.kiroe.index.market.frontdesk.service.MarketHomeService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author Kiro
 * @Date 2023/12/29 23:31
 **/
@Service
@RequiredArgsConstructor
public class MarketHomeServiceImpl implements MarketHomeService {

    private final MarketGoodsMapper goodsMapper;
    private final MarketCouponMapper couponMapper;
    private final MarketCategoryMapper categoryMapper;
    private final MarketAdMapper adMapper;
    private final MarketBrandMapper brandMapper;
    private final MarketTopicMapper topicMapper;

    @Override
    public HomeIndexVo index() {
        HomeIndexVo homeIndexVo = new HomeIndexVo();
        // 获取goods数据
        List<MarketGoods> goodsList = goodsMapper.selectList(null);
        int maxSize = 4;
        homeIndexVo.setNewGoodsList(goodsList.stream().filter(MarketGoods::getIsNew).limit(maxSize).collect(Collectors.toList()));
        homeIndexVo.setHotGoodsList(goodsList.stream().filter(MarketGoods::getIsHot).limit(maxSize).collect(Collectors.toList()));
        // 获取 coupon
        List<MarketCoupon> couponList = couponMapper.selectList(null);
        homeIndexVo.setCouponList(couponList.stream().limit(maxSize).collect(Collectors.toList()));
        // 获取channel
        List<MarketCategory> channel = categoryMapper.selectList(new LambdaQueryWrapper<MarketCategory>().eq(MarketCategory::getPid, 0));
        homeIndexVo.setChannel(channel.stream().limit(maxSize).collect(Collectors.toList()));
        // 获取banner
        List<MarketAd> banner = adMapper.selectList(null);
        homeIndexVo.setBanner(banner.stream().limit(maxSize).collect(Collectors.toList()));
        // 获取brand
        List<MarketBrand> brandList = brandMapper.selectList(null);
        homeIndexVo.setBrandList(brandList.stream().limit(maxSize).collect(Collectors.toList()));
        // 设置topic
        List<MarketTopic> topicList = topicMapper.selectList(null);
        homeIndexVo.setTopicList(topicList.stream().limit(maxSize).collect(Collectors.toList()));
        Map<Integer, List<MarketGoods>> goodsMap = goodsList.stream().collect(Collectors.groupingBy(MarketGoods::getCategoryId));
        // 设置floorGoodsList
        List<HomeIndexVo.FloorGoodsListDTO> floorGoodsListDTOS = BeanUtil.copyToList(channel, HomeIndexVo.FloorGoodsListDTO.class);
        for (final HomeIndexVo.FloorGoodsListDTO floorGoodsListDTO : floorGoodsListDTOS) {
            List<MarketGoods> list = goodsMap.get(floorGoodsListDTO.getId());
            if(list != null){
               list = list.stream().limit(maxSize).collect(Collectors.toList());
            }
            floorGoodsListDTO.setGoodsList(list);
        }
        homeIndexVo.setFloorGoodsList(floorGoodsListDTOS);
        return homeIndexVo;
    }
}
