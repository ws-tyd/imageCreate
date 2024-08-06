package com.jyyy.picturegeneration.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class OneRequestHandler implements HandlerInterceptor {
    private static final Logger log = LoggerFactory.getLogger(OneRequestHandler.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestUrl = request.getRequestURL().toString();

//        // 获取请求参数（对于 POST 请求，参数通常在请求体中）
//        StringBuilder params = new StringBuilder();
//        if ("POST".equalsIgnoreCase(request.getMethod())) {
//            StringBuilder buffer = new StringBuilder();
//            BufferedReader reader = request.getReader();
//            String line;
//            while ((line = reader.readLine()) != null) {
//                buffer.append(line);
//            }
//            String payload = buffer.toString();
//            params.append(payload);
//        } else {
//            // 对于 GET 或其他方法，仍然可以获取 URL 参数
//            Map<String, String[]> parameterMap = request.getParameterMap();
//            for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
//                String paramName = entry.getKey();
//                String paramValue = Arrays.toString(entry.getValue());
//                params.append(paramName).append("=").append(paramValue).append(", ");
//            }
//        }
        // 记录日志
        log.info("Request URL: {}", requestUrl);

        // 返回true，表示继续执行请求；返回false，表示中断请求
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
