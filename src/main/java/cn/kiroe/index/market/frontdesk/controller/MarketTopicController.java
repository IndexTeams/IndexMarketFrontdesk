package cn.kiroe.index.market.frontdesk.controller;

import cn.kiroe.index.market.frontdesk.dao.entity.MarketTopic;
import cn.kiroe.index.market.frontdesk.dao.vo.TopicDetailVo;
import cn.kiroe.index.market.frontdesk.dao.vo.common.BaseRespVo;
import cn.kiroe.index.market.frontdesk.dao.vo.common.CommonData;
import cn.kiroe.index.market.frontdesk.service.IMarketTopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: 阿超
 * @Date: 2024年01月03日 20:16
 * @Title: MarketTopicController
 * @Package cn.kiroe.index.market.frontdesk.controller
 */
@RestController
@RequestMapping("wx/topic")
@RequiredArgsConstructor
public class MarketTopicController {
    private final IMarketTopicService topicService;

    @GetMapping("/list")
    public BaseRespVo list(Integer page,Integer limit){

        CommonData<List<MarketTopic>> topicCommonData = topicService.list(page, limit);
        return BaseRespVo.ok(topicCommonData);
    }

    @GetMapping("/detail")
    public BaseRespVo<TopicDetailVo> detail(Integer id){

        return topicService.selectTopicGoods(id);
    }

    @GetMapping("/related")
    public  BaseRespVo<List<MarketTopic>> related(Integer id){
        return topicService.related(id);
    }




}
