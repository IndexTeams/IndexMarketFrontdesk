package cn.kiroe.index.market.frontdesk.service.impl;

import cn.kiroe.index.market.frontdesk.dao.entity.MarketLog;
import cn.kiroe.index.market.frontdesk.dao.mapper.MarketLogMapper;
import cn.kiroe.index.market.frontdesk.service.IMarketLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 操作日志表 服务实现类
 * </p>
 *
 * @author kiro
 * @since 2023-12-29 20:15:32
 */
@Service
public class MarketLogServiceImpl extends ServiceImpl<MarketLogMapper, MarketLog> implements IMarketLogService {

}
