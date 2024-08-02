package com.jyyy.picturegeneration.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Document;
import org.xhtmlrenderer.layout.SharedContext;
import org.xhtmlrenderer.swing.Java2DRenderer;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * @description 工具类
 */
@Slf4j
public class FreemarkerUtil {

    private static Configuration config = null;

    /**
     * 初始化获取html模板
     */
    static {
        config = new Configuration(Configuration.VERSION_2_3_20);
        config.setDefaultEncoding("UTF-8");
        try {
            config.setClassForTemplateLoading(FreemarkerUtil.class, "/templates");
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.toString());
        }
    }

    /**
     * 把BufferedImage 图片转base64
     *
     * @param bufferedImage
     * @return
     * @throws Exception
     */
    private static String bufferedImageToBase64(BufferedImage bufferedImage) throws Exception {
        String png_base64;//转换成base64串
        //io流
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            ImageIO.write(bufferedImage, "png", baos);//写入流中
            byte[] bytes = baos.toByteArray();//转换成字节
            png_base64 = Base64.getEncoder().encodeToString(bytes);
            png_base64 = png_base64.replaceAll("\n", "").replaceAll("\r", "");//删除 \r\n
        }
        return "data:image/jpg;base64," + png_base64;
    }

    /**
     * 将html转成base64字节
     *
     * @param html
     * @param width
     * @param height
     * @return
     * @throws Exception
     */
    public static String html2ImgBase64(String html, int width, int height) throws Exception {
        byte[] bytes = html.getBytes();
        BufferedImage img;
        try (ByteArrayInputStream bin = new ByteArrayInputStream(bytes)) {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(bin);
            Java2DRenderer renderer = new Java2DRenderer(document, width, height);
            SharedContext sharedContext = renderer.getSharedContext();
            sharedContext.setDotsPerPixel(3);
            sharedContext.setDPI(523);
            img = renderer.getImage();

            String imgName = Thread.currentThread().getContextClassLoader().getResource("").getPath() + "test.jpg";
            System.out.println("输出地址：" + imgName);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(img, "jpg", new FileOutputStream(imgName));


        }
        return bufferedImageToBase64(img);
    }

    /**
     * 将html转成 图片
     *
     * @param html
     * @param width
     * @param height
     * @return
     * @throws Exception
     */
    public static BufferedImage html2Img(String html, int width, int height) throws Exception {
        byte[] bytes = html.getBytes();
        BufferedImage img;
        //转BufferedImage
        try (ByteArrayInputStream bin = new ByteArrayInputStream(bytes)) {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(bin);
            Java2DRenderer renderer = new Java2DRenderer(document, width, height);
            SharedContext sharedContext = renderer.getSharedContext();
            sharedContext.setDotsPerPixel(1);
            sharedContext.setDPI(100);
            //字体
            Font simsun = getSIMSUN(Font.BOLD, 12);
            sharedContext.setFontMapping("simsun", simsun);//这样设置字体无效
            Map map = new HashMap<>();//设置参数
            map.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            map.put(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
            map.put(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
            map.put(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
            map.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            map.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            map.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            map.put(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
            map.put(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
            renderer.setRenderingHints(map);
            img = renderer.getImage();
        }
        return img;
    }
    public static void html2Img(String html, HttpServletResponse response, int width, int height) throws Exception {
        BufferedImage img = html2Img(html, width, height);
        // 设置响应头，指定为PNG格式
        response.setContentType("image/png");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");
        // 将图像写入响应输出流
        ImageIO.write(img, "png", response.getOutputStream());
    }
    /**
     * 获取模板数据
     *
     * @param template
     * @param params
     * @return
     * @throws Exception
     */
    public static String generate(String template, Map params) throws Exception {
        Template tp = config.getTemplate(template);
        tp.setEncoding("UTF-8");
        StringWriter stringWriter = new StringWriter();
        String htmlStr;
        try {
            tp.process(params, stringWriter);
            htmlStr = stringWriter.toString();
            stringWriter.flush();
        } finally {
            stringWriter.close();
        }
        return htmlStr;
    }

    /**
     * 宋体
     *
     * @param style Font.BOLD
     * @param size  24
     */
    public static Font getSIMSUN(int style, float size) {
        Font font = null;
        //获取字体流
        InputStream simsunFontFile = FreemarkerUtil.class.getResourceAsStream("/static/fonts/simsun.ttc");
        try {
            //创建字体
            font = Font.createFont(Font.PLAIN, simsunFontFile).deriveFont(style, size);
        } catch (FontFormatException e) {
            log.error("", e);
        } catch (IOException e) {
            font = new Font("宋体", Font.BOLD, 6);
            log.error("", e);
        }
        return font;
    }
}

