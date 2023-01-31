package com.distribution.common.Utils;

import io.minio.MinioClient;
import io.minio.PutObjectOptions;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
public class MinioUtils {
    @Autowired
    private MinioClient minioClient;
    @Value("${minio.bucketName}")
    private String bucketName;
    @Value("${minio.endpoint}")
    private String endpoint;

    public boolean saveImgInMinio(InputStream imgStream,String filekey){
        //存入
        try{

            //imgStream = new FileInputStream(new File("C:\\Users\\admin\\Desktop\\5b3345789ca2c.jpg"));
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        ImageIO.write(bufferedImage, "png", out);

            //初始化 PutObjectOptions
            PutObjectOptions options = new PutObjectOptions(imgStream.available(),-1);
            options.setContentType("image/jpeg");
            minioClient.putObject(bucketName,filekey,imgStream,options);


        }catch (Exception e){
            e.printStackTrace();
            return false;

        }finally {
            try {
                imgStream.close();
                return true;
            } catch (IOException e) {
                new Exception("图片上传关闭流失败");
            }

        }

        return true;
    }

    public  String getObjectUrlByName(String name) throws InvalidBucketNameException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {

        return minioClient.getObjectUrl(bucketName,name);
    }
}
