package chapter6.permission.dao.impl;

import chapter6.permission.JdbcTemplatesUtils;
import chapter6.permission.dao.PermissionDao;
import chapter6.permission.entity.Permission;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author terryrao on 2016/7/4.
 */
public class PermissionDaoImpl implements PermissionDao {
    JdbcTemplate jdbcTemplate = JdbcTemplatesUtils.getJdbcTemplate();
    @Override
    public Permission createPermission(final Permission permission) {
        final String sql = "insert into sys_permissions(permission,description,available) values(?,?,?,?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement psst = connection.prepareStatement(sql,new String [] {"1"});
            psst.setString(1,permission.getPermission());
            psst.setString(2, permission.getDescription());
            psst.setBoolean(3,permission.getAvailable());
            return psst;
        }, keyHolder);
        permission.setId(keyHolder.getKey().longValue());
        return permission;
    }

    @Override
    public void deletePermission(Long permissionId) {

    }
}
