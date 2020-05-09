package com.gxyan.aoplog.aspect;

import com.gxyan.aoplog.annotation.Log;
import com.gxyan.aoplog.mapper.SysLogDao;
import com.gxyan.aoplog.entity.SysLog;
import com.gxyan.aoplog.util.HttpContextUtils;
import com.gxyan.aoplog.util.IPUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author gxyan
 * @date 2020/5/6 22:49
 */
@Aspect
@Component
public class LogAspect {

    @Resource
    private SysLogDao sysLogDao;

    @Pointcut("@annotation(com.gxyan.aoplog.annotation.Log)")
    public void pointcut() {}

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) {
        Object result = null;
        long beginTime = System.currentTimeMillis();
        try {
            // 执行方法
            result = point.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        // 执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        // 保存日志
        saveLog(point, time);
        return result;
    }

    private void saveLog(ProceedingJoinPoint point, long time) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        SysLog sysLog = new SysLog();
        Log log = method.getAnnotation(Log.class);
        if (log != null) {
            // 注解上的描述
            sysLog.setOperation(log.value());
        }
        // 方法名
        String className = point.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");
        // 请求的方法参数值
        Object[] args = point.getArgs();
        // 请求的方法参数名称
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] parameterNames = u.getParameterNames(method);
        if (args != null && parameterNames != null) {
            StringBuilder params = new StringBuilder();
            for (int i = 0; i < args.length; i++) {
                params.append(" ").append(parameterNames[i]).append(": ").append(args[i]);
            }
            sysLog.setParams(params.toString());
        }
        // 获取request
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        // 设置IP地址
        sysLog.setIp(IPUtils.getIpAddr(request));
        // 模拟一个用户名
        sysLog.setUserName("我是谁");
        sysLog.setTime(time);
        sysLog.setCreateTime(new Date());
        // 保存系统日志
        sysLogDao.insertSelective(sysLog);
    }
}
