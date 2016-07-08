package chapter6.permission.service.impl;

import chapter6.permission.dao.PermissionDao;
import chapter6.permission.dao.impl.PermissionDaoImpl;
import chapter6.permission.entity.Permission;
import chapter6.permission.service.PermissionService;

/**
 * create: 2016-07-08 15:26
 *
 * @author admin
 */
public class PermissionServiceImpl implements PermissionService {

    private PermissionDao dao = new PermissionDaoImpl();

    @Override
    public Permission createPermission(Permission permission) {
        return dao.createPermission(permission);
    }

    @Override
    public void deletePermission(Long permissionId) {
        dao.deletePermission(permissionId);
    }
}
