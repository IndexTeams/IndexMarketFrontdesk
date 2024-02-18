package cn.kiroe.index.market.frontdesk.service.impl;

import cn.kiroe.index.market.frontdesk.dao.vo.UserIndexVo;
import cn.kiroe.index.market.frontdesk.service.IMarketUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @Author Kiro
 * @Date 2024/01/03 14:59
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MarketUserServiceImplTest {
    @Autowired
    IMarketUserService userService;
    @Test
    public void index() {
        UserIndexVo index = userService.index(1);

    }
}