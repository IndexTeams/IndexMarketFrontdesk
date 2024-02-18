package cn.kiroe.index.market.frontdesk.service.impl;

import cn.kiroe.index.market.frontdesk.dao.entity.MarketCategory;
import cn.kiroe.index.market.frontdesk.dao.vo.CatalogIndexVo;
import cn.kiroe.index.market.frontdesk.dao.mapper.MarketCategoryMapper;
import cn.kiroe.index.market.frontdesk.service.IMarketCategoryService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 类目表 服务实现类
 * </p>
 *
 * @author kiro
 * @since 2023-12-29 20:15:32
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class MarketCategoryServiceImpl extends ServiceImpl<MarketCategoryMapper, MarketCategory> implements IMarketCategoryService {

    private final MarketCategoryMapper mapper;

    @Override
    public CatalogIndexVo index() {
        CatalogIndexVo catalogIndexVo = new CatalogIndexVo();
        List<MarketCategory> marketCategories = mapper.selectList(null);
        if (marketCategories.isEmpty()) {
            log.error("marketCategories没有获取数据");
            new Exception("categories没数据");
        }
        // 分为三部分
        // 1. 当前分类
        // 第一次默认 为第一个
        List<MarketCategory> fatherCollect = marketCategories.stream().filter(marketCategory -> marketCategory.getPid() == 0)
                .sorted(((o1, o2) -> o1.getId() - o2.getId()))
                .collect(Collectors.toList());
        MarketCategory currentCategory = fatherCollect.stream().findFirst().get();
        catalogIndexVo.setCurrentCategory(currentCategory);
        // 2. 分类列表
        catalogIndexVo.setCategoryList(fatherCollect);
        // 3. 当前分类的子列表
        List<MarketCategory> childClassHeading = marketCategories.stream()
                .filter(marketCategory -> marketCategory.getPid().intValue() == currentCategory.getId())
                .collect(Collectors.toList());
        catalogIndexVo.setCurrentSubCategory(childClassHeading);
        return catalogIndexVo;
    }

    @Override
    public CatalogIndexVo current(final Integer id) {
        MarketCategory marketCategory = mapper.selectById(id);
        if (marketCategory == null){
            return null;
        }
        CatalogIndexVo catalogIndexVo = new CatalogIndexVo();
        catalogIndexVo.setCurrentCategory(marketCategory);
        // 获取 当前子分类
        catalogIndexVo.setCurrentSubCategory(
                mapper.selectList(new LambdaQueryWrapper<MarketCategory>()
                .eq(MarketCategory::getPid,id)));
        return catalogIndexVo;
    }
}
