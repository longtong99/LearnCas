package com.zzl.cas.client.configuration;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Created by admin on 2019/7/3.
 * 定义开启cas客户端的注解
 * 参考的net.unicon.cas.client.configuration.EnableCasClient
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(CasClientConfiguration.class)
public @interface EnableCasClient {
}
