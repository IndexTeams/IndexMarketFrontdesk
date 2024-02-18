package cn.kiroe.index.market.frontdesk.service;

import cn.kiroe.index.market.frontdesk.dao.entity.MarketStorage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 文件存储表 服务类
 * </p>
 *
 * @author kiro
 * @since 2023-12-29 20:15:32
 */
public interface IMarketStorageService extends IService<MarketStorage> {

    MarketStorage upload(MultipartFile imgPart);
}
