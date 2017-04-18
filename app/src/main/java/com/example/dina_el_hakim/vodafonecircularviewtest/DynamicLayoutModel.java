package com.example.dina_el_hakim.vodafonecircularviewtest;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yahia on 1/4/17.
 */

public class DynamicLayoutModel {

    @SerializedName("bundle")
    private LayoutComponent[] bundleLayoutComponents;
    @SerializedName("products_services")
    private LayoutComponent[] productAndServicesLayoutComponents;
    @SerializedName("how_to_upgrade")
    private LayoutComponent[] upgradeScreenLayoutComponents;

    public LayoutComponent[] getBundleLayoutComponents() {
        return bundleLayoutComponents;
    }

    public void setBundleLayoutComponents(LayoutComponent[] bundleLayoutComponents) {
        this.bundleLayoutComponents = bundleLayoutComponents;
    }

    public LayoutComponent[] getProductAndServicesLayoutComponents() {
        return productAndServicesLayoutComponents;
    }

    public void setProductAndServicesLayoutComponents(LayoutComponent[] productAndServicesLayoutComponents) {
        this.productAndServicesLayoutComponents = productAndServicesLayoutComponents;
    }

    public LayoutComponent[] getUpgradeScreenLayoutComponents() {
        return upgradeScreenLayoutComponents;
    }

    public void setUpgradeScreenLayoutComponents(LayoutComponent[] upgradeScreenLayoutComponents) {
        this.upgradeScreenLayoutComponents = upgradeScreenLayoutComponents;
    }

    public static class LayoutComponent implements Parcelable {

        private String title;
        private String description;
        private DisplayType displayType;
        private String footer;

        private Action[] actions;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public DisplayType getDisplayType() {
            return displayType;
        }

        public void setDisplayType(DisplayType displayType) {
            this.displayType = displayType;
        }

        public String getFooter() {
            return footer;
        }

        public void setFooter(String footer) {
            this.footer = footer;
        }

        public Action[] getActions() {
            return actions;
        }

        public void setActions(Action[] actions) {
            this.actions = actions;
        }

        public LayoutComponent() {
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.title);
            dest.writeString(this.description);
            dest.writeInt(this.displayType == null ? -1 : this.displayType.ordinal());
            dest.writeString(this.footer);
            dest.writeTypedArray(this.actions, flags);
        }

        protected LayoutComponent(Parcel in) {
            this.title = in.readString();
            this.description = in.readString();
            int tmpDisplayType = in.readInt();
            this.displayType = tmpDisplayType == -1 ? null : DisplayType.values()[tmpDisplayType];
            this.footer = in.readString();
            this.actions = in.createTypedArray(Action.CREATOR);
        }

        public static final Creator<LayoutComponent> CREATOR = new Creator<LayoutComponent>() {
            @Override
            public LayoutComponent createFromParcel(Parcel source) {
                return new LayoutComponent(source);
            }

            @Override
            public LayoutComponent[] newArray(int size) {
                return new LayoutComponent[size];
            }
        };
    }

    public enum DisplayType {
        CARD("CARD"), CELL("CELL");

        @SerializedName("displayType")
        private String displayType;

        DisplayType(String displayType) {
            this.displayType = displayType;
        }

        public boolean equal(DisplayType displayType) {
            return this.displayType != null && this.displayType.equals(displayType.toString());
        }

        @Override
        public String toString() {
            return displayType;
        }

    }

    public static class Action implements Parcelable {

        public static final String COLOR_RED = "red";
        public static final String COLOR_GRAY = "gray";
        public static final String COLOR_LIGHT_GRAY = "light_gray";
        public static final String COLOR_DARK_GRAY = "dark_gray";

        private String text;
        private String color;
        private String journeyKey;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getJourneyKey() {
            return journeyKey;
        }

        public void setJourneyKey(String journeyKey) {
            this.journeyKey = journeyKey;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.text);
            dest.writeString(this.color);
            dest.writeString(this.journeyKey);
        }

        public Action() {
        }

        protected Action(Parcel in) {
            this.text = in.readString();
            this.color = in.readString();
            this.journeyKey = in.readString();
        }

        public static final Creator<Action> CREATOR = new Creator<Action>() {
            @Override
            public Action createFromParcel(Parcel source) {
                return new Action(source);
            }

            @Override
            public Action[] newArray(int size) {
                return new Action[size];
            }
        };
    }
}
