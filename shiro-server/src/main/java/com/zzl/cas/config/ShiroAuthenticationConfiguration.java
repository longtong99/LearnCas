package com.zzl.cas.config;/**
 * Created by admin on 2019/6/14.
 */

import com.zzl.cas.auth.MyShiroAuthenticationHandler;
import com.zzl.cas.auth.ShiroRealm;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apereo.cas.authentication.AuthenticationEventExecutionPlan;
import org.apereo.cas.authentication.AuthenticationEventExecutionPlanConfigurer;
import org.apereo.cas.authentication.AuthenticationHandler;
import org.apereo.cas.authentication.principal.DefaultPrincipalFactory;
import org.apereo.cas.configuration.CasConfigurationProperties;
import org.apereo.cas.services.ServicesManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zzl
 * @version 1.0
 * @desception 各种信息注册类 包含shiro的配置
 * @date 2019/6/14 18:16
 */
@Configuration
@EnableConfigurationProperties(CasConfigurationProperties.class)
public class ShiroAuthenticationConfiguration implements AuthenticationEventExecutionPlanConfigurer {
    @Autowired
    private CasConfigurationProperties casProperties;

    @Autowired
    private ServicesManager servicesManager;

    @Bean(name="securityManager")
    public SecurityManager securityManager() {
        DefaultSecurityManager securityManager =  new DefaultSecurityManager();
        //设置自定义realm.
        securityManager.setRealm(shiroRealm());
        return securityManager;
    }

    @Bean
    public ShiroRealm shiroRealm(){
        ShiroRealm shiroRealm = new ShiroRealm();
        shiroRealm.setCachingEnabled(false);
        //启用身份验证缓存，即缓存AuthenticationInfo信息，默认false
        shiroRealm.setAuthenticationCachingEnabled(false);
        //启用授权缓存，即缓存AuthorizationInfo信息，默认false
        shiroRealm.setAuthorizationCachingEnabled(false);
        return shiroRealm;
    }

    /**
     * Spring静态注入
     * 因为shiro也有登出、鉴权之类的接口，和cas的冲突，所以这里需要设置下，不然会报错，我们只用shiro的登陆鉴权，其他的不用
     * @return
     */
    @Bean
    public MethodInvokingFactoryBean getMethodInvokingFactoryBean(){
        MethodInvokingFactoryBean factoryBean = new MethodInvokingFactoryBean();
        factoryBean.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
        factoryBean.setArguments(new Object[]{securityManager()});
        return factoryBean;
    }

    /**
     * 将自定义验证器注册到spring
     * @return
     */
    @Bean
    public AuthenticationHandler shiroAuthenticationHandler() {
        MyShiroAuthenticationHandler handler = new MyShiroAuthenticationHandler(MyShiroAuthenticationHandler.class.getSimpleName(), servicesManager, new DefaultPrincipalFactory(),10);
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
        // TODO Auto-generated method stub
        plan.registerAuthenticationHandler(shiroAuthenticationHandler());
    }
}
