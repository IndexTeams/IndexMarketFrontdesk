package cn.kiroe.index.market.frontdesk.service.impl;

import cn.kiroe.index.market.frontdesk.dao.entity.MarketGoods;
import cn.kiroe.index.market.frontdesk.dao.entity.MarketTopic;
import cn.kiroe.index.market.frontdesk.dao.mapper.MarketGoodsMapper;
import cn.kiroe.index.market.frontdesk.dao.mapper.MarketTopicMapper;
import cn.kiroe.index.market.frontdesk.dao.vo.TopicDetailVo;
import cn.kiroe.index.market.frontdesk.dao.vo.common.BaseRespVo;
import cn.kiroe.index.market.frontdesk.dao.vo.common.CommonData;
import cn.kiroe.index.market.frontdesk.service.IMarketTopicService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 专题表 服务实现类
 * </p>
 *
 * @author kiro
 * @since 2023-12-29 20:15:32
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class MarketTopicServiceImpl extends ServiceImpl<MarketTopicMapper, MarketTopic> implements IMarketTopicService {
         private final MarketTopicMapper topicMapper;
         private final MarketGoodsMapper goodsMapper;
    @Override
    public BaseRespVo<TopicDetailVo> selectTopicGoods(Integer id) {
        MarketTopic marketTopic = topicMapper.selectById(id);
        String[] goodsIds = marketTopic.getGoods();
        QueryWrapper<MarketGoods> queryWrapper = new QueryWrapper<>();
        if (goodsIds!=null && goodsIds.length>0){
            queryWrapper.in("id", (Object[]) goodsIds);
        }else {
            return  BaseRespVo.ok(null);
        }
        queryWrapper.select("id","name","brief","pic_url","is_new","is_hot","counter_price","retail_price");
        List<MarketGoods> marketGoods = goodsMapper.selectList(queryWrapper);
        TopicDetailVo topicDetailVo = new TopicDetailVo();
        topicDetailVo.setTopic(marketTopic);
        topicDetailVo.setGoods(marketGoods);
        return BaseRespVo.ok(topicDetailVo);
    }

    @Override
    public BaseRespVo<List<MarketTopic>> related(Integer id) {
        MarketTopic marketTopic = topicMapper.selectById(id);
        String[] goodsIds = marketTopic.getGoods();
        QueryWrapper<MarketTopic> queryWrapper = new QueryWrapper<>();
        if (goodsIds!=null && goodsIds.length>0){
            queryWrapper.in("id", (Object[]) goodsIds);
        }else {
            return  BaseRespVo.ok(null);
        }

        List<MarketTopic> marketTopics = topicMapper.selectList(queryWrapper);

        return BaseRespVo.ok(marketTopics);
    }

    @Override
    public CommonData<List<MarketTopic>> list(Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        QueryWrapper<MarketTopic> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id","title","subtitle","price","read_count","pic_url");
        List<MarketTopic> marketTopics = topicMapper.selectList(queryWrapper);
        PageInfo<MarketTopic> marketTopicPageInfo = new PageInfo<>(marketTopics);

        return CommonData.data(marketTopicPageInfo);
    }
}
