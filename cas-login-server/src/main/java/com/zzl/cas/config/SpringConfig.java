package com.zzl.cas.config;/**
 * Created by admin on 2019/6/18.
 */

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author zzl
 * @version 1.0
 * @desception spring配置类，通过spring.factories把它放到springIOC中，然后通过他扫描别的类到springIOC中
 * @date 2019/6/18 15:54
 */
@Configuration
@ComponentScan("com.zzl.cas")
public class SpringConfig implements BeanPostProcessor {
    //再实例初始化之前调用
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessBeforeInitialization..."+beanName+"=>"+bean);
        return bean;
    }
    //再实例初始化之后调用
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessAfterInitialization..."+beanName+"=>"+bean);
        return bean;
    }
}
