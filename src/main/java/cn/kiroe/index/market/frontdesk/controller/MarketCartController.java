package cn.kiroe.index.market.frontdesk.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.kiroe.index.market.frontdesk.dao.entity.MarketCart;
import cn.kiroe.index.market.frontdesk.dao.vo.CartCheckedParamVo;
import cn.kiroe.index.market.frontdesk.dao.vo.CartIndexVo;
import cn.kiroe.index.market.frontdesk.dao.vo.CartUpdateParamVo;
import cn.kiroe.index.market.frontdesk.dao.vo.common.BaseRespVo;
import cn.kiroe.index.market.frontdesk.service.IMarketCartService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

/**
 * @Author Kiro
 * @Date 2024/01/03 21:31
 **/
@RestController
@RequestMapping("/wx/cart")
@RequiredArgsConstructor
@RequiresAuthentication
public class MarketCartController {
    private final  IMarketCartService cartService;


    public Integer getUserId(){
        return  (Integer) SecurityUtils.getSubject().getPrincipal();
    }
    @GetMapping("/index")
    public BaseRespVo<CartIndexVo> index(){
        Integer userId = (Integer) SecurityUtils.getSubject().getPrincipal();
        CartIndexVo cartIndexVo =  cartService.index(userId);
        return BaseRespVo.ok(cartIndexVo);
    }
    @PostMapping("/checked")
    public BaseRespVo<CartIndexVo> checked(@RequestBody @Valid CartCheckedParamVo cartCheckedParamVo){
        cartService.updateChecked(getUserId(),cartCheckedParamVo);
        return index();
    }

    @PostMapping("/update")
    public BaseRespVo update(@RequestBody @Valid CartUpdateParamVo updateParamVo){
        MarketCart marketCart = BeanUtil.copyProperties(updateParamVo, MarketCart.class);
        boolean success = cartService.updateById(marketCart);
        return success?BaseRespVo.ok():BaseRespVo.fail("更新失败");
    }

    @PostMapping("/delete")
    @Transactional
    public BaseRespVo delete(@RequestBody Map<String,Integer[]> map){
        Integer[] productIds = map.get("productIds");
        cartService.delete(getUserId(),productIds);
        return index();
    }


}
