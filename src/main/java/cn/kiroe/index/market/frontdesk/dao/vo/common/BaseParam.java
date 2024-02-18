package cn.kiroe.index.market.frontdesk.dao.vo.common;

import cn.hutool.core.bean.BeanUtil;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author stone
 * @date 2022/01/06 16:37
 */
@Data
public class BaseParam extends BasePageInfo {
    // 用来接收request的请求参数信息
    List list;


    public static BaseParam init(Map<String, String[]> parameterMap){
        BaseParam baseParam = new BaseParam();
        BeanUtil.copyProperties(baseParam, parameterMap);
        return baseParam;
    }
}
