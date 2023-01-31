package com.distribution.web.controller;

import com.distribution.common.responsResult;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

@RestController
public class ImageController {
/*
    @GetMapping(value = "/img",produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getImage() throws Exception {
        BufferedImage bufferedImage = ImageIO.read(new FileInputStream(new File("C:\\Users\\admin\\Desktop\\5b3345789ca2c.jpg")));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", out);
        return out.toByteArray();
    }
*/
    @PostMapping(value = "/img")
    public responsResult upload(@RequestParam("upfile") MultipartFile file){
        System.out.println(file);
        return  responsResult.success();
    }
}
