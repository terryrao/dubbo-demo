package chapter6.permission.service.impl;

import chapter6.permission.dao.UserDao;
import chapter6.permission.dao.impl.UserDaoImpl;
import chapter6.permission.entity.User;
import chapter6.permission.service.UserService;

import java.util.Set;

/**
 * @author terryrao on 2016/7/10.
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    @Override
    public User createUser(User user) {
        return this.userDao.createUser(user);
    }

    @Override
    public void changePassword(Long userId, String password) {
        User user = this.userDao.findOne(userId);
        user.setPassword(password);
        this.userDao.updateUser(user);
    }

    @Override
    public void correlationRoles(Long userId, Long... roleIds) {
        this.userDao.correlationRoles(userId,roleIds);
    }

    @Override
    public void unrelationRoles(Long userId, Long... roleIds) {
        this.userDao.uncorrelationRoles(userId,roleIds);
    }

    @Override
    public User findByUsername(String name) {
        return this.userDao.findByUsername(name);
    }

    @Override
    public Set<String> findRoles(String username) {
        return this.userDao.findRoles(username);
    }

    @Override
    public Set<String> findPermission(String username) {
        return this.userDao.findPermissions(username);
    }
}
