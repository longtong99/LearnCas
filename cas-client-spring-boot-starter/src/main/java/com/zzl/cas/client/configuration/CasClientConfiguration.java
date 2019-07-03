package com.zzl.cas.client.configuration;/**
 * Created by admin on 2019/7/3.
 */

import org.jasig.cas.client.authentication.AuthenticationFilter;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
import org.jasig.cas.client.util.HttpServletRequestWrapperFilter;
import org.jasig.cas.client.validation.Cas30ProxyReceivingTicketValidationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.EventListener;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zzl
 * @version 1.0
 * @desception cas客户端的自动化配置类
 * 参考的net.unicon.cas.client.configuration.CasClientConfiguration
 *
 * 这个类的作用就是在项目启动的时候，把单点登陆、登出、获取信息等过滤器/监听器（就是之前官网客户端的web.xml里面的配置）注册到springIOC容器中
 * 干的就是之前web.xml干的事
 *
 * @date 2019/7/3 16:32
 */
@Configuration
@EnableConfigurationProperties(CasClientConfigurationProperties.class)
public class CasClientConfiguration {

    @Autowired
    CasClientConfigurationProperties configProps;

    /**
     * 配置登出过滤器
     * 仔细看下逻辑，发现和官网的客户端（cas-login-client1、cas-login-client2）的web.xml里的作用一样
     *
     *
     *
         <!-- 该过滤器用于实现单点登出功能，可选配置。 -->
         <filter>
         <filter-name>CAS Single Sign Out Filter</filter-name>
         <filter-class>org.jasig.cas.client.session.SingleSignOutFilter</filter-class>
         <init-param>
         <param-name>casServerUrlPrefix</param-name>
         <param-value>https://server.cas.com:8443/cas</param-value>
         </init-param>
         </filter>
         <filter-mapping>
         <filter-name>CAS Single Sign Out Filter</filter-name>
         <url-pattern>/*</url-pattern>
         </filter-mapping>
     *
     *
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean filterSingleRegistration() {
        final FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new SingleSignOutFilter());
        // 设定匹配的路径
        registration.addUrlPatterns("/*");
        Map<String,String> initParameters = new HashMap<String, String>();
        initParameters.put("casServerUrlPrefix", configProps.getServerUrlPrefix());
        registration.setInitParameters(initParameters);
        // 设定加载的顺序
        registration.setOrder(1);
        return registration;
    }

    /**
     * 配置过滤验证器 这里用的是Cas30ProxyReceivingTicketValidationFilter
     * 仔细看下逻辑，发现和官网的客户端（cas-login-client1、cas-login-client2）的web.xml里的作用一样
     *
     <!-- 该过滤器负责对Ticket的校验工作 -->
     <filter>
     <filter-name>CAS Validation Filter</filter-name>
     <filter-class>org.jasig.cas.client.validation.Cas30ProxyReceivingTicketValidationFilter</filter-class>
     <init-param>
     <param-name>casServerUrlPrefix</param-name>
     <param-value>https://server.cas.com:8443/cas</param-value>
     <!-- 使用的CAS-Server的地址,一定是在浏览器输入该地址能正常打开CAS-Server的根地址 -->
     </init-param>
     <init-param>
     <param-name>serverName</param-name>
     <param-value>http://app1.cas.com:8081</param-value>
     <!-- 当前Client系统的地址 -->
     </init-param>
     </filter>
     <filter-mapping>
     <filter-name>CAS Validation Filter</filter-name>
     <url-pattern>/*</url-pattern>
     </filter-mapping>
     *
     *
     *
     *
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean filterValidationRegistration() {
        final FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new Cas30ProxyReceivingTicketValidationFilter());
        // 设定匹配的路径
        registration.addUrlPatterns("/*");
        Map<String,String> initParameters = new HashMap<String, String>();
        initParameters.put("casServerUrlPrefix", configProps.getServerUrlPrefix());
        initParameters.put("serverName", configProps.getClientHostUrl());
        initParameters.put("useSession", "true");
        registration.setInitParameters(initParameters);
        // 设定加载的顺序
        registration.setOrder(2);
        return registration;
    }

    /**
     * 配置授权过滤器
     *仔细看下逻辑，发现和官网的客户端（cas-login-client1、cas-login-client2）的web.xml里的作用一样
     *
     <!-- 该过滤器用于实现单点登录功能 -->
     <filter>
     <filter-name>CAS Filter</filter-name>
     <filter-class>org.jasig.cas.client.authentication.AuthenticationFilter</filter-class>
     <init-param>
     <param-name>casServerLoginUrl</param-name>
     <param-value>https://server.cas.com:8443/cas/login</param-value>
     <!-- 使用的CAS-Server的登录地址,一定是到登录的action -->
     </init-param>
     <init-param>
     <param-name>serverName</param-name>
     <param-value>http://app1.cas.com:8081</param-value>
     <!-- 当前Client系统的地址 -->
     </init-param>
     </filter>
     <filter-mapping>
     <filter-name>CAS Filter</filter-name>
     <url-pattern>/*</url-pattern>
     </filter-mapping>
     *
     *
     *
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean filterAuthenticationRegistration() {
        final FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new AuthenticationFilter());
        // 设定匹配的路径
        registration.addUrlPatterns("/*");
        Map<String,String>  initParameters = new HashMap<String, String>();
        initParameters.put("casServerLoginUrl", configProps.getServerLoginUrl());
        initParameters.put("serverName", configProps.getClientHostUrl());

        //从这网上和web.xml里的配置的作用一样，这里下面的逻辑是对自定义的配置参数的处理
        if(configProps.getIgnorePattern() != null && !"".equals(configProps.getIgnorePattern())){
            initParameters.put("ignorePattern", configProps.getIgnorePattern());
        }

        //自定义UrlPatternMatcherStrategy 验证规则
        if(configProps.getIgnoreUrlPatternType() != null && !"".equals(configProps.getIgnoreUrlPatternType())){
            initParameters.put("ignoreUrlPatternType", configProps.getIgnoreUrlPatternType());
        }

        registration.setInitParameters(initParameters);
        // 设定加载的顺序
        registration.setOrder(3);
        return registration;
    }

    /**
     * request wraper过滤器
     * 仔细看下逻辑，发现和官网的客户端（cas-login-client1、cas-login-client2）的web.xml里的作用一样
     *
     <!-- 该过滤器负责实现HttpServletRequest请求的包裹， 比如允许开发者通过HttpServletRequest的getRemoteUser()方法获得SSO登录用户的登录名，可选配置。 -->
     <filter>
     <filter-name>CAS HttpServletRequest Wrapper Filter</filter-name>
     <filter-class>org.jasig.cas.client.util.HttpServletRequestWrapperFilter</filter-class>
     </filter>
     <filter-mapping>
     <filter-name>CAS HttpServletRequest Wrapper Filter</filter-name>
     <url-pattern>/*</url-pattern>
     </filter-mapping>
     *
     *
     *
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean filterWrapperRegistration() {
        final FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new HttpServletRequestWrapperFilter());
        // 设定匹配的路径
        registration.addUrlPatterns("/*");
        // 设定加载的顺序
        registration.setOrder(4);
        return registration;
    }

    /**
     * 添加监听器
     * 仔细看下逻辑，发现和官网的客户端（cas-login-client1、cas-login-client2）的web.xml里的作用一样
     *
     <!-- 用于单点退出，该过滤器用于实现单点登出功能，可选配置 -->
     <listener>
     <listener-class>org.jasig.cas.client.session.SingleSignOutHttpSessionListener</listener-class>
     </listener>
     *
     *
     * @return
     */
    @Bean
    public ServletListenerRegistrationBean<EventListener> singleSignOutListenerRegistration(){
        ServletListenerRegistrationBean<EventListener> registrationBean = new ServletListenerRegistrationBean<EventListener>();
        registrationBean.setListener(new SingleSignOutHttpSessionListener());
        registrationBean.setOrder(1);
        return registrationBean;
    }


}
