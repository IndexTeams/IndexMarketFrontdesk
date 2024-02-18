package cn.kiroe.index.market.frontdesk.service;

import cn.kiroe.index.market.frontdesk.dao.entity.MarketUser;
import cn.kiroe.index.market.frontdesk.dao.vo.UserIndexVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author kiro
 * @since 2023-12-29 20:15:32
 */
public interface IMarketUserService extends IService<MarketUser> {

    UserIndexVo index(Integer userId);

    String sendCheckCode(String mobile);

    boolean send(String mobile, String code);
}
