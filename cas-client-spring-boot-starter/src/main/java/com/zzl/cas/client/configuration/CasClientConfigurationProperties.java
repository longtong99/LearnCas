package com.zzl.cas.client.configuration;/**
 * Created by admin on 2019/7/3.
 */

import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.constraints.NotNull;

/**
 * @author zzl
 * @version 1.0
 * @desception 配置类
 * 参考的 net.unicon.cas.client.configuration.CasClientConfigurationProperties
 * @date 2019/7/3 15:57
 */
@ConfigurationProperties(prefix = "cas", ignoreUnknownFields = false)
public class CasClientConfigurationProperties {

    /**
     * CAS server URL E.g. https://example.com/cas or https://cas.example. Required.
     * CAS 服务端 url 不能为空
     */
    @NotNull
    private String serverUrlPrefix = "https://localhost:8443/cas";

    /**
     * CAS server login URL E.g. https://example.com/cas/login or https://cas.example/login. Required.
     * CAS 服务端登录地址  上面的连接 加上/login 该参数不能为空
     */
    @NotNull
    private String serverLoginUrl = "https://localhost:8443/cas/login";

    /**
     * CAS-protected client application host URL E.g. https://myclient.example.com Required.
     * 当前客户端的地址
     */
    @NotNull
    private String clientHostUrl = "http://localhost:8081";

    /**
     * 忽略规则,访问那些地址 不需要登录
     */
    private String ignorePattern;

    /**
     * 自定义UrlPatternMatcherStrategy验证
     */
    private String ignoreUrlPatternType;


    public String getServerUrlPrefix() {
        return serverUrlPrefix;
    }

    public void setServerUrlPrefix(String serverUrlPrefix) {
        this.serverUrlPrefix = serverUrlPrefix;
    }

    public String getServerLoginUrl() {
        return serverLoginUrl;
    }

    public void setServerLoginUrl(String serverLoginUrl) {
        this.serverLoginUrl = serverLoginUrl;
    }

    public String getClientHostUrl() {
        return clientHostUrl;
    }

    public void setClientHostUrl(String clientHostUrl) {
        this.clientHostUrl = clientHostUrl;
    }

    public String getIgnorePattern() {
        return ignorePattern;
    }

    public void setIgnorePattern(String ignorePattern) {
        this.ignorePattern = ignorePattern;
    }

    public String getIgnoreUrlPatternType() {
        return ignoreUrlPatternType;
    }

    public void setIgnoreUrlPatternType(String ignoreUrlPatternType) {
        this.ignoreUrlPatternType = ignoreUrlPatternType;
    }

}
