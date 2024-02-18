package cn.kiroe.index.market.frontdesk.controller;

import cn.kiroe.index.market.frontdesk.dao.vo.GoodsCategoryVo;
import cn.kiroe.index.market.frontdesk.dao.vo.GoodsListVo;
import cn.kiroe.index.market.frontdesk.dao.vo.common.BasePage;
import cn.kiroe.index.market.frontdesk.dao.vo.common.BaseRespVo;
import cn.kiroe.index.market.frontdesk.service.IMarketGoodsService;
import com.github.pagehelper.util.StringUtil;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * @Author Kiro
 * @Date 2024/01/03 20:08
 **/
@RestController
@RequestMapping("/wx/goods/")
@RequiredArgsConstructor
public class MarketGoodsController {

    private final IMarketGoodsService goodsService;

    @GetMapping("/category")
    public BaseRespVo<GoodsCategoryVo> category(@Valid @NotNull Integer id) {
        GoodsCategoryVo category = goodsService.category(id);
        return BaseRespVo.ok(category);
    }

    @GetMapping("/list")
    public BaseRespVo<GoodsListVo> list(@Valid BasePage basePage, @Valid @Positive Integer categoryId, String keyword, HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()){
            Integer userId = (Integer) subject.getPrincipal();
            String requestURI = request.getRequestURI();
            String searchHistoryFrom = requestURI.substring(requestURI.indexOf("/")+1, 3);
            if (StringUtil.isNotEmpty(keyword)){
                goodsService.insert(userId,keyword,searchHistoryFrom);
            }

            GoodsListVo goodsListVo =  goodsService.list(basePage,categoryId);
            return BaseRespVo.ok(goodsListVo);

        }else {

            GoodsListVo goodsListVo =  goodsService.list(basePage,categoryId);
            return BaseRespVo.ok(goodsListVo);
        }
    }

    @GetMapping("count")
    public BaseRespVo<Long> count(){
        return BaseRespVo.ok(goodsService.count());
    }

}
