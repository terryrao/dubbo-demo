package chapter6.permission.realm;

import chapter6.permission.entity.User;
import chapter6.permission.service.UserService;
import chapter6.permission.service.impl.UserServiceImpl;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.security.auth.login.AccountNotFoundException;

/**
 * @author terryrao on 2016/7/10.
 */
public class UserRealm extends AuthorizingRealm {
    private UserService userService = new UserServiceImpl();

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection token) throws AuthenticationException {
        String name = (String) token.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(userService.findRoles(name));
        authorizationInfo.setStringPermissions(userService.findPermission(name));
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String name = (String) token.getPrincipal();
        User user = userService.findByUsername(name);
        if (user== null) {
            throw new UnknownAccountException("没有该账号");
        }
        if (user.getLocked()) {
            throw new LockedAccountException();
        }

        return new SimpleAuthenticationInfo(
                user.getUserName(),
                user.getPassword(),
                ByteSource.Util.bytes(user.getCredentialsSalt()),
                getName()
        );

    }
}
