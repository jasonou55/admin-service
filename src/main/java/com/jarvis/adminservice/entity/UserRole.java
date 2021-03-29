package com.jarvis.adminservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "user_role")
public class UserRole extends GenericEntityImpl {

    @Column(nullable = false)
    private long userId;

    @Column(nullable = false)
    private long roleId;

    public long getUserId() {
        return userId;
    }

    public void setUserId(final long userId) {
        this.userId = userId;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(final long roleId) {
        this.roleId = roleId;
    }
}
