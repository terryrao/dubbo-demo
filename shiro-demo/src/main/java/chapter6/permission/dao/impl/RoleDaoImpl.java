package chapter6.permission.dao.impl;

import chapter6.permission.JdbcTemplatesUtils;
import chapter6.permission.dao.RoleDao;
import chapter6.permission.entity.Role;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.PreparedStatement;

/**
 * ${DESCRIPTION}
 * create: 2016-07-08 16:38
 *
 * @author admin
 */
public class RoleDaoImpl implements RoleDao {

    private JdbcTemplate jdbcTemplate = JdbcTemplatesUtils.getJdbcTemplate();

    @Override
    public Role createRole(Role role) {
        String sql = "insert into sys_role(role,description, available) values (?,?,?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql,new String[]{"id"});
            preparedStatement.setString(1,role.getRole());
            preparedStatement.setString(2,role.getRole());
            preparedStatement.setBoolean(3,role.getAvailable());
            return preparedStatement;

        },keyHolder);
        role.setId(keyHolder.getKey().longValue());
        return role;
    }

    @Override
    public void deleteRole(Long roleId) {
        String deleteUserRoleSql = "delete from sys_role where role_id = ?";
        jdbcTemplate.update(deleteUserRoleSql,roleId);

        String deleteRole = "delete from sys_role where id = ?";
        jdbcTemplate.update(deleteRole,roleId);
    }

    @Override
    public void correlationPermissions(Long roleId, Long... permissionIds) {
        if (permissionIds == null || permissionIds.length == 0) {
            return ;
        }

        String sql = "insert into sys_roles_permissions(role_id, permission_id) values (?,?)";
        for (Long permissionId : permissionIds) {
            if (!exists(roleId,permissionId)) {
                jdbcTemplate.update(sql,roleId,permissionId);
            }
        }
    }

    @Override
    public void uncorrelationPermissions(Long roleId, Long... permissionIds) {
        if (ArrayUtils.isEmpty(permissionIds)) {
            return;
        }

        String sql = "delete from sys_roles_permissions where role_id = ? and permission_id = ?";

        for (Long permissionId : permissionIds) {
            if (!exists(roleId,permissionId)) {
                jdbcTemplate.update(sql,roleId,permissionId);
            }

        }
    }


    private boolean exists(Long roleId, Long permissionId) {
        String sql = "select count(1) from sys_roles_permissions where role_id = ? and permission_id = ?";
        return jdbcTemplate.queryForObject(sql,Integer.class,roleId,permissionId) != 0;
    }
}
