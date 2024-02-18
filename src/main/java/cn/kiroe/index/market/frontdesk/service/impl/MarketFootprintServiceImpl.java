package cn.kiroe.index.market.frontdesk.service.impl;

import cn.kiroe.index.market.frontdesk.dao.entity.MarketFootprint;
import cn.kiroe.index.market.frontdesk.dao.entity.MarketFootprintGoods;
import cn.kiroe.index.market.frontdesk.dao.entity.MarketGoods;
import cn.kiroe.index.market.frontdesk.dao.mapper.MarketFeedbackMapper;
import cn.kiroe.index.market.frontdesk.dao.mapper.MarketFootprintMapper;
import cn.kiroe.index.market.frontdesk.dao.mapper.MarketGoodsMapper;
import cn.kiroe.index.market.frontdesk.dao.vo.AuthLoginVo;
import cn.kiroe.index.market.frontdesk.dao.vo.common.CommonData;
import cn.kiroe.index.market.frontdesk.service.IMarketFootprintService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户浏览足迹表 服务实现类
 * </p>
 *
 * @author kiro
 * @since 2023-12-29 20:15:32
 */
@Service
public class MarketFootprintServiceImpl extends ServiceImpl<MarketFootprintMapper, MarketFootprint> implements IMarketFootprintService {

    @Autowired
    MarketFootprintMapper footprintMapper;

    @Autowired
    MarketGoodsMapper goodsMapper;

    @Override
    public CommonData list(Integer page, Integer limit,Integer userId) {
        // 开启分页
        PageHelper.startPage(page,limit);
        // 查询用户足迹信息
        List<MarketFootprint> footprintList = footprintMapper.selectList(new LambdaQueryWrapper<MarketFootprint>()
                .eq(MarketFootprint::getUserId, userId)
                // 按照时间降序排序
                .orderByDesc(MarketFootprint::getAddTime));
        // 封装分页
        PageInfo<MarketFootprint> footprintPageInfo = new PageInfo<>(footprintList);
        CommonData data = CommonData.data(footprintPageInfo);
        // 整合足迹关联的商品信息
        List<MarketFootprintGoods> marketFootprintGoodsList = footprintList.stream().map(marketFootprint -> {
            Integer goodsId = marketFootprint.getGoodsId();
            MarketGoods marketGoods = goodsMapper.selectById(goodsId);
            MarketFootprintGoods marketFootprintGoods = new MarketFootprintGoods(marketFootprint, marketGoods);
            return marketFootprintGoods;
        }).collect(Collectors.toList());
        data.setList(marketFootprintGoodsList);
        return data;
    }

    @Override
    public int deleteById(Integer id) {
        int affectRows = footprintMapper.deleteById(id);
        return affectRows;
    }
}
