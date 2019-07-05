package com.zzl.cas.config;/**
 * Created by admin on 2019/7/4.
 */

import org.jasig.cas.client.authentication.UrlPatternMatcherStrategy;

import java.util.regex.Pattern;

/**
 * @author zzl
 * @version 1.0
 * @desception 自定义哪些路径不需要过滤
 * @date 2019/7/4 18:16
 */
public class SimpleUrlPatternMatcherStrategy implements UrlPatternMatcherStrategy {

    private Pattern pattern;

    /**
     * 路径是否需要登录，true不需要
     * @param url 访问的路径，注意这里的是全路径，如http://app1.cas.com:8081/insert?user=lisi
     * @return
     */
    @Override
    public boolean matches(String url) {
        //包含/insert的路径可以放行
        if(url.contains("/insert")){
            return true;
        }
        //默认是根据cas.ignore-pattern来判断是否否满足过滤
        return this.pattern.matcher(url).find();
    }

    /**
     * 这个pattern就是 在web.xml中配置的ignorePattern
     * @param pattern
     */
    @Override
    public void setPattern(String pattern) {
        this.pattern = Pattern.compile(pattern);
    }
}
