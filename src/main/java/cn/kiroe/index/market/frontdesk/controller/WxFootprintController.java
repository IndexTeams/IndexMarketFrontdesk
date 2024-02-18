package cn.kiroe.index.market.frontdesk.controller;

import cn.kiroe.index.market.frontdesk.dao.vo.common.BaseRespVo;
import cn.kiroe.index.market.frontdesk.dao.vo.common.CommonData;
import cn.kiroe.index.market.frontdesk.service.IMarketFootprintService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Author:  abin
 * Date:  2024/01/03 19:56
 *
 * 足迹功能
 */
@RestController
@RequestMapping("/wx/footprint")
public class WxFootprintController {

    @Autowired
    IMarketFootprintService footprintService;

    @RequestMapping("list")
    public BaseRespVo list(Integer page,Integer limit){
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            Integer userId = (Integer) subject.getPrincipal();
            CommonData data = footprintService.list(page, limit, userId);
            return BaseRespVo.ok(data);
        }else {
            return BaseRespVo.fail("请先登录");
        }
    }

    @RequestMapping("delete")
    public BaseRespVo delete(@RequestBody Map map){
        Integer id = (Integer) map.get("id");
        int affectRows = footprintService.deleteById(id);
        if (affectRows == 1){
            return BaseRespVo.ok();
        }
        return BaseRespVo.fail("删除失败");
    }
}
