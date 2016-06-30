package xyz.raowei.chapter3;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * ${DESCRIPTION}
 * create: 2016-06-30 19:24
 *
 * @author admin
 */
public class MyRealm1 implements Realm {
    @Override
    public String getName() {
        return "myRealM1";
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        //仅支持 UserNamePasswordToken
        return token instanceof UsernamePasswordToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String username = (String) token.getPrincipal();
        String password = (String) token.getCredentials();
        if (!StringUtils.equals("123",password)) {
            throw new IncorrectCredentialsException("密码错误");
        }
        return new SimpleAuthenticationInfo(username,password,getName());
    }
}
