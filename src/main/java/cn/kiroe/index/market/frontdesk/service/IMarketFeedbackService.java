package cn.kiroe.index.market.frontdesk.service;

import cn.kiroe.index.market.frontdesk.dao.entity.MarketFeedback;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 意见反馈表 服务类
 * </p>
 *
 * @author kiro
 * @since 2023-12-29 20:15:32
 */
public interface IMarketFeedbackService extends IService<MarketFeedback> {

    MarketFeedback insert(MarketFeedback marketFeedback);
}
