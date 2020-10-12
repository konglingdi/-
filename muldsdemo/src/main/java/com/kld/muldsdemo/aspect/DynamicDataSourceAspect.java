package com.kld.muldsdemo.aspect;

import com.kld.muldsdemo.annotation.CurDataSource;
import com.kld.muldsdemo.pojo.DataSourceContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Description:
 * @ClassName DynamicDataSourceAspect
 * @date: 2020.09.05 09:28
 * @Author: 孔令迪
 */
@Aspect
@Component
public class DynamicDataSourceAspect {

    @Around("@annotation(com.kld.muldsdemo.annotation.CurDataSource)")
    public Object routingWithDataSource(ProceedingJoinPoint joinPoint) throws Throwable {
        Class<?> targetCls=joinPoint.getTarget().getClass();
        MethodSignature ms=(MethodSignature)joinPoint.getSignature();
        Method targetMethod=
                targetCls.getDeclaredMethod(
                        ms.getName(),
                        ms.getParameterTypes());
        CurDataSource annotation = targetMethod.getAnnotation(CurDataSource.class);
        DataSourceContextHolder.setDataSource(annotation.value());
        Object obj =  joinPoint.proceed();
        DataSourceContextHolder.removeDataSource();
        return obj;
    }

}
