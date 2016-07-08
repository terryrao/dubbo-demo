package chapter6.permission.service;

import chapter6.permission.entity.Permission;

/**
 * @author terryrao on 2016/7/3.
 */
public interface PermissionService {

    Permission createPermission(Permission permission);

    void deletePermission(Long permissionId);

}
