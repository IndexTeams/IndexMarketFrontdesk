package cn.kiroe.index.market.frontdesk.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.kiroe.index.market.frontdesk.dao.entity.MarketAddress;
import cn.kiroe.index.market.frontdesk.dao.mapper.MarketAddressMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @Author Vagrant
 * @Date 2024/01/03 10:49
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MarketAddressServiceImplTest {
    @Autowired
    MarketAddressMapper addressMapper;
    @Test
    public void listByCommonData() {
        List<MarketAddress> marketAddressList = addressMapper.selectList(null);
        System.out.println(marketAddressList);
    }

    @Test
    public void detail() {
        MarketAddress marketAddress = addressMapper.selectById(1);
    }

    @Test
    public void saveReturnId() {
        MarketAddress marketAddress = new MarketAddress();
        marketAddress.setId(0);
        marketAddress.setName("wo");
        marketAddress.setIsDefault(true);
        marketAddress.setCity("北京");
        marketAddress.setProvince("市辖区");
        marketAddress.setCounty("东城区");
        marketAddress.setAddressDetail("胡同");
        if (marketAddress.getIsDefault()) {
            List<MarketAddress> marketAddressList = addressMapper.selectList(new
                    LambdaQueryWrapper<MarketAddress>().eq(MarketAddress::getIsDefault, true));
            for (MarketAddress address : marketAddressList) {
                address.setIsDefault(false);
            }
        }
        int result = addressMapper.insert(marketAddress);
        System.out.println(result);
        System.out.println(marketAddress.getId());
    }
}