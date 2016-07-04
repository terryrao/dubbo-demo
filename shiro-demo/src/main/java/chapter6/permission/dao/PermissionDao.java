package chapter6.permission.dao;

import chapter6.permission.entity.Permission;

/**
 * @author terryrao on 2016/7/4.
 */
public interface PermissionDao {


    Permission createPermission(Permission permission);

    void deletePermission(Long permissionId);
}
