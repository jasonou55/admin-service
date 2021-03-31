package com.jarvis.adminservice.request;

import org.springframework.stereotype.Component;

@Component
public class NavigationRequest extends GenericRequestImpl {

    private String icon;
    private String title;
    private String toUrl;
    private long orderIs;

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

    public long getOrderIs() {
        return orderIs;
    }

    public void setOrderIs(final long orderIs) {
        this.orderIs = orderIs;
    }
}
