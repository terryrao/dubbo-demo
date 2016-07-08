package chapter6.permission.dao;

import chapter6.permission.entity.Role;

/**
 * ${DESCRIPTION}
 * create: 2016-07-08 16:36
 *
 * @author admin
 */
public interface RoleDao {

    public Role createRole(Role role);

    public void deleteRole(Long roleId);

    public void correlationPermissions(Long roleId, Long... permissionIds);

    public void uncorrelationPermissions(Long roleId, Long... permissionIds);
}
