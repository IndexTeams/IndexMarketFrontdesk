package cn.kiroe.index.market.frontdesk.service;

import cn.kiroe.index.market.frontdesk.dao.entity.MarketComment;
import cn.kiroe.index.market.frontdesk.dao.vo.CommentVo;
import cn.kiroe.index.market.frontdesk.dao.vo.common.CommonData;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 评论表 服务类
 * </p>
 *
 * @author kiro
 * @since 2023-12-29 20:15:32
 */
public interface IMarketCommentService extends IService<MarketComment> {

    CommonData getCommentList(CommentVo commentVo);

    Map getCommentCount(Integer valueId, Byte type);

    MarketComment postComment(MarketComment comment);
}
