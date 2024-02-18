package cn.kiroe.index.market.frontdesk.controller;

import cn.kiroe.index.market.frontdesk.dao.entity.MarketUser;
import cn.kiroe.index.market.frontdesk.dao.vo.SearchIndexVo;
import cn.kiroe.index.market.frontdesk.dao.vo.common.BaseRespVo;
import cn.kiroe.index.market.frontdesk.dao.vo.common.NotBaseRespVo;
import cn.kiroe.index.market.frontdesk.service.IMarketSearchHistoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @Author: 阿超
 * @Date: 2024年01月02日 19:58
 * @Title: MarketSearchController
 * @Package cn.kiroe.index.market.frontdesk.controller
 */
@RestController
@RequestMapping("/wx/search")
@RequiredArgsConstructor
@Tag(name = "搜索接口/wx/search",description = "搜索接口")
public class MarketSearchController {
    private  final IMarketSearchHistoryService service;

    @GetMapping("/index")
    public BaseRespVo<SearchIndexVo> index(){
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()){
            Integer id = (Integer) subject.getPrincipal();

            return service.index(id);
        }else {
            return BaseRespVo.unAuthc();
        }



    }

    @GetMapping("/helper")
    public BaseRespVo<List<String>> helper(String keyword){

            BaseRespVo<List<String>> listBaseRespVo;
            listBaseRespVo = service.selectListByKeyword(keyword);
            return listBaseRespVo;

    }

    @PostMapping("/clearhistory")
    public NotBaseRespVo clearhistory(){
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()){
            Integer id = (Integer) subject.getPrincipal();
            Boolean aBoolean = service.deleteById(id);
            if (aBoolean){
                return NotBaseRespVo.ok();
            }else {
                return NotBaseRespVo.fail(0,"失败");
            }
        }else {
            return NotBaseRespVo.fail(502,"认证失败");
        }

    }


}
