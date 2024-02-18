package cn.kiroe.index.market.frontdesk.service.impl;

import cn.kiroe.index.market.frontdesk.dao.entity.MarketStorage;
import cn.kiroe.index.market.frontdesk.dao.mapper.MarketStorageMapper;
import cn.kiroe.index.market.frontdesk.service.IMarketStorageService;
import com.alibaba.cloud.spring.boot.oss.env.OssProperties;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * <p>
 * 文件存储表 服务实现类
 * </p>
 *
 * @author kiro
 * @since 2023-12-29 20:15:32
 */
@Service
public class MarketStorageServiceImpl extends ServiceImpl<MarketStorageMapper, MarketStorage> implements IMarketStorageService {
    @Autowired
    private OSS ossClient;
    @Autowired
    private OssProperties ossProperties;
    @Autowired
    private MarketStorageMapper storageMapper;
    @Value("${alibaba.cloud.bucket-name}")
    private String bucketName;

    @Override
    public MarketStorage upload(MultipartFile file) {
        String fileName = file.getOriginalFilename();

        String suffix = fileName.substring(fileName.lastIndexOf("."));
        String objectName = UUID.randomUUID().toString() + suffix;
        // String url = urlPrefix + "/" + objectName;

        InputStream inputStream = null;

        try {
            inputStream = file.getInputStream();
            // 保存在哪个bucket、保存的文件名、输入流
            ossClient.putObject(bucketName, objectName, inputStream);
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        // http://wdproject2.oss-cn-beijing.aliyuncs.com/0fac95f9-c82e-4c34-9b48-bfb43b95a84b.jpg
        String endpoint = ossProperties.getEndpoint();
        endpoint = endpoint.substring("https://".length());
        // String accessUrl = OssConfig.BUCKET_NAME + "." + endpoint + "/" + objectName;
        String accessUrl = String.format("https://%s.%s/%s", bucketName, endpoint, objectName);

        // 得到对应的服务器url，准备存入数据库
        MarketStorage storage = new MarketStorage();
        // addTime: "2024-01-04 22:25:28"
        // id: 53
        // key: "y5tprogn550mbepooqsd.jpg"
        storage.setKey(fileName);
        // name: "BI4l5cW6TscI7257830a09ce98aab9e102a3a5e263ca.jpg"
        storage.setName(objectName);
        // size: 33759
        storage.setSize((int) file.getSize());
        // type: "image/jpeg"
        storage.setType(suffix);
        // updateTime: "2024-01-04 22:25:28"
        // url: "http://101.43.69.31:8083/wx/storage/fetch/y5tprogn550mbepooqsd.jpg"
        storage.setUrl(accessUrl);
        // 记得加飘号
        storageMapper.insert(storage);
        return storage;
    }
}
