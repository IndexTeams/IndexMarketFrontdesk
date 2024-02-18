package cn.kiroe.index.market.frontdesk.service;

import cn.kiroe.index.market.frontdesk.dao.entity.MarketTopic;
import cn.kiroe.index.market.frontdesk.dao.vo.TopicDetailVo;
import cn.kiroe.index.market.frontdesk.dao.vo.common.BaseRespVo;
import cn.kiroe.index.market.frontdesk.dao.vo.common.CommonData;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 专题表 服务类
 * </p>
 *
 * @author kiro
 * @since 2023-12-29 20:15:32
 */
public interface IMarketTopicService extends IService<MarketTopic> {

    BaseRespVo<TopicDetailVo> selectTopicGoods(Integer id);

    BaseRespVo<List<MarketTopic>> related(Integer id);

    CommonData<List<MarketTopic>> list(Integer page, Integer limit);
}
