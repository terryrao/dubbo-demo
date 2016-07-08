package chapter6.permission.dao.impl;

import chapter6.permission.JdbcTemplatesUtils;
import chapter6.permission.dao.UserDao;
import chapter6.permission.entity.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.PreparedStatement;
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
        jdbcTemplate.update(connection ->{
            PreparedStatement ppst = connection.prepareStatement(sql,new String[]{"id"});


            return ppst;

        },keyHolder);
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUser(Long userId) {

    }

    @Override
    public void correlationRoles(Long userId, Long... roleIds) {

    }

    @Override
    public void uncorrelationRoles(Long userId, Long... roleIds) {

    }

    @Override
    public User findOne(Long userId) {
        return null;
    }

    @Override
    public User findByUsername(String username) {
        return null;
    }

    @Override
    public Set<String> findRoles(String username) {
        return null;
    }

    @Override
    public Set<String> findPermissions(String username) {
        return null;
    }
}
