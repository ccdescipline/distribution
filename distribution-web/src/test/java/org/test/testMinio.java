package org.test;

import com.distribution.common.Utils.MinioUtils;
import io.minio.MinioClient;
import io.minio.PutObjectOptions;
import io.minio.errors.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.distribution.web.distributionApplication.class)
public class testMinio {
    @Autowired
    private MinioClient minioClient;
    @Value("${minio.bucketName}")
    private String bucketName;
    @Value("${minio.endpoint}")
    private String endpoint;

    @Autowired
    private MinioUtils minioUtils;

    @Test
    public void testgetobject() throws InvalidBucketNameException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        String objectUrl = minioClient.getObjectUrl(bucketName, "5b3345789ca2c.jpg");
        System.out.println(objectUrl);
    }

    @Test
    public void testupload() {


//        InputStream  inputStream = null;
//        //存入
//        try{
//
//            inputStream = new FileInputStream(new File("C:\\Users\\admin\\Desktop\\5b3345789ca2c.jpg"));
////        ByteArrayOutputStream out = new ByteArrayOutputStream();
////        ImageIO.write(bufferedImage, "png", out);
//
//            //初始化 PutObjectOptions
//            PutObjectOptions options = new PutObjectOptions(inputStream.available(),-1);
//            options.setContentType("image/jpeg");
//            minioClient.putObject(bucketName,"testimg.jpg",inputStream,options);
//        }catch (Exception e){
//            e.printStackTrace();
//        }finally {
//            try {
//                inputStream.close();
//            } catch (IOException e) {
//                new Exception("图片上传关闭流失败");
//            }
//
//        }

        try {
            minioUtils.saveImgInMinio(new FileInputStream(new File("C:\\Users\\admin\\Desktop\\21e72fb6-f11b-43aa-aba9-18a08da114c4")),"21e72fb6-f11b-43aa-aba9-18a08da114c4");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


    }
}
