package cn.kiroe.index.market.frontdesk.dao.vo.common;

import lombok.Data;

/**
 * E K T V
 * @author stone
 * @date 2022/01/06 16:24
 */
@Data
public class BaseRespVo<T> {
    int errno;
    T data;
    String errmsg;

    public static <T> BaseRespVo<T> resp(int errno,String errmsg){
        BaseRespVo<T> baseRespVo = new BaseRespVo<>();
        baseRespVo.setErrno(errno);
        baseRespVo.setErrmsg(errmsg);
        return baseRespVo;
    }

    public static <T> BaseRespVo<T> ok(T data) {
        BaseRespVo<T> baseRespVo = new BaseRespVo<>();
        baseRespVo.setErrmsg("成功");
        baseRespVo.setData(data);
        return baseRespVo;
    }
    public static <T> BaseRespVo<T> ok() {
        BaseRespVo<T> baseRespVo = new BaseRespVo<>();
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    public static <T> BaseRespVo<T> invalidData(String msg) {
        BaseRespVo<T> baseRespVo = new BaseRespVo<>();
        baseRespVo.setErrno(504);
        baseRespVo.setErrmsg(msg);
        return baseRespVo;
    }
    public static <T> BaseRespVo<T> invalidData() {
        BaseRespVo<T> baseRespVo = new BaseRespVo<>();
        baseRespVo.setErrno(504);
        baseRespVo.setErrmsg("更新数据已失效");
        return baseRespVo;
    }
    public static <T> BaseRespVo<T> invalidParameter(String msg) {
        BaseRespVo<T> baseRespVo = new BaseRespVo<>();
        baseRespVo.setErrno(400);
        baseRespVo.setErrmsg(msg);
        return baseRespVo;
    }
    public static <T> BaseRespVo<T> unAuthc() {
        BaseRespVo<T> baseRespVo = new BaseRespVo<>();
        baseRespVo.setErrno(502);
        baseRespVo.setErrmsg("认证失败");
        return baseRespVo;
    }
    public static <T> BaseRespVo<T> expired() {
        BaseRespVo<T> baseRespVo = new BaseRespVo<>();
        baseRespVo.setErrno(502);
        baseRespVo.setErrmsg("认证信息过期，请重新登录");
        return baseRespVo;
    }


    public static  BaseRespVo badArgument() {

        return fail(401, "参数不对");
    }

    public static <T> BaseRespVo<T> badArgumentOrOk(T data) {
        if (data == null){
            return fail(401, "参数不对");
        }
        return BaseRespVo.ok(data);

    }

    public static  BaseRespVo fail(int errno, String errmsg) {
        BaseRespVo baseRespVo = new BaseRespVo();
        baseRespVo.setErrno(errno);
        baseRespVo.setErrmsg(errmsg);
        return baseRespVo;
    }
    public static <T> BaseRespVo<T> fail(String errmsg) {
        BaseRespVo<T> baseRespVo = new BaseRespVo<>();
        baseRespVo.setErrno(500);
        baseRespVo.setErrmsg(errmsg);
        return baseRespVo;
    }


}
