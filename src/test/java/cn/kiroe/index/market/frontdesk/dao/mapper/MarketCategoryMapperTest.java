package cn.kiroe.index.market.frontdesk.dao.mapper;

import cn.kiroe.index.market.frontdesk.dao.entity.MarketGoods;
import cn.kiroe.index.market.frontdesk.dao.entity.MarketIssue;
import cn.kiroe.index.market.frontdesk.dao.entity.MarketUser;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


/**
 * @Author Kiro
 * @Date 2023/12/30 20:47
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class MarketCategoryMapperTest {
   // private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    MarketGoodsMapper mapper;

    @Autowired
    MarketIssueMapper issueMapper;

    @Test
    public void testMapper(){
        List<MarketGoods> marketGoods = mapper.selectList(null);
    }

    @Test
    public void testPage(){
        Page<MarketGoods> marketGoodsPage = PageHelper.startPage(1, 3);

        List<MarketGoods> marketGoods = mapper.selectList(null);
        long total = marketGoodsPage.getTotal();
        log.info(String.valueOf(total));
        List<MarketIssue> marketIssues = issueMapper.selectList(null);
        log.info(marketIssues.toString());
        List<MarketGoods> result = marketGoodsPage.getResult();
        log.info(result.toString());
    }

    @Test
    public void logicDelete(){
        mapper.delete(new LambdaQueryWrapper<MarketGoods>()
                .select(MarketGoods::getCategoryId,MarketGoods::getIsNew)
        );
        int i = mapper.deleteById(1006007);
        System.out.println("i = " + i);
    }

    // 自动填充
    @Test
    public void fillTest(){
        MarketIssue entity = new MarketIssue();
        entity.setQuestion("test");
        entity.setAnswer("test");
        issueMapper.insert(entity);
    }

    // 更新
    @Test
    public void updateTest(){
        MarketIssue marketIssue = new MarketIssue();
        marketIssue.setId(9);
        marketIssue.setAnswer("answer");
        marketIssue.setQuestion("qqqqq");
        int i = issueMapper.updateById(marketIssue);
        log.info(String.valueOf(i));
    }

}