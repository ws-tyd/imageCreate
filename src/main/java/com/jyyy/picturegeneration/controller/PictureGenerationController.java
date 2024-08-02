package com.jyyy.picturegeneration.controller;

import com.jyyy.picturegeneration.controller.param.JccMainPictureParam;
import com.jyyy.picturegeneration.controller.param.MLolMainPictureParam;
import com.jyyy.picturegeneration.service.PictureGenerationService;
import com.jyyy.picturegeneration.utils.FreemarkerUtil;
import com.jyyy.picturegeneration.utils.LoadTemplatesUtils;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
public class PictureGenerationController {

    @Autowired
    private PictureGenerationService pictureGenerationService;

    @GetMapping("/test")
    public String test() {
        return "test";
    }

//    @PostMapping("/getImage")
//    public void getImage(@RequestBody MLolMainPictureParam param, HttpServletResponse response){
//        try {
////            GenerationPictureUtils.turnImagePng(LoadTemplatesUtils.getTemplateByClassLoader("m_lol.ftl",param.buildDataMap()),response);
//            FreemarkerUtil.html2Img(LoadTemplatesUtils.getTemplateByClassLoader("m_lol.ftl",param.buildDataMap()),response,960,630);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    @PostMapping("/getImage/mlol")
    public void getImageMlol(@RequestBody MLolMainPictureParam param, HttpServletResponse response) {
        BufferedImage image = pictureGenerationService.generation(param);
        returnImage(response,image);
    }
    @PostMapping("/getImage/jcc")
    public void getImageJcc(@RequestBody JccMainPictureParam param, HttpServletResponse response) {
        BufferedImage image = pictureGenerationService.generation(param);
        returnImage(response,image);
    }

    public void returnImage(HttpServletResponse response, BufferedImage image) {
        // 设置响应头，指定为PNG格式
        response.setContentType("image/png");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");
        // 将图像写入响应输出流
        try {
            ImageIO.write(image, "png", response.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
