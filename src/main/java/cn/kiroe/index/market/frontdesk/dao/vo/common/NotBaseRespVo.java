package cn.kiroe.index.market.frontdesk.dao.vo.common;

import lombok.Data;

/**
 * @Author: 阿超
 * @Date: 2023年12月16日 11:35
 * @Title: NotBaseRespVo
 * @Package cn.kiroe.index.market.bean.vo.common
 */
@Data
public class NotBaseRespVo {
    int errno;
    String errmsg;

    public static NotBaseRespVo  ok() {
        NotBaseRespVo notBaseRespVo = new NotBaseRespVo();
        notBaseRespVo.setErrno(0);
        notBaseRespVo.setErrmsg("成功");
        return notBaseRespVo;
    }

    public static NotBaseRespVo fail(int errno,String errmsg){
        NotBaseRespVo notBaseRespVo = new NotBaseRespVo();
       notBaseRespVo.setErrno(errno);
       notBaseRespVo.setErrmsg(errmsg);
        return notBaseRespVo;
    }

}
