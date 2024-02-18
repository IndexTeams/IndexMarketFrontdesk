package cn.kiroe.index.market.frontdesk.service.impl;

import cn.kiroe.index.market.frontdesk.dao.entity.MarketCategory;
import cn.kiroe.index.market.frontdesk.dao.entity.MarketGoods;
import cn.kiroe.index.market.frontdesk.dao.entity.MarketSearchHistory;
import cn.kiroe.index.market.frontdesk.dao.mapper.MarketCategoryMapper;
import cn.kiroe.index.market.frontdesk.dao.mapper.MarketGoodsMapper;
import cn.kiroe.index.market.frontdesk.dao.mapper.MarketSearchHistoryMapper;
import cn.kiroe.index.market.frontdesk.dao.vo.GoodsCategoryVo;
import cn.kiroe.index.market.frontdesk.dao.vo.GoodsListVo;
import cn.kiroe.index.market.frontdesk.dao.vo.common.BasePage;
import cn.kiroe.index.market.frontdesk.dao.vo.common.CommonData;
import cn.kiroe.index.market.frontdesk.service.IMarketGoodsService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商品基本信息表 服务实现类
 * </p>
 *
 * @author kiro
 * @since 2023-12-29 20:15:32
 */
@Service
@RequiredArgsConstructor
public class MarketGoodsServiceImpl extends ServiceImpl<MarketGoodsMapper, MarketGoods> implements IMarketGoodsService {
    private final MarketCategoryMapper categoryMapper;
    private final MarketGoodsMapper goodsMapper;
    private final MarketSearchHistoryMapper searchHistoryMapper;


    @Override
    public GoodsCategoryVo category(Integer id) {
        // 查询当前
        MarketCategory currentCategory = categoryMapper.selectOne(new LambdaQueryWrapper<MarketCategory>().eq(MarketCategory::getId, id));
        if (currentCategory == null){
            throw new RuntimeException("无当前分类");
        }
        // 兄弟 分类
        List<MarketCategory> brotherCategory = categoryMapper.selectList(new LambdaQueryWrapper<MarketCategory>().eq(MarketCategory::getPid, currentCategory.getPid()));
        // 父 分类
        MarketCategory parentCategory = categoryMapper.selectOne(new LambdaQueryWrapper<MarketCategory>().eq(MarketCategory::getId, currentCategory.getPid()));
        GoodsCategoryVo goodsCategoryVo = GoodsCategoryVo.builder().currentCategory(currentCategory).brotherCategory(brotherCategory).parentCategory(parentCategory).build();
        return goodsCategoryVo;
    }

    @Override
    public GoodsListVo list(final BasePage basePage, final Integer categoryId) {
        GoodsListVo goodsListVo = new GoodsListVo();
        Page<MarketGoods> startPage = PageHelper.startPage(basePage.getPage(), basePage.getLimit());
        // 获取当前分类的前10个商品
        goodsMapper.selectList(new LambdaQueryWrapper<MarketGoods>().eq(MarketGoods::getCategoryId, categoryId));
        goodsListVo.setData(startPage);
        return goodsListVo;
    }

    @Override
    public Boolean insert(Integer userId, String keyword,String searchHistoryFrom) {
        MarketSearchHistory marketSearchHistory = new MarketSearchHistory();
        marketSearchHistory.setUserId(userId);
        marketSearchHistory.setKeyword(keyword);
        marketSearchHistory.setFrom(searchHistoryFrom);
        searchHistoryMapper.insert(marketSearchHistory);
        return null;
    }
}
