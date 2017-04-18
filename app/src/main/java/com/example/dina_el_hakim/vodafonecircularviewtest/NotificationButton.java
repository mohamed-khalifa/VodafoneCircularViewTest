package com.example.dina_el_hakim.vodafonecircularviewtest;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class NotificationButton implements Serializable {

    @SerializedName("title")
    private String title;
    @SerializedName("target")
    private String target;
    @SerializedName("style")
    private Integer style;

    public NotificationButton(String title, Integer style) {
        this.title = title;
        this.style = style;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTarget() {
        return target == null ? "" : target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Integer getStyle() {
        return style;
    }

    public void setStyle(Integer style) {
        this.style = style;
    }
}
