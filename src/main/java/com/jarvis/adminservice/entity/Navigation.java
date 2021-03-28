package com.jarvis.adminservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "navigations")
public class Navigation extends GenericEntityImpl {

    @Column(nullable = false)
    private String icon;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false)
    private String toUrl;

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

    public String getToUrl() {
        return toUrl;
    }

    public void setToUrl(final String toUrl) {
        this.toUrl = toUrl;
    }
}
