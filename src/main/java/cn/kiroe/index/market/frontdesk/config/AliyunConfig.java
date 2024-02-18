package cn.kiroe.index.market.frontdesk.config;

import com.alibaba.cloud.spring.boot.sms.ISmsService;
import com.aliyun.oss.OSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author ZDX
 * @Date 2024/01/04 17:15
 **/
@Configuration
public class AliyunConfig {

    @Autowired
    private OSS ossClient;

    @Value("${alibaba.cloud.bucket-name}")
    public String bucketName;

    // 阿里云OSS服务
    // 只用到了注册的oss实体类 自动配置类starter已经实现好了
    @Bean
    public AppRunner appRunner() {
        return new AppRunner();
    }

    class AppRunner implements ApplicationRunner {

        @Override
        public void run(ApplicationArguments args) throws Exception {
            try {
                // 检查指定的OSS存储桶是否存在，如果不存在则创建。
                if (!ossClient.doesBucketExist(bucketName)) {
                    ossClient.createBucket(bucketName);
                }
            } catch (Exception e) {
                System.err.println("oss handle bucket error: " + e.getMessage());
                System.exit(-1);
            }
        }
    }
}
