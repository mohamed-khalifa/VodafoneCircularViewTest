package com.example.dina_el_hakim.vodafonecircularviewtest;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Map;


public class ContentDetail extends VodafoneFeedResponse {

    @SerializedName("content")
    private Map<String, Object> mContent;

    @SerializedName("notifications")
    private ArrayList<Notification> mNotifications = new ArrayList<>();

    public Map<String, Object> getContent() {
        return mContent;
    }

    public void setContent(Map<String, Object> content) {
        mContent = content;
    }

    public ArrayList<Notification> getNotifications() {
        return mNotifications;
    }

    public void setNotifications(ArrayList<Notification> notifications) {
        mNotifications = notifications;
    }
}


