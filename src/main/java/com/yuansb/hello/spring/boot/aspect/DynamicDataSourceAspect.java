package com.yuansb.hello.spring.boot.aspect;

import com.yuansb.hello.spring.boot.annotation.DataSourceSign;
import com.yuansb.hello.spring.boot.common.ContextConst;
import com.yuansb.hello.spring.boot.dataSource.DataSourceContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Order(-1)// 保证该AOP在@Transactional之前执行
@Aspect
public class DynamicDataSourceAspect {

    @Before("execution(* com.yuansb.hello.spring.boot.service..*.*(..))")
    public void before(JoinPoint point) {
        try {
            DataSourceSign annotationOfClass = point.getTarget().getClass().getAnnotation(DataSourceSign.class);

            String methodName = point.getSignature().getName();

            Class[] parameterTypes = ((MethodSignature) point.getSignature()).getParameterTypes();

            Method method = point.getTarget().getClass().getMethod(methodName, parameterTypes);

            DataSourceSign methodAnnotation = method.getAnnotation(DataSourceSign.class);

            methodAnnotation = methodAnnotation ==
                    null ? annotationOfClass : methodAnnotation;

            ContextConst.DataSourceType dataSourceType =
                    methodAnnotation != null && methodAnnotation.value() != null ?
                            methodAnnotation.value() : ContextConst.DataSourceType.DS_1;
            DataSourceContextHolder.setDataSource(dataSourceType.name());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @After("execution(* com.yuansb.hello.spring.boot.service..*.*(..))")
    public void after(JoinPoint point) {
        //相当于切换到默认数据源
        //DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.DS_1.name());
        DataSourceContextHolder.clearDataSource();
    }

}
