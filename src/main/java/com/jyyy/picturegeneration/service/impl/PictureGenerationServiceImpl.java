package com.jyyy.picturegeneration.service.impl;

import cn.hutool.core.map.MapUtil;
import com.freewayso.image.combiner.ImageCombiner;
import com.freewayso.image.combiner.enums.OutputFormat;
import com.freewayso.image.combiner.enums.ZoomMode;
import com.jyyy.picturegeneration.controller.param.JccMainPictureParam;
import com.jyyy.picturegeneration.controller.param.MLolMainPictureParam;
import com.jyyy.picturegeneration.pojo.jcc.JccHeroes;
import com.jyyy.picturegeneration.pojo.mLol.MlolSkins;
import com.jyyy.picturegeneration.service.PictureGenerationService;
import lombok.SneakyThrows;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PictureGenerationServiceImpl implements PictureGenerationService {

    private static final Logger log = LoggerFactory.getLogger(PictureGenerationServiceImpl.class);
    @Autowired
    private ResourceLoader resourceLoader;

    @Value("${file.localPath}")
    private String localUrl;

    @SneakyThrows
    // 测试
    public BufferedImage generation(Map<String, Object> config) {
        BufferedImage image = ImageIO.read(new File("D:\\workDemo\\PictureGeneration\\src\\main\\resources\\static\\img\\games\\17\\lolm0.png"));
        ImageCombiner combiner = new ImageCombiner(image, OutputFormat.PNG);
        int x = 30, y = 88;
        addTextElementTheme(combiner, "QQ：", 100, 85);
        addTextElementTheme(combiner, "英雄数量：", 460, 40);
        addTextElementTheme(combiner, "皮肤数量：", 735, 40);
        addTextElementTheme(combiner, "游戏区服：", 460, 85);
        addTextElementTheme(combiner, "可二次：", 735, 85);
        combiner.addImageElement("https://imgs.ali213.net/oday/uploadfile/2021/01/08/2021010834637833.jpg", 67, 185, 130, 185, ZoomMode.WidthHeight);
        combiner.addImageElement("https://imgs.ali213.net/oday/uploadfile/2021/01/08/2021010834637833.jpg", 242, 185, 130, 185, ZoomMode.WidthHeight);
        combiner.addImageElement("https://imgs.ali213.net/oday/uploadfile/2021/01/08/2021010834637833.jpg", 417, 185, 130, 185, ZoomMode.WidthHeight);
        combiner.addImageElement("https://imgs.ali213.net/oday/uploadfile/2021/01/08/2021010834637833.jpg", 592, 185, 130, 185, ZoomMode.WidthHeight);
        combiner.addImageElement("https://imgs.ali213.net/oday/uploadfile/2021/01/08/2021010834637833.jpg", 767, 185, 130, 185, ZoomMode.WidthHeight);
        combiner.addImageElement("https://imgs.ali213.net/oday/uploadfile/2021/01/08/2021010834637833.jpg", 67, 400, 130, 185, ZoomMode.WidthHeight);
        combiner.addImageElement("https://imgs.ali213.net/oday/uploadfile/2021/01/08/2021010834637833.jpg", 242, 400, 130, 185, ZoomMode.WidthHeight);
        combiner.addImageElement("https://imgs.ali213.net/oday/uploadfile/2021/01/08/2021010834637833.jpg", 417, 400, 130, 185, ZoomMode.WidthHeight);
        combiner.addImageElement("https://imgs.ali213.net/oday/uploadfile/2021/01/08/2021010834637833.jpg", 592, 400, 130, 185, ZoomMode.WidthHeight);
        combiner.addImageElement("https://imgs.ali213.net/oday/uploadfile/2021/01/08/2021010834637833.jpg", 767, 400, 130, 185, ZoomMode.WidthHeight);
        combiner.combine();
        BufferedImage bufferedImage = combiner.getCombinedImage();
        return bufferedImage;
    }

    @SneakyThrows
    @Override
    public BufferedImage generation(MLolMainPictureParam config) {
        Resource resource = resourceLoader.getResource("classpath:static/img/games/17/lolm0.png");
        BufferedImage image = ImageIO.read(resource.getInputStream());
        ImageCombiner combiner = new ImageCombiner(image, OutputFormat.PNG);
        setTitleInfo(combiner, config.getAccount().getQq(), config.getAccount().getHeroesAmount(), config.getAccount().getSkinsAmount(), config.getAccount().getServerArea());
        List<MlolSkins> heroes = config.getSkins();
        heroes.addAll(config.skins);
        List<Map<String, Object>> map = heroes.stream()
                .map(item -> MapUtil.<String, Object>builder()
                        .put("image", item.getImage())
                        .put("id", item.getId()).build())
                .collect(Collectors.toList());
        Color color = new Color(1, 74, 158);
        addImageElementAutoSize(combiner, map, "17", 40, 135, 890, 480, 130, 185, 2, 5, color);
        combiner.combine();
        BufferedImage bufferedImage = combiner.getCombinedImage();
        return bufferedImage;
    }


    @SneakyThrows
    @Override
    public BufferedImage generation(JccMainPictureParam config) {
        Resource resource = resourceLoader.getResource("classpath:static/img/games/25/main0.png");
        BufferedImage image = ImageIO.read(resource.getInputStream());
        ImageCombiner combiner = new ImageCombiner(image, OutputFormat.PNG);
        setTitleInfo(combiner, config.getAccount().getQq(), config.getAccount().getHeroesAmount(), config.getAccount().getSkinsAmount(), config.getAccount().getServerArea());
        List<JccHeroes> heroes = config.getHeroes();
        heroes.addAll(config.heroes);
        List<Map<String, Object>> map = heroes.stream()
                .map(item -> MapUtil.<String, Object>builder()
                        .put("image", item.getImage())
                        .put("id", item.getId()).build())
                .collect(Collectors.toList());
        Color color = new Color(50, 165, 252);
        addImageElementAutoSize(combiner, map, "25", 40, 135, 890, 480, 110, 110, 3, 6, color);
        combiner.combine();
        BufferedImage bufferedImage = combiner.getCombinedImage();
        return bufferedImage;
    }


    void setTitleInfo(ImageCombiner combiner, String qq, long heroesAmount, long skinsAmount, String serverArea) {
        addTextElementTheme(combiner, "QQ：" + qq, 80, 85);
        addTextElementTheme(combiner, "英雄数量：" + heroesAmount, 440, 40);
        addTextElementTheme(combiner, "皮肤数量：" + skinsAmount, 715, 40);
        addTextElementTheme(combiner, "游戏区服：" + serverArea, 440, 85);
        addTextElementTheme(combiner, "可二次", 715, 85);
    }

    void addTextElementTheme(ImageCombiner combiner, String text, int x, int y) {
        combiner.addTextElement(text, 18, x - 1, y - 1).setLineHeight(30).setColor(Color.BLUE);
        combiner.addTextElement(text, 18, x + 1, y + 1).setLineHeight(30).setColor(Color.BLUE);
        combiner.addTextElement(text, 18, x - 1, y + 1).setLineHeight(30).setColor(Color.BLUE);
        combiner.addTextElement(text, 18, x + 1, y - 1).setLineHeight(30).setColor(Color.BLUE);
        combiner.addTextElement(text, 18, x + 4, y + 2).setLineHeight(30).setColor(Color.PINK).setAlpha(0.7f);
        combiner.addTextElement(text, 18, x, y).setLineHeight(30).setColor(Color.white);
    }

    public void addImageElementAutoSize(ImageCombiner combiner, List<Map<String, Object>> skins,
                                        String gameId, int left, int top,
                                        int detailWidth, int detailHeight,
                                        int elementWidth, int elementHeight,
                                        int numRows, int numCols) {
        addImageElementAutoSize(combiner, skins, gameId, left, top, detailWidth, detailHeight, elementWidth, elementHeight, numRows, numCols, null);
    }

    public void addImageElementAutoSize(ImageCombiner combiner, List<Map<String, Object>> skins,
                                        String gameId, int left, int top,
                                        int detailWidth, int detailHeight,
                                        int elementWidth, int elementHeight,
                                        int numRows, int numCols, Color color) {
        int padding = 30; // 边距
        if (skins.isEmpty()) {
            return;
        }
        int widthPadding = (detailWidth - (numCols * elementWidth)) / numCols;
        int heightPadding = (detailHeight - (numRows * elementHeight)) / numRows;

        // 计算实际盒子之间的间距
        int totalWidth = numCols * (elementWidth + widthPadding) - widthPadding; // 总宽度，减去最后一个元素的边距
        int totalHeight = numRows * (elementHeight + heightPadding) - heightPadding; // 总高度，减去最后一个元素的边距

        if (totalWidth > detailWidth) {
            elementWidth = (detailWidth - (numCols + 1) * padding) / numCols;
            widthPadding = (detailWidth - (numCols * elementWidth)) / numCols;
            totalWidth = numCols * (elementWidth + widthPadding) - widthPadding;
        }
        if (totalHeight > detailHeight) {
            elementHeight = (detailHeight - (numRows + 1) * padding) / numRows;
            heightPadding = (detailHeight - (numRows * elementHeight)) / numRows;
            totalHeight = numRows * (elementHeight + heightPadding) - heightPadding;
        }
        // 计算起始位置，使得小盒子居中对齐在大盒子内
        int startX = left + (detailWidth - totalWidth) / 2;
        int startY = top + (detailHeight - totalHeight) / 2;
        // 开始循环
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                int index = row * numCols + col;
                if (index >= skins.size()) {
                    break; // 如果已经超过列表长度，跳出循环
                }
                int imageX = startX + col * (elementWidth + widthPadding);
                int imageY = startY + row * (elementHeight + heightPadding);
                // 将 element 添加到 combiner 中，这里假设 combiner 提供了相应的方法
                String image = skins.get(index).get("image").toString();
                if (ObjectUtils.isNotEmpty(image)) {
                    if (ObjectUtils.isNotEmpty(color)) {
                        combiner.addRectangleElement(imageX + 8, imageY + 8, elementWidth, elementHeight).setColor(color).setRoundCorner(15);
                    }
                    BufferedImage bufferedImage = loadImage(gameId, skins.get(index).get("id").toString(), image);
                    combiner.addImageElement(bufferedImage, imageX, imageY, elementWidth, elementHeight, ZoomMode.WidthHeight)
                            .setRoundCorner(15);
                }
            }
        }
    }

    public BufferedImage loadImage(String gameId, String skinId, String image) {
        BufferedImage bufferedImage = null;
        try {
            String localPath = localUrl + gameId + "/skins/" + skinId + ".png";
            File file = new File(localPath);
            if (file.exists()) {
                bufferedImage = ImageIO.read(file);
            } else {
                bufferedImage = loadImageFromURL(image);

                // 如果从网络加载成功，备份图片到本地
                if (bufferedImage != null) {
                    backupImageLocally(bufferedImage, localPath);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bufferedImage;
    }

    private BufferedImage loadImageFromURL(String imageUrl) {
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(new URL(imageUrl));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bufferedImage;
    }

    private void backupImageLocally(BufferedImage image, String localPath) {
        try {
            Path outputPath = Paths.get(localPath);
            Files.createDirectories(outputPath.getParent());
            ImageIO.write(image, "png", outputPath.toFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
