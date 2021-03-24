package com.jarvis.adminservice.entity;

import org.springframework.stereotype.Repository;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Repository
@Entity
@Table(name = "navigations")
public class Navigation extends BaseEntity {

    @Column(nullable = false)
    private String icon;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String to;

    public String getIcon() {
        return icon;
    }

    public void setIcon(final String icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getTo() {
        return to;
    }

    public void setTo(final String to) {
        this.to = to;
    }
}
