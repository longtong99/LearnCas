package util;/**
 * Created by admin on 2019/6/12.
 */

import org.apache.shiro.crypto.hash.ConfigurableHashService;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.HashRequest;
import org.apache.shiro.util.ByteSource;

/**
 * @author zzl
 * @version 1.0
 * @desception 盐值加密工具类
 * @date 2019/6/12 16:15
 */
public class PasswordSalt {

    //静态盐值
    private static  String staticSalt = ".";

    /**
     *
     * @param algorithmName 加密算法名
     * @param dynaSalt 盐值
     * @param encodedPassword 密码
     * @return
     */
    public static String encode(String algorithmName,String dynaSalt,String encodedPassword){
        ConfigurableHashService hashService = new DefaultHashService();
        hashService.setPrivateSalt(ByteSource.Util.bytes(staticSalt));
        hashService.setHashAlgorithmName(algorithmName);
        hashService.setHashIterations(2);
        HashRequest request = new HashRequest.Builder()
                .setSalt(dynaSalt)
                .setSource(encodedPassword)
                .build();
        String res =  hashService.computeHash(request).toHex();
        return res;
    }

    public static void main(String[] args) {
        System.out.println(encode("MD5", "zzl", "123456"));
    }

}
