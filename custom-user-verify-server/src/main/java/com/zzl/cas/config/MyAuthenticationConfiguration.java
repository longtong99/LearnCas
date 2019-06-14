package com.zzl.cas.config;/**
 * Created by admin on 2019/6/13.
 */

import com.zzl.cas.auth.MyAuthenticationHandler;
import org.apereo.cas.authentication.AuthenticationEventExecutionPlan;
import org.apereo.cas.authentication.AuthenticationEventExecutionPlanConfigurer;
import org.apereo.cas.authentication.AuthenticationHandler;
import org.apereo.cas.authentication.principal.DefaultPrincipalFactory;
import org.apereo.cas.configuration.CasConfigurationProperties;
import org.apereo.cas.services.ServicesManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zzl
 * @version 1.0
 * @desception
 * @date 2019/6/13 16:36
 */
@Configuration("myAuthenticationConfiguration")
@EnableConfigurationProperties(CasConfigurationProperties.class)
public class MyAuthenticationConfiguration implements AuthenticationEventExecutionPlanConfigurer {

    @Autowired
    private CasConfigurationProperties casProperties;

    @Autowired
    //@Qualifier("servicesManager")
    private ServicesManager servicesManager;

    /**
     * 将自定义验证器注册到spring
     * @return
     */
    @Bean
    public AuthenticationHandler myAuthenticationHandler() {
        MyAuthenticationHandler handler = new MyAuthenticationHandler(MyAuthenticationHandler.class.getSimpleName(), servicesManager, new DefaultPrincipalFactory(), 1);
        return handler;
    }

    /**
     * 把自定义验证器注册到cas的验证authenticationHandlers
     * cas在验证的时候会把authenticationHandlers里面的所有验证都验证一遍，只要有一个满足的就通过
     * 例如前面提到的用盐值加密的方式验证以及用MD5的方式同时验证，有一个满足的就通过
     * @param plan
     */
    @Override
    public void configureAuthenticationExecutionPlan(AuthenticationEventExecutionPlan plan) {
        plan.registerAuthenticationHandler(myAuthenticationHandler());
    }
}
