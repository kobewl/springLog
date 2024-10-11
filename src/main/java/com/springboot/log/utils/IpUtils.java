package com.springboot.log.utils;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wangliang
 */
public class IpUtils {

    /**
     * 获取客户端IP
     *
     * @param request 请求对象
     * @return IP地址
     */
    public static String getIpAddr(HttpServletRequest request) {
        if (request == null) {
            return "unknown";
        }
        String ip = getHeader(request, "x-forwarded-for");
        ip = getHeader(request, "Proxy-Client-IP", ip);
        ip = getHeader(request, "X-Forwarded-For", ip);
        ip = getHeader(request, "WL-Proxy-Client-IP", ip);
        ip = getHeader(request, "X-Real-IP", ip);

        if (isUnknown(ip)) {
            ip = request.getRemoteAddr();
        }

        return "0:0:0:0:0:0:0:1".equals(ip)? "127.0.0.1" : getMultistageReverseProxyIp(ip);
    }

    /**
     * 从多级反向代理中获得第一个非unknown IP地址
     *
     * @param ip 获得的IP地址
     * @return 第一个非unknown IP地址
     */
    public static String getMultistageReverseProxyIp(String ip) {
        // 多级反向代理检测
        if (ip!= null && ip.indexOf(",") > 0) {
            final String[] ips = ip.trim().split(",");
            for (String subIp : ips) {
                if (!isUnknown(subIp)) {
                    ip = subIp;
                    break;
                }
            }
        }
        return ip;
    }

    private static String getHeader(HttpServletRequest request, String name, String defaultValue) {
        String value = request.getHeader(name);
        return isUnknown(value)? defaultValue : value;
    }

    private static String getHeader(HttpServletRequest request, String name) {
        return getHeader(request, name, null);
    }

    private static boolean isUnknown(String checkString) {
        return StringUtils.isBlank(checkString) || "unknown".equalsIgnoreCase(checkString);
    }
}


