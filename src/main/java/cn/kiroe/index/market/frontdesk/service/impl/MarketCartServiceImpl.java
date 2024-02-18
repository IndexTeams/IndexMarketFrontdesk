package cn.kiroe.index.market.frontdesk.service.impl;

import cn.kiroe.index.market.frontdesk.dao.entity.MarketCart;
import cn.kiroe.index.market.frontdesk.dao.mapper.MarketCartMapper;
import cn.kiroe.index.market.frontdesk.dao.vo.CartCheckedParamVo;
import cn.kiroe.index.market.frontdesk.dao.vo.CartIndexVo;
import cn.kiroe.index.market.frontdesk.service.IMarketCartService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>
 * 购物车商品表 服务实现类
 * </p>
 *
 * @author kiro
 * @since 2023-12-29 20:15:32
 */
@Service
@RequiredArgsConstructor
public class MarketCartServiceImpl extends ServiceImpl<MarketCartMapper, MarketCart> implements IMarketCartService {
    private final MarketCartMapper cartMapper;

    @Override
    public CartIndexVo index(final Integer userId) {
        // 获取list
        List<MarketCart> cartList = cartMapper.selectList(new LambdaQueryWrapper<MarketCart>().eq(MarketCart::getUserId, userId));
        // 获取统计，总数
        List<MarketCart> checkedCartList = cartList.stream().filter(c -> c.getChecked()).collect(Collectors.toList());
        CartIndexVo cartIndexVo = new CartIndexVo();
        // 设置 list
        cartIndexVo.setCartList(cartList);
        // 设置统计信息
        Optional<BigDecimal> sumOptional = cartList.stream().map(MarketCart::getPrice).reduce(BigDecimal::add);
        Optional<BigDecimal> sumCheckedOptional = checkedCartList.stream().map(MarketCart::getPrice).reduce(BigDecimal::add);
        // 设置值
        CartIndexVo.CartTotalDTO cartTotalDTO = CartIndexVo.CartTotalDTO.builder()
                                                                 .checkedGoodsCount(checkedCartList.size())
                                                                 .checkedGoodsAmount(sumCheckedOptional.orElse(new BigDecimal("0")).intValue())
                                                                 .goodsAmount(sumOptional.orElse(new BigDecimal("0")).intValue())
                                                                 .goodsCount(cartList.size()).build();
        cartIndexVo.setCartTotal(cartTotalDTO);
        return cartIndexVo;
    }

    @Override
    public void updateChecked(final Integer userId,final CartCheckedParamVo cartCheckedParamVo) {
        // 主要获取
        // 更新，更具 productId 获取id，然后更新
        // 查询到 cart的id即可
        // 循环更新
        List<Integer> productIds = cartCheckedParamVo.getProductIds();
        boolean checkout  = cartCheckedParamVo.getIsChecked() == 1;
        for (final Integer productId : productIds) {
            MarketCart marketCart = new MarketCart();
            marketCart.setChecked(checkout);
            int update = cartMapper.update(marketCart,
                    new LambdaUpdateWrapper<MarketCart>().eq(MarketCart::getProductId,
                    productId).eq(MarketCart::getUserId,userId));
            if(update !=  1){
                throw new RuntimeException("更新失败");
            }
        }
    }

    @Override
    public void delete(final Integer userId,final Integer... productIds) {
        // 循环删除
        for (final Integer productId : productIds) {
            int delete = cartMapper.delete(new LambdaQueryWrapper<MarketCart>()
                    .eq(MarketCart::getProductId, productId)
                    .eq(MarketCart::getUserId, userId));
            if (delete != 1){
                throw new RuntimeException("删除失败");
            }
        }

    }


}
