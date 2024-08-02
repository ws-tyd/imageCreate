package com.jyyy.picturegeneration;

import com.freewayso.image.combiner.ImageCombiner;
import com.freewayso.image.combiner.enums.Direction;
import com.freewayso.image.combiner.enums.OutputFormat;
import com.freewayso.image.combiner.enums.ZoomMode;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

@SpringBootTest
class PictureGenerationApplicationTests {

    @Test
    void contextLoads() {
    }

    void addTextElementTheme(ImageCombiner combiner,String text,int x,int y){
        combiner.addTextElement(text,18,x-1,y-1).setAutoFitWidth(230).setLineHeight(30).setColor(Color.BLUE).setDirection(Direction.CenterLeftRight);
        combiner.addTextElement(text,18,x+1,y+1).setAutoFitWidth(230).setLineHeight(30).setColor(Color.BLUE).setDirection(Direction.CenterLeftRight);
        combiner.addTextElement(text,18,x-1,y+1).setAutoFitWidth(230).setLineHeight(30).setColor(Color.BLUE).setDirection(Direction.CenterLeftRight);
        combiner.addTextElement(text,18,x+1,y-1).setAutoFitWidth(230).setLineHeight(30).setColor(Color.BLUE).setDirection(Direction.CenterLeftRight);
        combiner.addTextElement(text,18,x+3,y+3).setAutoFitWidth(230).setLineHeight(30).setColor(Color.PINK).setDirection(Direction.CenterLeftRight);
        combiner.addTextElement(text,18,x,y).setAutoFitWidth(230).setLineHeight(30).setColor(Color.white).setDirection(Direction.CenterLeftRight);
    }

    @SneakyThrows
    @Test
    void pictureGeneration() {
        BufferedImage image = ImageIO.read(new File("D:\\workDemo\\PictureGeneration\\src\\main\\resources\\static\\img\\games\\17\\lolm0.png"));
        ImageCombiner combiner = new ImageCombiner(image, OutputFormat.PNG);
        int x = 30,y=88;
        addTextElementTheme(combiner,"QQ：",100,85);
        addTextElementTheme(combiner,"英雄数量：",460,40);
        addTextElementTheme(combiner,"皮肤数量：",735,40);
        addTextElementTheme(combiner,"游戏区服：",460,85);
        addTextElementTheme(combiner,"可二次：",735,85);
        combiner.addImageElement("https://imgs.ali213.net/oday/uploadfile/2021/01/08/2021010834637833.jpg",67,185,130,185, ZoomMode.WidthHeight);
        combiner.addImageElement("https://imgs.ali213.net/oday/uploadfile/2021/01/08/2021010834637833.jpg",242,185,130,185, ZoomMode.WidthHeight);
        combiner.addImageElement("https://imgs.ali213.net/oday/uploadfile/2021/01/08/2021010834637833.jpg",417,185,130,185, ZoomMode.WidthHeight);
        combiner.addImageElement("https://imgs.ali213.net/oday/uploadfile/2021/01/08/2021010834637833.jpg",592,185,130,185, ZoomMode.WidthHeight);
        combiner.addImageElement("https://imgs.ali213.net/oday/uploadfile/2021/01/08/2021010834637833.jpg",767,185,130,185, ZoomMode.WidthHeight);
        combiner.addImageElement("https://imgs.ali213.net/oday/uploadfile/2021/01/08/2021010834637833.jpg",67,400,130,185, ZoomMode.WidthHeight);
        combiner.addImageElement("https://imgs.ali213.net/oday/uploadfile/2021/01/08/2021010834637833.jpg",242,400,130,185, ZoomMode.WidthHeight);
        combiner.addImageElement("https://imgs.ali213.net/oday/uploadfile/2021/01/08/2021010834637833.jpg",417,400,130,185, ZoomMode.WidthHeight);
        combiner.addImageElement("https://imgs.ali213.net/oday/uploadfile/2021/01/08/2021010834637833.jpg",592,400,130,185, ZoomMode.WidthHeight);
        combiner.addImageElement("https://imgs.ali213.net/oday/uploadfile/2021/01/08/2021010834637833.jpg",767,400,130,185, ZoomMode.WidthHeight);
//        combiner.addTextElement("英雄数量:",18,390,40).setAutoFitWidth(230).setLineHeight(30);
//        combiner.addTextElement("皮肤数量:",18,665,40).setAutoFitWidth(230).setLineHeight(30);
//        combiner.addTextElement("游戏区服:",18,390,88).setAutoFitWidth(230).setLineHeight(30);
//        combiner.addTextElement("可二次:",18,665,88).setAutoFitWidth(230).setLineHeight(30);
        combiner.combine();
        combiner.save("test.png");
    }
}
