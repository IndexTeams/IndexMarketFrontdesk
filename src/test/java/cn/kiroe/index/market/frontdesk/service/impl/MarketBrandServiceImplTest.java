package cn.kiroe.index.market.frontdesk.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.kiroe.index.market.frontdesk.dao.entity.MarketBrand;
import cn.kiroe.index.market.frontdesk.dao.mapper.MarketBrandMapper;
import cn.kiroe.index.market.frontdesk.dao.vo.BrandListVo;
import cn.kiroe.index.market.frontdesk.dao.vo.common.CommonData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author Vagrant
 * @Date 2024/01/03 22:24
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MarketBrandServiceImplTest {
    @Autowired
    MarketBrandMapper brandMapper;

    @Test
    public void queryById() {
    }

    @Test
    public void listByPage() {
        List<MarketBrand> marketBrands = brandMapper.selectList(null);
        List<BrandListVo> brandListVos = BeanUtil.copyToList(marketBrands, BrandListVo.class);
        System.out.println(CommonData.data(brandListVos));
    }
}