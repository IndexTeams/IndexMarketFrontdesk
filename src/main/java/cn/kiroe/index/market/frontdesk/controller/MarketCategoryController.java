package cn.kiroe.index.market.frontdesk.controller;


import cn.kiroe.index.market.frontdesk.dao.vo.CatalogIndexVo;
import cn.kiroe.index.market.frontdesk.dao.vo.common.BaseRespVo;
import cn.kiroe.index.market.frontdesk.service.IMarketCategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 类目表 前端控制器
 * </p>
 *
 * @author kiro
 * @since 2023-12-29 20:15:32
 */
@RestController
@RequestMapping("/wx/catalog")
@RequiredArgsConstructor
@Tag(name = "分页接口/wx/catalog", description = "分页接口")
public class MarketCategoryController {

    private final IMarketCategoryService service;

    @GetMapping("/index")
    public BaseRespVo<CatalogIndexVo> index() {
        CatalogIndexVo index = service.index();
        return BaseRespVo.ok(index);
    }

    // /wx/catalog/current?id=1005001
    @GetMapping("/current")
    public BaseRespVo<CatalogIndexVo> current(Integer id) {
        CatalogIndexVo indexVo = service.current(id);
        if (indexVo == null) {
            return BaseRespVo.fail("数据库中没有该id的数据");
        }
        return BaseRespVo.ok(indexVo);
    }

}
