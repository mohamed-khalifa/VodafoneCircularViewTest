package com.example.dina_el_hakim.vodafonecircularviewtest;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 * Created by Mostafa Montaser on 1/4/2017.
 */

public class UpgradeInfo extends VodafoneFeedResponse {

    @SerializedName("upgradeInfo")
    private Map<String, String> upgradeDataMap;
    @SerializedName("screensContent")
    private DynamicLayoutModel screenContent;

    private transient UpgradeData upgradeData;

    public UpgradeData getUpgradeData() {
        return upgradeData == null ? new UpgradeData(upgradeDataMap) : upgradeData;
    }

    public void setUpgradeData(UpgradeData upgradeData) {
        this.upgradeData = upgradeData;
    }

    public DynamicLayoutModel getScreenContent() {
        return screenContent;
    }

    public void setScreenContent(DynamicLayoutModel screenContent) {
        this.screenContent = screenContent;
    }
}
