package chapter6.permission.realm;

import chapter6.permission.entity.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * @author terryrao on 2016/8/9.
 */
public class MyRealm3 implements Realm {
    @Override
    public String getName() {
        return "c";
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        User user = new User("zhang","123");
        return new SimpleAuthenticationInfo(user.getUserName(),"123",getName());
    }
}
