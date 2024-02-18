package cn.kiroe.index.market.frontdesk.controller;

import cn.kiroe.index.market.frontdesk.dao.entity.MarketComment;
import cn.kiroe.index.market.frontdesk.dao.vo.CommentVo;
import cn.kiroe.index.market.frontdesk.dao.vo.common.BaseRespVo;
import cn.kiroe.index.market.frontdesk.dao.vo.common.CommonData;
import cn.kiroe.index.market.frontdesk.service.IMarketCommentService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * ClassName: WxCommentController
 * Package: cn.kiroe.index.market.frontdesk.controller
 * Description:
 *
 * @Author: 李家合
 * @Create: 2024/1/2 20:09
 */

@RestController
@RequestMapping("/wx/comment")
public class WxCommentController {

    @Autowired
    IMarketCommentService commentService;

    @RequestMapping("/list")
    public BaseRespVo list(CommentVo commentVo) {
        // 不需要登录
        CommonData data = commentService.getCommentList(commentVo);
        return BaseRespVo.ok(data);
    }

    @RequestMapping("/count")
    public BaseRespVo count(Integer valueId, Byte type) {
        // 不需要登录
        Map data = commentService.getCommentCount(valueId, type);
        return BaseRespVo.ok(data);
    }

    @RequestMapping("/post")
    public BaseRespVo post(@RequestBody MarketComment comment) {
        // 需要登录
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            // 有登录信息
            Integer userId = (Integer) subject.getPrincipal();
            comment.setUserId(userId);
            MarketComment data = commentService.postComment(comment);
            if (data != null) {
                // 插入成功
                return BaseRespVo.ok(data);
            }
            // 插入失败
            return BaseRespVo.fail("评论失败");
        }

        // 没有登录信息
        return BaseRespVo.fail("请先登录");

    }
}
