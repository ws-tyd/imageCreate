package com.jyyy.picturegeneration.utils;

import com.jyyy.picturegeneration.controller.param.MLolMainPictureParam;
import freemarker.cache.ByteArrayTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.apache.commons.io.IOUtils;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class LoadTemplatesUtils {
    /**
     * 通过指定classpath:templates读取指定模板
     * 如果是打成war包，或者指定服务器绝对路径的时候，可以使用此方法
     *
     * @param template
     * @param map
     * @return
     * @throws IOException
     * @throws TemplateException
     */
    public static String getTemplateByTemplatePath(String template, Map<String, Object> map) throws Exception {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_30);
        String templatePath = ResourceUtils.getFile("classpath:templates").getPath();
        cfg.setDirectoryForTemplateLoading(new File(templatePath));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setClassicCompatible(true);
        Template temp = cfg.getTemplate(template);
        StringWriter stringWriter = new StringWriter();
        temp.process(map, stringWriter);
        stringWriter.flush();
        stringWriter.close();
        String result = stringWriter.getBuffer().toString();
        return result;
    }

    /**
     * 通过类加载器的方式获取模板
     * springboot项目在部署的时候会打包成jar，打包成jar以后在使用freemaker时会出现以下报错：
     * cannot be resolved to absolute file path because it does not reside in the file system: jar
     * 通过以下 setClassLoaderForTemplateLoading() 方法设置成类加载器的方式，可以解决上述无法访问模板路径的问题
     *
     * @param template
     * @param map
     * @return
     * @throws IOException
     * @throws TemplateException
     */
    public static String getTemplateByClassLoader(String template, Map<String, Object> map) throws Exception {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_30);
        cfg.setClassLoaderForTemplateLoading(LoadTemplatesUtils.class.getClassLoader(), "templates");
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setClassicCompatible(true);
        Template temp = cfg.getTemplate(template);
        StringWriter stringWriter = new StringWriter();
        temp.process(map, stringWriter);
        stringWriter.flush();
        stringWriter.close();
        String result = stringWriter.getBuffer().toString();
        return result;
    }

    public static String getTemplateByClassLoader(String template, MLolMainPictureParam param) throws Exception {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_30);
        cfg.setClassLoaderForTemplateLoading(LoadTemplatesUtils.class.getClassLoader(), "templates");
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setClassicCompatible(true);
        Template temp = cfg.getTemplate(template);
        StringWriter stringWriter = new StringWriter();
        temp.process(param, stringWriter);
        stringWriter.flush();
        stringWriter.close();
        String result = stringWriter.getBuffer().toString();
        return result;
    }

    /**
     * 通过远程URL地址获取模板
     * 此方法可以通过URL加载存储在远程服务器上的模板
     *
     * @param template
     * @param map
     * @return
     * @throws IOException
     * @throws TemplateException
     */
    public static String getTemplateByUrl(String template, Map<String, Object> map, String url) throws Exception {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_30);
        ByteArrayTemplateLoader byteArrayTemplateLoader = new ByteArrayTemplateLoader();
        InputStream initialStream = getInputStreamByGet(url);
        byteArrayTemplateLoader.putTemplate(template, IOUtils.toByteArray(initialStream));
        cfg.setTemplateLoader(byteArrayTemplateLoader);
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setClassicCompatible(true);
        Template temp = cfg.getTemplate(template);
        StringWriter stringWriter = new StringWriter();
        temp.process(map, stringWriter);
        stringWriter.flush();
        stringWriter.close();
        String result = stringWriter.getBuffer().toString();
        return result;
    }

    /**
     * 通过get请求得到读取器响应数据的数据流
     *
     * @param url
     * @return
     * @throws Exception
     */
    public static InputStream getInputStreamByGet(String url) throws Exception {
        InputStream inputStream = null;
        HttpURLConnection conn = (HttpURLConnection) new URL(url)
                .openConnection();
        conn.setReadTimeout(5000);
        conn.setConnectTimeout(5000);
        conn.setConnectTimeout(5000);
        if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
            inputStream = conn.getInputStream();
        }
        return inputStream;
    }

}
