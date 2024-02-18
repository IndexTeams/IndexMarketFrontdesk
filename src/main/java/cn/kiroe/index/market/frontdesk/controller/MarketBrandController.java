package cn.kiroe.index.market.frontdesk.controller;

import cn.kiroe.index.market.frontdesk.dao.entity.MarketBrand;
import cn.kiroe.index.market.frontdesk.dao.mapper.MarketBrandMapper;
import cn.kiroe.index.market.frontdesk.dao.vo.common.BaseRespVo;
import cn.kiroe.index.market.frontdesk.dao.vo.common.CommonData;
import cn.kiroe.index.market.frontdesk.service.IMarketBrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Vagrant
 * @Date 2024/01/03 21:58
 **/
@RestController
@RequestMapping("/wx/brand")
@RequiredArgsConstructor
public class MarketBrandController {
    private final IMarketBrandService brandService;

    @RequestMapping("/detail")
    public BaseRespVo detail(Integer id) {
        if (id <= 0) {
            return BaseRespVo.fail("参数有误");
        }
        MarketBrand data = brandService.queryById(id);
        return BaseRespVo.ok(data);
    }

    @RequestMapping("/list")
    public BaseRespVo list(Integer page, Integer limit) {
        CommonData data = brandService.listByPage(page, limit);
        return BaseRespVo.ok(data);
    }
}
