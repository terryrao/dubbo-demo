package chapter6.permission.dao.impl;

import chapter6.permission.JdbcTemplatesUtils;
import chapter6.permission.dao.UserDao;
import chapter6.permission.entity.User;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.PreparedStatement;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * ${DESCRIPTION}
 * create: 2016-07-08 17:09
 *
 * @author admin
 */
public class UserDaoImpl implements UserDao {
    private JdbcTemplate jdbcTemplate = JdbcTemplatesUtils.getJdbcTemplate();

    @Override
    public User createUser(User user) {
        String sql = "insert into sys_users(username,password,salt,locked) values(?,?,?,?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ppst = connection.prepareStatement(sql, new String[]{"id"});
            ppst.setString(1, user.getUserName());
            ppst.setString(2, user.getPassword());
            ppst.setString(3, user.getSalt());
            ppst.setBoolean(4, user.getLocked());
            return ppst;

        }, keyHolder);
        return user;
    }

    @Override
    public void updateUser(User user) {
        String sql = "update sys_users set username = ?, password = ?,salt = ?,locked = ? where id = ?";
        jdbcTemplate.update(sql, user.getUserName(), user.getPassword(), user.getSalt(), user.getLocked(), user.getId());
    }

    @Override
    public void deleteUser(Long userId) {
        String sql = "delete from sys_users_roles  where id = ?";
        jdbcTemplate.update(sql,userId);
    }

    @Override
    public void correlationRoles(Long userId, Long... roleIds) {
        if (ArrayUtils.isNotEmpty(roleIds)) {
            String sql = "insert into user_roles(user_id, roleId) value (?,?)";
            for (Long roleId : roleIds) {
                if (!exists(userId,roleId)) {
                    jdbcTemplate.update(sql,userId,roleId);
                }
            }
        }

    }

    @Override
    public void uncorrelationRoles(Long userId, Long... roleIds) {
        if (ArrayUtils.isNotEmpty(roleIds)) {
            String sql = "DELETE FROM sys_user_roles where user_id = ? ,role_id = ?";
            for (Long roleId : roleIds) {
                if (!exists(userId,roleId)) {
                    jdbcTemplate.update(sql,userId,roleId);
                }
            }
        }
    }

    @Override
    public User findOne(Long userId) {
        String sql = "select id,username,password,salt,locked from sys_users where id = ?";
        List<User> users = jdbcTemplate.query(sql,new BeanPropertyRowMapper<User>(User.class),userId);
        if (CollectionUtils.isNotEmpty(users)) {
            return users.get(0);
        }else {
            return null;
        }
    }

    @Override
    public User findByUsername(String username) {
        String sql = "select id, username, password, salt, locked from sys_users where username=?";
        List<User> userList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(User.class), username);
        if(userList.size() == 0) {
            return null;
        }
        return userList.get(0);
    }

    @Override
    public Set<String> findRoles(String username) {
        String sql = "select role from sys_users u, sys_roles r,sys_users_roles ur where u.username=? and u.id=ur.user_id and r.id=ur.role_id";
        return new HashSet(jdbcTemplate.queryForList(sql, String.class, username));
    }

    @Override
    public Set<String> findPermissions(String username) {
        //TODO 此处可以优化，比如查询到role后，一起获取roleId，然后直接根据roleId获取即可
        String sql = "select permission from sys_users u, sys_roles r, sys_permissions p, sys_users_roles ur, sys_roles_permissions rp where u.username=? and u.id=ur.user_id and r.id=ur.role_id and r.id=rp.role_id and p.id=rp.permission_id";
        return new HashSet(jdbcTemplate.queryForList(sql, String.class, username));
    }

    private boolean exists(Long userId, Long roleId) {
        String sql = "select count(*) from sys_users_roles where userId = ? and role_id = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class,userId,roleId) != 0;
    }
}
