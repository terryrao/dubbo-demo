package chapter2;

import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.HashRequest;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;
import org.junit.Assert;
import org.junit.Test;
import xyz.raowei.logger.SimpleLogger;

import java.security.Key;

/**
 * @author terryrao on 2016/7/2.
 */
public class ShiroEncryptionTest {

    private SimpleLogger logger = SimpleLogger.getLogger(this.getClass());

    /**
     * 1、首先创建一个DefaultHashService，默认使用SHA-512算法；
     2、可以通过hashAlgorithmName属性修改算法；
     3、可以通过privateSalt设置一个私盐，其在散列时自动与用户传入的公盐混合产生一个新盐；
     4、可以通过generatePublicSalt属性在用户没有传入公盐的情况下是否生成公盐；
     5、可以设置randomNumberGenerator用于生成公盐；
     6、可以设置hashIterations属性来修改默认加密迭代次数；
     7、需要构建一个HashRequest，传入算法、数据、公盐、迭代次数。
     */
    @Test
    public void test() {
        DefaultHashService hashService = new DefaultHashService();// 默认算法sha-512
        hashService.setHashAlgorithmName("SHA-512");
        hashService.setPrivateSalt(new SimpleByteSource("你妹"));
        hashService.setGeneratePublicSalt(true);
        hashService.setRandomNumberGenerator(new SecureRandomNumberGenerator());//用于生成公钥
        hashService.setHashIterations(1);

        HashRequest request = new HashRequest.Builder().setAlgorithmName("MD5").setSource(ByteSource.Util.bytes("你大爷"))
                .setSalt(new SimpleByteSource("你大爷")).setIterations(2).build();
        String hex = hashService.computeHash(request).toHex();
        System.out.println(hex);
    }

    /**
     * 随机数
     */
    @Test
    public void test2() {

        SecureRandomNumberGenerator generator = new SecureRandomNumberGenerator();
        generator.setSeed("123".getBytes());
        String hex = generator.nextBytes().toHex();
        logger.info(hex);


    }

    /**
     * 称式加密/解密算法  ASE
     */
    @Test
    public void test3() {
        AesCipherService aesCipherService = new AesCipherService();
        aesCipherService.setKeySize(128);
        Key key = aesCipherService.generateNewKey();
        String text = "hello";

        String hex = aesCipherService.encrypt(text.getBytes(), key.getEncoded()).toHex();

        String text2 = new String (aesCipherService.decrypt(Hex.decode(hex),key.getEncoded()).getBytes());
        logger.info(text2);
        Assert.assertEquals(text,text2);

    }
}
