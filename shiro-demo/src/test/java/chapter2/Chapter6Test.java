package chapter2;

import chapter6.permission.entity.Permission;
import chapter6.permission.entity.Role;
import chapter6.permission.entity.User;
import chapter6.permission.service.PermissionService;
import chapter6.permission.service.RoleService;
import chapter6.permission.service.UserService;
import chapter6.permission.service.impl.PermissionServiceImpl;
import chapter6.permission.service.impl.RoleServiceImpl;
import chapter6.permission.service.impl.UserServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;
import org.junit.Test;

import static chapter6.permission.JdbcTemplatesUtils.getJdbcTemplate;

/**
 * @author terryrao on 2016/8/6.
 */
public class Chapter6Test {
    protected UserService userService = new UserServiceImpl();
    protected PermissionService permissionService = new PermissionServiceImpl();
    protected RoleService roleService = new RoleServiceImpl();

    protected String password = "123";

    protected Permission p1;
    protected Permission p2;
    protected Permission p3;
    protected Role r1;
    protected Role r2;
    protected User u1;
    protected User u2;
    protected User u3;
    protected User u4;

    @Test
    public void before() {
        getJdbcTemplate().update("delete from sys_users");
        getJdbcTemplate().update("delete from sys_roles");
        getJdbcTemplate().update("delete from sys_permissions");
        getJdbcTemplate().update("delete from sys_users_roles");
        getJdbcTemplate().update("DELETE  FROM  sys_users");

        //1.新增权限
        p1 = new Permission("user:create","用户新增模块",Boolean.TRUE);
        p2 = new Permission("user:update", "用户模块修改", Boolean.TRUE);
        p3 = new Permission("menu:create", "菜单模块新增", Boolean.TRUE);
        permissionService.createPermission(p1);
        permissionService.createPermission(p2);
        permissionService.createPermission(p3);

        //2.新增角色

        r1 = new Role("admin","管理员", Boolean.TRUE);
        r2 = new Role("user","用户管理员", Boolean.TRUE);

        roleService.createRole(r1);
        roleService.createRole(r2);

        //关联角色权限
        roleService.correlationPermission(r1.getId(),p1.getId());
        roleService.correlationPermission(r1.getId(),p2.getId());
        roleService.correlationPermission(r1.getId(),p2.getId());
        roleService.correlationPermission(r1.getId(),p1.getId());
        roleService.correlationPermission(r2.getId(),p2.getId());

        //新增用户

        u1 = new User("zhang",password);
        u2 = new User("li",password);
        u3 = new User("wu",password);
        u4 = new User("wang",password);
        u4.setLocked(Boolean.TRUE);
        userService.createUser(u1);
        userService.createUser(u2);
        userService.createUser(u3);
        userService.createUser(u4);
        userService.correlationRoles(u1.getId(),r1.getId());
    }

    @After
    public void tearDown() throws Exception {
        ThreadContext.unbindSubject();//退出时请解除绑定Subject到线程 否则对下次测试造成影响
    }

    protected void login (String configFile,String username,String password) {
        //1.获取securityManager
        org.apache.shiro.util.Factory<SecurityManager> factory =
                new IniSecurityManagerFactory(configFile);

        SecurityManager securityManager = factory.getInstance();
        //2 绑定给SecurityUtils
        SecurityUtils.setSecurityManager(securityManager);

        //得到subject 及创建用户名/密码身份证明token
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        subject.login(token);

    }


    public Subject getSubject() {
        return SecurityUtils.getSubject();

    }
}
