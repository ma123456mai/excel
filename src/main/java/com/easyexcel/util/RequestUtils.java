package com.easyexcel.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Mr丶s
 * @date 2020/6/6 2:27 下午
 * @description
 */
public class RequestUtils {


    /**
     * <p>通过SpringMVC提供的RequestContextHolder获取Request相关内容</p>
     * <p>参考链接：https://blog.csdn.net/u012706811/article/details/53432032</p>
     *
     * @return
     */
    private static ServletRequestAttributes getRequestAttributes() {
        return (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    }

    /**
     * 获取HttpServletRequest
     *
     * @return
     */
    public static HttpServletRequest getHttpServletRequest() {
        return getRequestAttributes().getRequest();
    }

    /**
     * 获取HttpServletResponse
     *
     * @return
     */
    public static HttpServletResponse getHttpServletResponse() {
        return getRequestAttributes().getResponse();
    }


}
