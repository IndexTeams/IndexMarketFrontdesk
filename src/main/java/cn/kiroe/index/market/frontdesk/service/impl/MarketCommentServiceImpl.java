package cn.kiroe.index.market.frontdesk.service.impl;

import cn.kiroe.index.market.frontdesk.dao.entity.MarketComment;
import cn.kiroe.index.market.frontdesk.dao.entity.MarketUser;
import cn.kiroe.index.market.frontdesk.dao.mapper.MarketCommentMapper;
import cn.kiroe.index.market.frontdesk.dao.mapper.MarketUserMapper;
import cn.kiroe.index.market.frontdesk.dao.vo.CommentRespVo;
import cn.kiroe.index.market.frontdesk.dao.vo.CommentVo;
import cn.kiroe.index.market.frontdesk.dao.vo.UserInfoVo;
import cn.kiroe.index.market.frontdesk.dao.vo.common.CommonData;
import cn.kiroe.index.market.frontdesk.service.IMarketCommentService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 评论表 服务实现类
 * </p>
 *
 * @author kiro
 * @since 2023-12-29 20:15:32
 */
@Service
@Slf4j
public class MarketCommentServiceImpl extends ServiceImpl<MarketCommentMapper, MarketComment> implements IMarketCommentService {

    @Autowired
    MarketUserMapper userMapper;
    @Autowired
    MarketCommentMapper commentMapper;


    @Override
    public CommonData getCommentList(CommentVo commentVo) {

        PageHelper.startPage(commentVo.getPage(), commentVo.getLimit());

        // 获取market_comment的信息
        LambdaQueryWrapper<MarketComment> queryWrapper = Wrappers.lambdaQuery();

        // 根据showType找出含有图片和不含图片总评论 和 只含有图片的评论
        if (commentVo.getShowType() == 0) {
            // 都查
            queryWrapper.eq(MarketComment::getValueId, commentVo.getValueId());

        } else if (commentVo.getShowType() == 1) {
            // 只查带图片的
            queryWrapper.eq(MarketComment::getValueId, commentVo.getValueId())
                    .eq(MarketComment::getHasPicture, true);
        }

        List<MarketComment> commentList = commentMapper.selectList(queryWrapper);
        PageInfo<MarketComment> pageInfo = new PageInfo<>(commentList);
        CommonData data = CommonData.data(pageInfo);


        List<CommentRespVo> respList = commentList.stream().map(comment -> {
            // 根据user_id查userInfo
            MarketUser user = userMapper.selectById(comment.getUserId());
            return new CommentRespVo().setUserInfo(new UserInfoVo(user.getNickname(), user.getAvatar()))
                    .setAddTime(comment.getAddTime()).setPicList(comment.getPicUrls())
                    .setAdminContent(comment.getAdminContent()).setContent(comment.getContent());
        }).collect(Collectors.toList());

        data.setList(respList);

        return data;
    }



    @Override
    public Map getCommentCount(Integer valueId, Byte type) {
        Long allCount = commentMapper.selectCount(new LambdaQueryWrapper<MarketComment>()
                .eq(MarketComment::getValueId, valueId));

        Long hasPicCount = commentMapper.selectCount(new LambdaQueryWrapper<MarketComment>()
                .eq(MarketComment::getValueId, valueId)
                .eq(MarketComment::getHasPicture, true));

        Map<String, String> data = new HashMap<>();
        data.put("hasPicCount", String.valueOf(hasPicCount));
        data.put("allCount", String.valueOf(allCount));

        return data;
    }

    @Override
    @Transactional
    public MarketComment postComment(MarketComment comment) {
        // 插入数据
        int affectRows = commentMapper.insert(comment);

        if (affectRows != 1) {
            // 回滚事务
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

        return affectRows == 1 ? comment : null;
    }
}
