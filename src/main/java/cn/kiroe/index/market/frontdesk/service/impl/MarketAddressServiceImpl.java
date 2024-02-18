package cn.kiroe.index.market.frontdesk.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.kiroe.index.market.frontdesk.dao.entity.MarketAddress;
import cn.kiroe.index.market.frontdesk.dao.mapper.MarketAddressMapper;
import cn.kiroe.index.market.frontdesk.dao.vo.common.CommonData;
import cn.kiroe.index.market.frontdesk.service.IMarketAddressService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 收货地址表 服务实现类
 * </p>
 *
 * @author kiro
 * @since 2023-12-29 20:15:32
 */
@Service
@RequiredArgsConstructor
public class MarketAddressServiceImpl extends ServiceImpl<MarketAddressMapper, MarketAddress> implements IMarketAddressService {
    private final MarketAddressMapper addressMapper;

    @Override
    public CommonData<MarketAddress> listByCommonData(Integer userId) {
        List<MarketAddress> marketAddressList = addressMapper.selectList(
                new LambdaQueryWrapper<MarketAddress>().eq(MarketAddress::getUserId, userId));

        return CommonData.data(marketAddressList);
    }

    @Override
    public MarketAddress detail(Integer id, Integer userId) {
        MarketAddress marketAddress = addressMapper.selectOne(
                new LambdaQueryWrapper<MarketAddress>()
                        .eq(MarketAddress::getUserId, userId)
                        .eq(MarketAddress::getId, id));
        return marketAddress;
    }

    @Override
    public Integer saveReturnId(MarketAddress marketAddress, Integer userId) {
        marketAddress.setUserId(userId);
        // 根据传入的id查询表中是否已有数据
        MarketAddress addressById = addressMapper.selectById(marketAddress.getId());
        // 查询表中是否已有默认地址
        MarketAddress addressIsDefault = addressMapper.selectOne(new LambdaQueryWrapper<MarketAddress>()
                        .eq(MarketAddress::getIsDefault, true)
                        .eq(MarketAddress::getUserId, userId));

        int result = 0;
        if (addressById != null) {
            // 不为空说明表中已有数据，执行更新操作
            // 如果修改为默认地址，以前的默认地址需要取消默认
            if (marketAddress.getIsDefault()) {
                if (addressIsDefault != null) {
                    addressIsDefault.setIsDefault(false);
                    addressMapper.updateById(addressIsDefault);
                }
            }
            result = addressMapper.updateById(marketAddress);

        } else {
            // 如果为空执行添加操作
            // 如果传入的地址是默认地址，且已有默认地址，将原来的默认地址取消
            if (marketAddress.getIsDefault()) {
                if (addressIsDefault != null) {
                    addressIsDefault.setIsDefault(false);
                    addressMapper.updateById(addressIsDefault);
                    result = addressMapper.insert(marketAddress);
                }
            } else {
                result = addressMapper.insert(marketAddress);
            }
        }

        if (result != 1) {
            return null;
        }
        return marketAddress.getId();
    }

    @Override
    public Integer delete(Integer id, Integer userId) {
        int result = addressMapper.delete(new LambdaQueryWrapper<MarketAddress>()
                .eq(MarketAddress::getUserId, userId)
                .eq(MarketAddress::getId, id));
        return result;
    }
}
