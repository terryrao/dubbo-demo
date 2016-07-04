package chapter6.permission.entity;

/**
 * @author terryrao on 2016/7/3.
 */
public class RolePermission {

    private Long permissionId;
    private Long RoleId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RolePermission that = (RolePermission) o;

        if (permissionId != null ? !permissionId.equals(that.permissionId) : that.permissionId != null) return false;
        return RoleId != null ? RoleId.equals(that.RoleId) : that.RoleId == null;

    }

    @Override
    public int hashCode() {
        int result = permissionId != null ? permissionId.hashCode() : 0;
        result = 31 * result + (RoleId != null ? RoleId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RolePermission{" +
                "permissionId=" + permissionId +
                ", RoleId=" + RoleId +
                '}';
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public Long getRoleId() {
        return RoleId;
    }

    public void setRoleId(Long roleId) {
        RoleId = roleId;
    }
}
