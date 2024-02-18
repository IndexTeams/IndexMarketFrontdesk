package cn.kiroe.index.market.frontdesk.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author ZDX
 * @Date 2024/01/05 16:56
 **/
public class CheckCodeHolder {

    // 暂时存放手机号对应的验证码
    // key:手机号
    // value:验证码
    private static Map<String, String> checkCode = new HashMap<>();

    public static String getCode(String mobile) {
        return checkCode.getOrDefault(mobile, null);
    }
    public static void save(String mobile,String code) {
        String put = checkCode.put(mobile, code);
    }
}
