package cn.kiroe.index.market.frontdesk.config;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author Kiro
 * @Date 2024/01/03 09:58
 **/
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RedisConfigTest {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Test
    public void redisTest(){

        redisTemplate.opsForValue().set("valueTest","test");
    }
}