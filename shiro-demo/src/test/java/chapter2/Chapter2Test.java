package chapter2;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;
import xyz.raowei.logger.SimpleLogger;

/**
 * ${DESCRIPTION}
 * create: 2016-06-30 14:33
 *
 * @author admin
 */
public class Chapter2Test {
    private SimpleLogger logger = SimpleLogger.getLogger(this.getClass());
    @Test
    public void testHelloWorld() {
        //1.获取SecurityManage工厂，此处使用Ini配置文件初始化SecurityManager
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        //2.得到SecurityManage实例，并绑定给SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        //3.得到Subject及创建用户\密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("zhang","123123");

        try {
            // 4.登录认证
            subject.login(token);
        } catch (AuthenticationException e) {
            // 5.身份验证失败
            e.printStackTrace();
        }
        Assert.assertEquals(true,subject.isAuthenticated());
        logger.info("登录成功");

        subject.logout();



    }
}
