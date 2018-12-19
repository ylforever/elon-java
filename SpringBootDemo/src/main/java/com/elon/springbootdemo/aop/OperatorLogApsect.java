package com.elon.springbootdemo.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.elon.springbootdemo.manager.OperatorLog;
import com.elon.springbootdemo.model.ResponseModel;

/**
 * 操作日志切面定义。
 * @author elon
 * @version 2018年11月4日
 */
@Aspect
@Component
public class OperatorLogApsect {
    
    private static Logger logger = LogManager.getLogger(OperatorLogApsect.class);
    
    @Pointcut("@annotation(com.elon.springbootdemo.manager.OperatorLog)")
    public void operatorLog() {
        
    }
    
    @SuppressWarnings("rawtypes")
    @AfterReturning(returning="result", pointcut="operatorLog()&&@annotation(log)")
    public void afterReturn(JoinPoint joinPoint, ResponseModel result, OperatorLog log) {
        
        /**
         * 接口调用信息可以记日志，也可以写到数据库
         */
        StringBuilder sb = new StringBuilder();
        sb.append("模块：").append(log.module());
        sb.append("|操作:").append(log.operate());
        sb.append("|接口名称:").append(joinPoint.getSignature().getName());
        sb.append("|错误码:").append(result.getRetCode());
        sb.append("|错误信息:").append(result.getErrorMsg());
        logger.info(sb.toString());
    }
}
