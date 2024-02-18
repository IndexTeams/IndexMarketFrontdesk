package cn.kiroe.index.market.frontdesk.service;

import cn.kiroe.index.market.frontdesk.dao.entity.MarketFootprint;
import cn.kiroe.index.market.frontdesk.dao.vo.common.CommonData;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户浏览足迹表 服务类
 * </p>
 *
 * @author kiro
 * @since 2023-12-29 20:15:32
 */
public interface IMarketFootprintService extends IService<MarketFootprint> {

    CommonData list(Integer page, Integer limit,Integer userId);

    int deleteById(Integer id);
}
