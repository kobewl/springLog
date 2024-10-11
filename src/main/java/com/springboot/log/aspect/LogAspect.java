package com.springboot.log.aspect;

import com.springboot.log.annotation.Log;
import com.springboot.log.constant.BusinessTypeEnum;
import com.springboot.log.entity.SysLog;
import com.springboot.log.listener.EventPubListener;
import com.springboot.log.utils.IpUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author wangling
 */
@Aspect
@Component
public class LogAspect {

    // 日志记录
    private final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Resource
    private EventPubListener eventPubListener;

    /**
     * 以注解所标注的方法作为切入点
     */
    @Pointcut("@annotation(com.springboot.log.annotation.Log)")
    public void Log() {
    }

    /**
     * 在切点之后织入
     */
    @After("Log()")
    public void doAfter(JoinPoint joinPoint) {
        try {
            Log log = getLogAnnotation(joinPoint);
            if (log == null) {
                logger.warn("No Log annotation found on the method.");
                return;
            }
            ServletRequestAttributes attributes = getServletRequestAttributes();
            if (attributes == null) {
                logger.warn("No ServletRequestAttributes found in the current context.");
                return;
            }
            HttpServletRequest request = attributes.getRequest();
            String method = request.getMethod();
            String url = request.getRequestURL().toString();
            String ip = IpUtils.getIpAddr(request);
            BusinessTypeEnum businessTypeEnum = BusinessTypeEnum.c(method);
            SysLog sysLog = buildSysLog(log, method, url, ip);
            sysLog.setBusinessType(businessTypeEnum);
            sysLog.setOperName("测试人员");
            sysLog.setOperTime(new Date());
            // 发布消息
            eventPubListener.pushListener(sysLog);
            logger.info("=======日志发送成功，内容：{}", sysLog);
        } catch (Exception e) {
            logger.error("记录日志时发生错误", e);
        }
    }

    private Log getLogAnnotation(JoinPoint joinPoint) {
        // 获取方法签名
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        return signature.getMethod().getAnnotation(Log.class);
    }

    private ServletRequestAttributes getServletRequestAttributes() {
        // 获取当前线程绑定的请求对象
        return (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    }

    private SysLog buildSysLog(Log log, String method, String url, String ip) {
        // 封装日志信息
        SysLog sysLog = new SysLog();
        sysLog.setBusinessType(log.businessType().getCode());
        sysLog.setTitle(log.title());
        sysLog.setMethod(log.method());
        sysLog.setRequestMethod(method);
        sysLog.setOperIp(ip);
        sysLog.setOperUrl(url);
        return sysLog;
    }

}


