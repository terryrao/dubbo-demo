package chapter6.permission.service;

import chapter6.permission.entity.Role;

/**
 * @author terryrao on 2016/7/4.
 */
public interface RoleService {

    Role createRole(Role role);

    void delete(Long roleId);

    /**
     * 创建 role 与 permission之间的关系
     *
     * @param roleId
     * @param permissionsId
     */
    void correlationPermission(Long roleId, Long... permissionsId);

    /**
     * 移除role 与permission之间的关系
     */
    void uncorrelationPermission(Long roleId, Long... permissionIds);

}
