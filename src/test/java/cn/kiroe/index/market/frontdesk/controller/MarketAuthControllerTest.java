package cn.kiroe.index.market.frontdesk.controller;

import cn.hutool.Hutool;
import cn.hutool.core.convert.NumberWithFormat;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * @Author Kiro
 * @Date 2024/01/02 14:30
 **/
@Slf4j
public class MarketAuthControllerTest {

    @Test
    public void testJWT(){
        String jwtString = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJ0aGlzIGlzIG1hcmtldCB0b2tlbiIsImF1ZCI6Ik1JTklBUFAiLCJpc3MiOiJNQVJLRVQiLCJleHAiOjE3MDQxODQwMzUsInVzZXJJZCI6MSwiaWF0IjoxNzA0MTc2ODM1fQ.Xr3v72pEM9B834wbafpT9IqZ31aaNDea1VPqWd1W11A";
        JWT jwt = JWTUtil.parseToken(jwtString);
        JWTPayload payload = jwt.getPayload();
        log.info(payload.toString());
        NumberWithFormat exp = (NumberWithFormat) payload.getClaim("exp");
        long l = exp.longValue();
        Date date = new Date(l);
        log.info(date.toString());
    }
}