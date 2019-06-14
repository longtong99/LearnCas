package com.zzl.cas.auth;/**
 * Created by admin on 2019/6/13.
 */

import org.apereo.cas.authentication.AuthenticationHandlerExecutionResult;
import org.apereo.cas.authentication.Credential;
import org.apereo.cas.authentication.PreventedException;
import org.apereo.cas.authentication.UsernamePasswordCredential;
import org.apereo.cas.authentication.handler.support.AbstractUsernamePasswordAuthenticationHandler;
import org.apereo.cas.authentication.principal.PrincipalFactory;
import org.apereo.cas.services.ServicesManager;

import javax.security.auth.login.AccountNotFoundException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;

/**
 * @author zzl
 * @version 1.0
 * @desception 自定义验证类
 * @date 2019/6/13 16:18
 */
public class MyAuthenticationHandler extends AbstractUsernamePasswordAuthenticationHandler {
    public MyAuthenticationHandler(String name, ServicesManager servicesManager, PrincipalFactory principalFactory, Integer order) {
        super(name, servicesManager, principalFactory, order);
    }

    /**
     * 自定义验证策略
     * 这里是只有admin用户可以登录，并且不验证密码
     * @param credential
     * @param originalPassword
     * @return
     * @throws GeneralSecurityException
     * @throws PreventedException
     */
    @Override
    protected AuthenticationHandlerExecutionResult authenticateUsernamePasswordInternal(UsernamePasswordCredential credential, String originalPassword) throws GeneralSecurityException, PreventedException {
        if("admin".equals(credential.getUsername())){
            return createHandlerResult(credential,
                    this.principalFactory.createPrincipal(credential.getUsername()),
                    new ArrayList<>(0));
        }else{
            throw new AccountNotFoundException("必须是admin用户才允许通过");
        }
    }

}
