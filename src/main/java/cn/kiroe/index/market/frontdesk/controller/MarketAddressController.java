package cn.kiroe.index.market.frontdesk.controller;

import cn.kiroe.index.market.frontdesk.dao.entity.MarketAddress;
import cn.kiroe.index.market.frontdesk.dao.vo.common.BaseRespVo;
import cn.kiroe.index.market.frontdesk.dao.vo.common.CommonData;
import cn.kiroe.index.market.frontdesk.service.IMarketAddressService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author Vagrant
 * @Date 2024/01/03 10:37
 **/
@RestController
@RequestMapping("/wx/address")
public class MarketAddressController {
    @Autowired
    IMarketAddressService addressService;

    @GetMapping("/list")
    public BaseRespVo list() {
        Integer userId = (Integer) SecurityUtils.getSubject().getPrincipal();
        CommonData<MarketAddress> data = addressService.listByCommonData(userId);
        return BaseRespVo.ok(data);
    }

    @GetMapping("/detail")
    public BaseRespVo detail(Integer id) {
        Integer userId = (Integer) SecurityUtils.getSubject().getPrincipal();
        MarketAddress data = addressService.detail(id, userId);
        return BaseRespVo.ok(data);
    }

    @RequestMapping("/save")
    public BaseRespVo save(@RequestBody MarketAddress marketAddress) {
        Integer userId = (Integer) SecurityUtils.getSubject().getPrincipal();
        
        if (marketAddress.getTel().length() != 11) {
            return BaseRespVo.fail("手机号码格式有误");
        }
        Integer data = addressService.saveReturnId(marketAddress, userId);
        if (data == null || data == 0) {
            return BaseRespVo.fail("保存失败");
        }
        return BaseRespVo.ok(data);
    }

    @RequestMapping("/delete")
    public BaseRespVo delete(@RequestBody Map map) {
        Integer userId = (Integer) SecurityUtils.getSubject().getPrincipal();
        Integer id = (Integer) map.get("id");
        if (id <= 0) {
            return BaseRespVo.fail("参数有误");
        }
        Integer result = addressService.delete(id, userId);
        if (result != 1) {
            return BaseRespVo.fail("删除失败");
        }
        return BaseRespVo.ok();
    }
}
