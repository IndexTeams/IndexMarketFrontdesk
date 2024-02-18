package cn.kiroe.index.market.frontdesk.service.impl;

import cn.kiroe.index.market.frontdesk.dao.entity.MarketComment;
import cn.kiroe.index.market.frontdesk.dao.entity.MarketOrder;
import cn.kiroe.index.market.frontdesk.dao.entity.MarketUser;
import cn.kiroe.index.market.frontdesk.dao.mapper.MarketCommentMapper;
import cn.kiroe.index.market.frontdesk.dao.mapper.MarketOrderMapper;
import cn.kiroe.index.market.frontdesk.dao.mapper.MarketUserMapper;
import cn.kiroe.index.market.frontdesk.dao.vo.UserIndexVo;
import cn.kiroe.index.market.frontdesk.service.IMarketUserService;
import com.alibaba.cloud.spring.boot.sms.ISmsService;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author kiro
 * @since 2023-12-29 20:15:32
 */
@Service
@RequiredArgsConstructor
public class MarketUserServiceImpl extends ServiceImpl<MarketUserMapper, MarketUser> implements IMarketUserService {

    private final MarketOrderMapper orderMapper;

    private final MarketCommentMapper commentMapper;

    @Autowired// 相当于AsyncClient的作用 短信服务
    private ISmsService smsService;

    @Value("${alibaba.cloud.sms.sign-name}")
    private String signName;

    @Value("${alibaba.cloud.sms.template-code}")
    private String templateCode;

    @Override
    public UserIndexVo index(final Integer userId) {
        // order: {unrecv: 0, uncomment: 5, unpaid: 0, unship: 29}
        // 查询的状态码
        ArrayList<MarketOrder.OrderStatus> orderStatuses = new ArrayList<>();
        orderStatuses.add(MarketOrder.OrderStatus.STATUS_SHIP);
        orderStatuses.add(MarketOrder.OrderStatus.STATUS_CREATE);
        orderStatuses.add(MarketOrder.OrderStatus.STATUS_PAY);
        orderStatuses.add(MarketOrder.OrderStatus.STATUS_CONFIRM);
        // 获得该用户的 状态的订单
        List<MarketOrder> marketOrders = orderMapper.selectList(new LambdaQueryWrapper<MarketOrder>()
                .select(MarketOrder::getOrderStatus)
                .eq(MarketOrder::getUserId, userId)
                .in(MarketOrder::getOrderStatus, orderStatuses.stream().map(MarketOrder.OrderStatus::getValue).collect(Collectors.toList()))
        );
        Map<Integer, List<MarketOrder>> orderMap = marketOrders.stream().collect(Collectors.groupingBy(MarketOrder::getOrderStatus));

        UserIndexVo.DataDTO dataDTO1 = new UserIndexVo.DataDTO();
        // 设置视图状态
        dataDTO1.setUnrecv(orderMap.getOrDefault(MarketOrder.OrderStatus.STATUS_SHIP.getValue(), new ArrayList<>(0)).size());
        dataDTO1.setUnship(orderMap.getOrDefault(MarketOrder.OrderStatus.STATUS_PAY.getValue(), new ArrayList<>(0)).size());
        dataDTO1.setUnpaid(orderMap.getOrDefault(MarketOrder.OrderStatus.STATUS_CREATE.getValue(), new ArrayList<>(0)).size());

        // 获取 用户已经评论的商品数
        List<MarketComment> marketComments = commentMapper.selectList(new LambdaQueryWrapper<MarketComment>().select(MarketComment::getValueId).eq(MarketComment::getUserId, userId));
        Set<Integer> commentValueIds = marketComments.stream().map(MarketComment::getValueId).collect(Collectors.toSet());
        List<MarketOrder> confirmGoods = orderMap.getOrDefault(MarketOrder.OrderStatus.STATUS_CONFIRM, new ArrayList<>());
        // 获取 为评价的 商品
        int unCommentCount = 0;
        for (final MarketOrder confirmGood : confirmGoods) {
            if (!commentValueIds.contains(confirmGood.getId())) {
                unCommentCount++;
            }
        }

        UserIndexVo userIndexVo = new UserIndexVo();
        userIndexVo.setOrder(dataDTO1);
        return userIndexVo;
    }

    @Override
    public String sendCheckCode(String mobile) {
        // 生成验证码
        SecureRandom random = new SecureRandom();
        String code = String.valueOf(random.nextInt(899999) + 100000);
        // 调用发送请求的方法
        boolean sent = this.send(mobile, code);
        if (sent) {
            // 发送成功返回生成的验证码
            return code;
        }
        return null;
    }

    @Override
    public boolean send(String mobile, String code) {
        // 模板中的变量替换JSON串
        String templateParam = "{\"code\":\"" + code + "\"}";
        // 组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        // 必填:待发送手机号
        request.setPhoneNumbers(mobile);
        // 必填:短信签名-可在短信控制台中找到
        request.setSignName(signName);
        // 必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(templateCode);
        request.setTemplateParam(templateParam);
        try {
            SendSmsResponse sendSmsResponse = smsService.sendSmsRequest(request);
            return sendSmsResponse != null;
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return false;
    }
}
