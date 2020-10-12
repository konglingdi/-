package com.kld.muldsdemo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description:
 * @ClassName CurDataSource
 * @date: 2020.10.09 18:00
 * @Author: 孔令迪
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CurDataSource {
    String value();
}
