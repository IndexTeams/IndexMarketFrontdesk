package cn.kiroe.index.market.frontdesk.service;

import cn.kiroe.index.market.frontdesk.dao.entity.MarketCollect;
import cn.kiroe.index.market.frontdesk.dao.entity.MarketGoods;
import cn.kiroe.index.market.frontdesk.dao.vo.common.CommonData;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 收藏表 服务类
 * </p>
 *
 * @author kiro
 * @since 2023-12-29 20:15:32
 */
public interface IMarketCollectService extends IService<MarketCollect> {

    CommonData listByPage(Integer type, Integer page, Integer limit, Integer userId);

    boolean addOrDelete(Integer type, Integer valueId);
}
