package cn.kiroe.index.market.frontdesk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
// 设置扫描包路径注册mapper
@MapperScan("cn.kiroe.index.market.frontdesk.dao.mapper")
@EnableTransactionManagement
public class IndexMarketFrontdeskApplication {
    public static void main(String[] args) {
        SpringApplication.run(IndexMarketFrontdeskApplication.class, args);
    }
}
