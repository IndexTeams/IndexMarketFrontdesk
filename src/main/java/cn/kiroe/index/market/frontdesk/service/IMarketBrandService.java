package cn.kiroe.index.market.frontdesk.service;

import cn.kiroe.index.market.frontdesk.dao.entity.MarketBrand;
import cn.kiroe.index.market.frontdesk.dao.vo.common.CommonData;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 品牌商表 服务类
 * </p>
 *
 * @author kiro
 * @since 2023-12-29 20:15:32
 */
public interface IMarketBrandService extends IService<MarketBrand> {

    MarketBrand queryById(Integer id);

    CommonData listByPage(Integer page, Integer limit);
}
