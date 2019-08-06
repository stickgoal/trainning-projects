package com.woniuxy.rocket.mvc.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标识一个请求映射
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface LittleRequestMapping {

    /**
     * 请求路径
     * @return
     */
    String value() ;

    /**
     * 请求方法
     * @return
     */
    RequestMethod[] method() default {RequestMethod.GET,RequestMethod.POST};

}
