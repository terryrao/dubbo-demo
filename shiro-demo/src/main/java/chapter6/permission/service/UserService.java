package chapter6.permission.service;

import chapter6.permission.entity.Role;
import chapter6.permission.entity.User;

import java.util.Set;

/**
 * @author terryrao on 2016/7/4.
 */
public interface UserService {

    User createUser(User user);

    void changePassword(Long userId,String password);

    void correlationRoles(Long userId,Long ...roleIds);

    void unrelationRoles(Long userId,Long ...roleIds);

    User findByUsername(String name);

    Set<String> findRoles(String username);

    Set<String> findPermission(String username);




}
