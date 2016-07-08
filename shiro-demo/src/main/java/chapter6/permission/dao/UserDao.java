package chapter6.permission.dao;

import chapter6.permission.entity.User;

import java.util.Set;

/**
 * ${DESCRIPTION}
 * create: 2016-07-08 17:08
 *
 * @author admin
 */
public interface UserDao {
    User createUser(User user);

    void updateUser(User user);

    void deleteUser(Long userId);

    void correlationRoles(Long userId, Long... roleIds);

    void uncorrelationRoles(Long userId, Long... roleIds);

    User findOne(Long userId);

    User findByUsername(String username);

    Set<String> findRoles(String username);

    Set<String> findPermissions(String username);
}
