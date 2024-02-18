package cn.kiroe.index.market.frontdesk.controller;

import cn.kiroe.index.market.frontdesk.dao.entity.MarketCollect;
import cn.kiroe.index.market.frontdesk.dao.entity.MarketGoods;
import cn.kiroe.index.market.frontdesk.dao.vo.common.BaseParam;
import cn.kiroe.index.market.frontdesk.dao.vo.common.BaseRespVo;
import cn.kiroe.index.market.frontdesk.dao.vo.common.CommonData;
import cn.kiroe.index.market.frontdesk.service.IMarketCollectService;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * @Author Vagrant
 * @Date 2024/01/02 20:23
 **/
@RestController
@RequestMapping("/wx/collect")
@RequiredArgsConstructor
public class MarketCollectController {
    private final IMarketCollectService collectService;

    @GetMapping("/list")
    public BaseRespVo list(@NotNull Integer type,@NotNull Integer page,@NotNull Integer limit) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            Integer userId = (Integer) subject.getPrincipal();
            CommonData data = collectService.listByPage(type, page, limit, userId);
            return BaseRespVo.ok(data);
        }else {
            return BaseRespVo.fail("请先登录");
        }
    }

    @GetMapping("/addordelete")
    public BaseRespVo addOrDelete(@RequestBody Map<String, Integer> map) {
        Subject subject = SecurityUtils.getSubject();
        boolean result = false;
        if (subject.isAuthenticated()) {
            result = collectService.addOrDelete(map.get("type"), map.get("valueId"));
        }
        return result ? BaseRespVo.ok() : BaseRespVo.fail(0, "失败");
    }
}
