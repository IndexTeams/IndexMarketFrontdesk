package cn.kiroe.index.market.frontdesk.service.impl;

import cn.kiroe.index.market.frontdesk.dao.entity.MarketFeedback;
import cn.kiroe.index.market.frontdesk.dao.mapper.MarketFeedbackMapper;
import cn.kiroe.index.market.frontdesk.service.IMarketFeedbackService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 意见反馈表 服务实现类
 * </p>
 *
 * @author kiro
 * @since 2023-12-29 20:15:32
 */
@Service
public class MarketFeedbackServiceImpl extends ServiceImpl<MarketFeedbackMapper, MarketFeedback> implements IMarketFeedbackService {

    @Autowired
    MarketFeedbackMapper feedbackMapper;

    @Override
    public MarketFeedback insert(MarketFeedback marketFeedback) {
        int affectRows = feedbackMapper.insert(marketFeedback);
        return null;
    }
}
