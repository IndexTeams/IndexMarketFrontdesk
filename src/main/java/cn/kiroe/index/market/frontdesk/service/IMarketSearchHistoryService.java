package cn.kiroe.index.market.frontdesk.service;

import cn.kiroe.index.market.frontdesk.dao.entity.MarketSearchHistory;
import cn.kiroe.index.market.frontdesk.dao.vo.SearchIndexVo;
import cn.kiroe.index.market.frontdesk.dao.vo.common.BaseRespVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 搜索历史表 服务类
 * </p>
 *
 * @author kiro
 * @since 2023-12-29 20:15:32
 */
public interface IMarketSearchHistoryService extends IService<MarketSearchHistory> {
    BaseRespVo<List<String>> selectListByKeyword(String keyword);

    BaseRespVo<SearchIndexVo> index(Integer id);

    Boolean deleteById(Integer id);
}
