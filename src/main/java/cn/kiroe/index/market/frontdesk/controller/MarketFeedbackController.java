package cn.kiroe.index.market.frontdesk.controller;

import cn.kiroe.index.market.frontdesk.dao.entity.MarketFeedback;
import cn.kiroe.index.market.frontdesk.dao.vo.common.BaseRespVo;
import cn.kiroe.index.market.frontdesk.service.IMarketCategoryService;
import cn.kiroe.index.market.frontdesk.service.IMarketFeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author:  abin
 * Date:  2024/01/02 21:00
 *
 * 反馈功能
 */
@RestController
@RequestMapping("/wx/feedback")
public class MarketFeedbackController {

    @Autowired
    IMarketFeedbackService feedbackService;

    @RequestMapping("submit")
    public BaseRespVo create(@RequestBody MarketFeedback marketFeedback){
        MarketFeedback feedback = feedbackService.insert(marketFeedback);
        return BaseRespVo.ok();
    }
}
