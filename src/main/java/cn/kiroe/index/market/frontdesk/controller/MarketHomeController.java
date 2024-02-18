package cn.kiroe.index.market.frontdesk.controller;

import cn.kiroe.index.market.frontdesk.dao.vo.HomeIndexVo;
import cn.kiroe.index.market.frontdesk.dao.vo.common.BaseRespVo;
import cn.kiroe.index.market.frontdesk.service.MarketHomeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Kiro
 * @Date 2023/12/29 23:15
 **/
@RestController
@RequestMapping("/wx/home")
@RequiredArgsConstructor
@Tag(name = "主页接口")
public class MarketHomeController {
    private final MarketHomeService service;

    // http://101.43.69.31:8083/wx/home/index
    @GetMapping("/index")
    public BaseRespVo<HomeIndexVo> index(){
        HomeIndexVo indexVo = service.index();
        return BaseRespVo.ok(indexVo);
    }

}
