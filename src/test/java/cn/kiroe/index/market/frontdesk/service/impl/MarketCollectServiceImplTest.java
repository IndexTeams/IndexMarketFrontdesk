package cn.kiroe.index.market.frontdesk.service.impl;

import cn.kiroe.index.market.frontdesk.dao.entity.MarketCollect;
import cn.kiroe.index.market.frontdesk.dao.mapper.MarketCollectMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author Vagrant
 * @Date 2024/01/02 22:57
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MarketCollectServiceImplTest {
    @Autowired
    MarketCollectMapper collectMapper;
    @Test
    public void listByPage() {
        PageHelper.startPage(1, 10);
        List<MarketCollect> marketCollectList = collectMapper.selectList(new
                LambdaQueryWrapper<MarketCollect>().eq(MarketCollect::getType, 0));
        PageInfo<MarketCollect> pageInfo = new PageInfo<>(marketCollectList);
        System.out.println(marketCollectList);
        System.out.println(pageInfo);
    }
}