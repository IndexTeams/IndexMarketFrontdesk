package cn.kiroe.index.market.frontdesk.dao.mapper;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author Kiro
 * @Date 2023/12/30 23:30
 **/
public class LogTest {

    @Test
    public void logTest(){
        Logger logger = LoggerFactory.getLogger(this.getClass());
        logger.debug("debug");
        logger.info("info");
        logger.warn("warn");
        logger.error("error");
    }
}
