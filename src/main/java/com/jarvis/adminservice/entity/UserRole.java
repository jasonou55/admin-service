package com.jarvis.adminservice.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;

@Entity(name = "user_role")
@EntityListeners(AuditingEntityListener.class)
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
