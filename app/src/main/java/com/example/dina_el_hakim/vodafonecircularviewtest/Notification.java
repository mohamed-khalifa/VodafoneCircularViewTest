package com.example.dina_el_hakim.vodafonecircularviewtest;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.StringDef;

import com.google.gson.annotations.SerializedName;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

public class Notification implements Parcelable {

    public static final String DEFAULT = "";
    public static final String DISMISS = "DISMISS";
    public static final String MORE_DATA = "MORE_DATA";
    public static final String EUROTRAVELLER = "EUROTRAVELLER";

    @StringDef({DEFAULT, DISMISS, MORE_DATA, EUROTRAVELLER})
    @Retention(RetentionPolicy.SOURCE)
    public @interface TriggerType {
    }

    @SerializedName("id")
    private Integer id;
    @SerializedName("short_title")
    private String shortTitle;
    @SerializedName("title")
    private String title;
    @SerializedName("secondaryTitle")
    private String secondaryTitle;
    @SerializedName("short_message")
    private String shortMessage;
    @SerializedName("message")
    private String message;
    @SerializedName("priority")
    private Integer priority;
    @SerializedName("dismissDuration")
    private Integer dismissDuration;
    @SerializedName("expiryDate")
    private String expiryDate;
    @SerializedName("buttons")
    private List<NotificationButton> buttons = new ArrayList<>();
    @SerializedName("activeDate")
    private String activeDate;
    @SerializedName("renderType")
    private Integer renderType;
    @SerializedName("triggerType")
    private
    @TriggerType
    String triggerType;
    @SerializedName("triggerText")
    private String trigger;
    @SerializedName("messageSetId")
    private Integer messageSetId;

    private Integer sourcePriority = 0;
    private boolean isAnimated = false;

    public Notification() {
    }

    public Notification(Integer id, String shortTitle, String title, String secondaryTitle, String shortMessage,
                        String message, Integer priority, Integer dismissDuration, String expiryDate,
                        List<NotificationButton> buttons, String activeDate, Integer renderType, @TriggerType String triggerType,
                        String triger, Integer set, Integer sourcePriority) {
        this.id = id;
        this.shortTitle = shortTitle;
        this.title = title;
        this.secondaryTitle = secondaryTitle;
        this.shortMessage = shortMessage;
        this.message = message;
        this.priority = priority;
        this.dismissDuration = dismissDuration;
        this.expiryDate = expiryDate;
        this.buttons = buttons;
        this.activeDate = activeDate;
        this.renderType = renderType;
        this.triggerType = triggerType;
        this.trigger = triger;
        this.messageSetId = set;
        this.sourcePriority = sourcePriority;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSecondaryTitle() {
        return secondaryTitle;
    }

    public void setSecondaryTitle(String secondaryTitle) {
        this.secondaryTitle = secondaryTitle;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getDismissDuration() {
        return dismissDuration;
    }

    public void setDismissDuration(Integer dismissDuration) {
        this.dismissDuration = dismissDuration;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public List<NotificationButton> getButtons() {
        return buttons;
    }

    public void setButtons(List<NotificationButton> buttons) {
        this.buttons = buttons;
    }

    public String getActiveDate() {
        return activeDate;
    }

    public void setActiveDate(String activeDate) {
        this.activeDate = activeDate;
    }

    public Integer getRenderType() {
        return renderType;
    }

    public void setRenderType(Integer renderType) {
        this.renderType = renderType;
    }

    public
    @TriggerType
    String getTriggerType() {
        return triggerType == null ? Notification.DEFAULT : triggerType;
    }

    public void setTriggerType(@TriggerType String triggerType) {
        this.triggerType = triggerType;
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }

    public String getShortMessage() {
        return shortMessage;
    }

    public void setShortMessage(String shortMessage) {
        this.shortMessage = shortMessage;
    }

    public String getTrigger() {
        return trigger == null ? "" : trigger;
    }

    public void setTrigger(String trigger) {
        this.trigger = trigger;
    }

    public Integer getSet() {
        return messageSetId == null ? -1 : messageSetId;
    }

    public void setSet(Integer set) {
        this.messageSetId = set;
    }

    public Integer getSourcePriority() {
        return sourcePriority;
    }

    public void setSourcePriority(Integer sourcePriority) {
        this.sourcePriority = sourcePriority;
    }

    public boolean isAnimated() {
        return isAnimated;
    }

    public void setAnimated(boolean animated) {
        isAnimated = animated;
    }

    protected Notification(Parcel in) {
        id = in.readByte() == 0x00 ? null : in.readInt();
        shortTitle = in.readString();
        title = in.readString();
        secondaryTitle = in.readString();
        shortMessage = in.readString();
        message = in.readString();
        priority = in.readByte() == 0x00 ? null : in.readInt();
        dismissDuration = in.readByte() == 0x00 ? null : in.readInt();
        expiryDate = in.readString();
        if (in.readByte() == 0x01) {
            buttons = new ArrayList<>();
            in.readList(buttons, NotificationButton.class.getClassLoader());
        } else {
            buttons = null;
        }
        activeDate = in.readString();
        renderType = in.readByte() == 0x00 ? null : in.readInt();
        triggerType = in.readString();
        trigger = in.readString();
        messageSetId = in.readInt();
        sourcePriority = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(id);
        }
        dest.writeString(shortTitle);
        dest.writeString(title);
        dest.writeString(secondaryTitle);
        dest.writeString(shortMessage);
        dest.writeString(message);
        if (priority == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(priority);
        }
        if (dismissDuration == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(dismissDuration);
        }
        dest.writeString(expiryDate);
        if (buttons == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(buttons);
        }
        dest.writeString(activeDate);
        if (renderType == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(renderType);
        }
        dest.writeString(triggerType);
        dest.writeString(trigger);
        dest.writeInt(messageSetId);
        dest.writeInt(sourcePriority);
    }

    public static final Creator<Notification> CREATOR = new Creator<Notification>() {
        @Override
        public Notification createFromParcel(Parcel in) {
            return new Notification(in);
        }

        @Override
        public Notification[] newArray(int size) {
            return new Notification[size];
        }
    };
}
