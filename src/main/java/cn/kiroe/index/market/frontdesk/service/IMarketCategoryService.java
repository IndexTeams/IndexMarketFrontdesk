package cn.kiroe.index.market.frontdesk.service;

import cn.kiroe.index.market.frontdesk.dao.entity.MarketCategory;
import cn.kiroe.index.market.frontdesk.dao.vo.CatalogIndexVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 类目表 服务类
 * </p>
 *
 * @author kiro
 * @since 2023-12-29 20:15:32
 */
public interface IMarketCategoryService extends IService<MarketCategory> {

    CatalogIndexVo index();

    CatalogIndexVo current(Integer id);
}
