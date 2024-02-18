package cn.kiroe.index.market.frontdesk.service.impl;

import cn.kiroe.index.market.frontdesk.dao.entity.MarketCollect;
import cn.kiroe.index.market.frontdesk.dao.entity.MarketGoods;
import cn.kiroe.index.market.frontdesk.dao.mapper.MarketCollectMapper;
import cn.kiroe.index.market.frontdesk.dao.mapper.MarketGoodsMapper;
import cn.kiroe.index.market.frontdesk.dao.vo.MarketCollectGoods;
import cn.kiroe.index.market.frontdesk.dao.vo.common.CommonData;
import cn.kiroe.index.market.frontdesk.service.IMarketCollectService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 收藏表 服务实现类
 * </p>
 *
 * @author kiro
 * @since 2023-12-29 20:15:32
 */
@Service
@RequiredArgsConstructor
public class MarketCollectServiceImpl extends ServiceImpl<MarketCollectMapper, MarketCollect> implements IMarketCollectService {
    private final MarketCollectMapper collectMapper;
    private final MarketGoodsMapper goodsMapper;

    @Override
    public CommonData listByPage(Integer type, Integer page, Integer limit, Integer userId) {
        // 开启分页
        PageHelper.startPage(page, limit);

        List<MarketCollect> marketCollectList = null;
        if (type != null) {
            marketCollectList = collectMapper.selectList(new LambdaQueryWrapper<MarketCollect>()
                    .eq(MarketCollect::getType, type));
        }
        PageInfo<MarketCollect> pageInfo = new PageInfo<>(marketCollectList);
        CommonData data = CommonData.data(pageInfo);

        List<MarketCollectGoods> collectGoods = marketCollectList.stream().map(marketCollect -> {
            Integer goodsId = marketCollect.getValueId();
            MarketGoods marketGoods = goodsMapper.selectById(goodsId);
            MarketCollectGoods marketCollectGoods = new MarketCollectGoods(marketCollect, marketGoods);
            return marketCollectGoods;
        }).collect(Collectors.toList());
        data.setList(collectGoods);
        return data;
    }

    @Override
    public boolean addOrDelete(Integer type, Integer valueId) {
        int result = collectMapper.delete(new LambdaQueryWrapper<MarketCollect>().eq(MarketCollect::getType, type).eq(MarketCollect::getValueId, valueId));
        return result == 1;
    }
}
