package chapter6.permission.service.impl;

import chapter6.permission.entity.Role;
import chapter6.permission.service.RoleService;

/**
 * ${DESCRIPTION}
 * create: 2016-07-08 15:33
 *
 * @author admin
 */
public class RoleServiceImpl implements RoleService {
    @Override
    public Role createRole(Role role) {
        return null;
    }

    @Override
    public void delete(Long roleId) {

    }

    @Override
    public void correlationPermission(Long roleId, Long... permissionsId) {

    }

    @Override
    public void uncorrelationPermission(Long roleId, Long... permissionIds) {

    }
}
