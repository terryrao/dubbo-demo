package chapter6.permission.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * @author terryrao on 2016/8/9.
 */
public class MyRealm2 implements Realm {
    @Override
    public String getName() {
        return "b";
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        return new SimpleAuthenticationInfo("zhang", "123", getName());
    }
}
