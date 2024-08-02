package com.jyyy.picturegeneration.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.util.HashMap;
import java.util.Map;

public class GenerationPictureUtils {
    private static final Logger log = LoggerFactory.getLogger(GenerationPictureUtils.class);

    /**
     * ftl模板生成图片接口
     *
     * @param filename    生成图片名称
     * @param templateUrl ftl模板路径
     * @param template    ftl模板名称
     * @param map         模板占位符数据
     * @throws Exception
     */
    public static byte[] turnImage(String templateUrl, String template, Map<String, Object> map, String filename) throws Exception {
        // 写出到流
        String html = LoadTemplatesUtils.getTemplateByUrl(template, map, templateUrl);
        byte[] bytes = html.getBytes("UTF-8");
        ByteArrayInputStream bin = new ByteArrayInputStream(bytes);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(bin);
        Java2DRenderer renderer = new Java2DRenderer(document, 1000, 500);
        BufferedImage img = renderer.getImage();
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        ImageIO.write(img, filename, outStream);
        return outStream.toByteArray();
    }

    public static byte[] turnImage(String html, String filename) throws Exception {
        // 写出到流
        byte[] bytes = html.getBytes("UTF-8");
        ByteArrayInputStream bin = new ByteArrayInputStream(bytes);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(bin);
        Java2DRenderer renderer = new Java2DRenderer(document, 1000, 500);
        BufferedImage img = renderer.getImage();
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        ImageIO.write(img, filename, outStream);
        return outStream.toByteArray();
    }
    /**
     * ftl模板生成图片接口，并输出到浏览器
     * @param template
     * @param map
     * @param response
     * @param url
     * @throws Exception
     */
    public static void turnImage(String template, Map<String,Object> map, HttpServletResponse response, String url) throws Exception {
        //方式一：指定模板文件路径加载模板
//        String html = getTemplateByTemplatePath(template, map);
        //方式二：指定类加载器加载模板
//        String html = getTemplateByClassLoader(template, map);
        //方式三：指定远程模板文件存储路径加载模板
        String html = LoadTemplatesUtils.getTemplateByUrl(template, map,url);
        byte[] bytes=html.getBytes();
        ByteArrayInputStream bin=new ByteArrayInputStream(bytes);
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        DocumentBuilder builder=factory.newDocumentBuilder();
        Document document=builder.parse(bin);
        Java2DRenderer renderer = new Java2DRenderer(document,1000,800);
        BufferedImage img = renderer.getImage();
        response.setContentType("image/jpeg");
        response.setDateHeader("expries", -1);
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        ImageIO.write(img, "jpg", response.getOutputStream());
    }

    public static void turnImage(String html, HttpServletResponse response) throws Exception {
        //方式一：指定模板文件路径加载模板
//        String html = getTemplateByTemplatePath(template, map);
        //方式二：指定类加载器加载模板
//        String html = getTemplateByClassLoader(template, map);
        //方式三：指定远程模板文件存储路径加载模板
        byte[] bytes=html.getBytes();
        ByteArrayInputStream bin=new ByteArrayInputStream(bytes);
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        DocumentBuilder builder=factory.newDocumentBuilder();
        Document document=builder.parse(bin);
        Java2DRenderer renderer = new Java2DRenderer(document,960,630);
        BufferedImage img = renderer.getImage();
        response.setContentType("image/jpeg");
        response.setDateHeader("expries", -1);
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        ImageIO.write(img, "jpg", response.getOutputStream());
    }
    public static void turnImagePng(String html, HttpServletResponse response) throws Exception {
        byte[] bytes = html.getBytes();
        ByteArrayInputStream bin = new ByteArrayInputStream(bytes);

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(bin);

        Java2DRenderer renderer = new Java2DRenderer(document, 960, 630);
        SharedContext sharedContext = renderer.getSharedContext();
        sharedContext.setDotsPerPixel(1);
        sharedContext.setDPI(480);
        BufferedImage img = renderer.getImage();

        // 设置响应头，指定为PNG格式
        response.setContentType("image/png");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");

        // 将图像写入响应输出流
        ImageIO.write(img, "png", response.getOutputStream());
    }
    /**
     * 图片盖章
     *
     * @param stampedImageUrl 图片
     * @param sealImageUrl    印章图片
     * @param filename        图片名称
     * @return
     * @throws Exception
     */
    public static byte[] imageSynthesis(String stampedImageUrl, String sealImageUrl, String filename) {
        try {
            InputStream stampedImageIn = LoadTemplatesUtils.getInputStreamByGet(stampedImageUrl);
            BufferedImage stampedImage = ImageIO.read(stampedImageIn);
            Graphics g = stampedImage.getGraphics();
            InputStream sealImageIn = LoadTemplatesUtils.getInputStreamByGet(sealImageUrl);
            BufferedImage sealImage = ImageIO.read(sealImageIn);
            //加盖图片章
            int x = stampedImage.getWidth() - sealImage.getWidth() - 100;
            int y = stampedImage.getHeight() - sealImage.getHeight() - 100;
            g.drawImage(sealImage, x, y, null);
            g.dispose();
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            ImageIO.write(stampedImage, filename, outStream);
            return outStream.toByteArray();
        } catch (FileNotFoundException e) {
            log.error("文件找不到：{}，{}", e.getMessage(), e);
        } catch (IOException e) {
            log.error("文件转图片IO异常：{}，{}", e.getMessage(), e);
        } catch (Exception e) {
            log.error("图片盖章异常：{}，{}", e.getMessage(), e);
        }
        return null;
    }

    public static void main(String[] args) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("title","asdasdas1dasdasd");
        map.put("name","asdasdas2dasdasd");
        map.put("info","asdasdas3dasdasd");
        try {
            byte[] bytes = turnImage(LoadTemplatesUtils.getTemplateByClassLoader("m_lol.ftl", map), "png");
            BufferedImage image = ImageIO.read(new ByteArrayInputStream(bytes));
            File file = new File("test.png");
            ImageIO.write(image,"png",file);
            System.out.println(file.getAbsoluteFile());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
