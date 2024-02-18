package cn.kiroe.index.market.frontdesk.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.kiroe.index.market.frontdesk.dao.entity.MarketBrand;
import cn.kiroe.index.market.frontdesk.dao.mapper.MarketBrandMapper;
import cn.kiroe.index.market.frontdesk.dao.vo.BrandListVo;
import cn.kiroe.index.market.frontdesk.dao.vo.common.CommonData;
import cn.kiroe.index.market.frontdesk.service.IMarketBrandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 品牌商表 服务实现类
 * </p>
 *
 * @author kiro
 * @since 2023-12-29 20:15:32
 */
@Service
@RequiredArgsConstructor
public class MarketBrandServiceImpl extends ServiceImpl<MarketBrandMapper, MarketBrand> implements IMarketBrandService {
    private final MarketBrandMapper brandMapper;
    @Override
    public MarketBrand queryById(Integer id) {
        MarketBrand marketBrand = brandMapper.selectById(id);
        return marketBrand;
    }

    @Override
    public CommonData listByPage(Integer page, Integer limit) {
        List<MarketBrand> marketBrands = brandMapper.selectList(null);
        List<BrandListVo> brandListVos = BeanUtil.copyToList(marketBrands, BrandListVo.class);
        return CommonData.data(brandListVos);
    }
}
