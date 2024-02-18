package cn.kiroe.index.market.frontdesk.service;

import cn.kiroe.index.market.frontdesk.dao.entity.MarketAddress;
import cn.kiroe.index.market.frontdesk.dao.vo.common.CommonData;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 收货地址表 服务类
 * </p>
 *
 * @author kiro
 * @since 2023-12-29 20:15:32
 */
public interface IMarketAddressService extends IService<MarketAddress> {

    CommonData<MarketAddress> listByCommonData(Integer userId);

    MarketAddress detail(Integer id, Integer userId);

    Integer saveReturnId(MarketAddress marketAddress, Integer userId);

    Integer delete(Integer id, Integer userId);
}
