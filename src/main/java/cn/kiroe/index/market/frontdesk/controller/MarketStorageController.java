package cn.kiroe.index.market.frontdesk.controller;

import cn.kiroe.index.market.frontdesk.dao.entity.MarketStorage;
import cn.kiroe.index.market.frontdesk.dao.vo.common.BaseRespVo;
import cn.kiroe.index.market.frontdesk.service.IMarketStorageService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author ZDX
 * @Date 2024/01/03 23:11
 **/
@RestController
@RequestMapping("wx/storage")
// @RequiresAuthentication
public class MarketStorageController {
    @Autowired
    IMarketStorageService storageService;

    @PostMapping("upload")
    public BaseRespVo upload(MultipartFile file) {

        MarketStorage data = storageService.upload(file);
        return BaseRespVo.ok(data);
    }
}
