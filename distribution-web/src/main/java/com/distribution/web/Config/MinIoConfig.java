package com.distribution.web.Config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MinIO配置
 *
 * @author hacken
 * @email hacken_hu@foxmail.com
 * @date 2021-11-25 11:25:23
 */
@Configuration
public class MinIoConfig {

    @Value("${minio.endpoint}")
    protected String endpoint;

    @Value("${minio.bucketName}")
    protected String bucketName;

    @Value("${minio.accessKey}")
    protected String accessKey;

    @Value("${minio.secretKey}")
    protected String secretKey;

    @Bean
    public MinioClient minioClient() {
        MinioClient client = null;
        try {
            client = new MinioClient(endpoint, accessKey, secretKey);
        } catch (Exception e) {
            new Exception("MinIO对象存储注入失败");
        }
        return client;
    }
}