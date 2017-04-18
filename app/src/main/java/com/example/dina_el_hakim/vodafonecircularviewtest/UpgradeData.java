package com.example.dina_el_hakim.vodafonecircularviewtest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mostafa Montaser on 1/4/2017.
 */

public class UpgradeData {

    public static final String KEY_ELIGIBLE_FOR_UPGRADE = "eligibleForUpgrade";
    public static final String KEY_ELIGIBLE_TARIFF_MIGRATE = "eligibleTariffMigrate";

    private boolean eligibleForUpgrade;
    private boolean eligibleTariffMigrate;
    private Map<String, String> upgradeDataMap;

    public UpgradeData(Map<String, String> upgradeDataMap) {
        this.upgradeDataMap = upgradeDataMap == null ? new HashMap<String, String>() : upgradeDataMap;
        unwrapMap(upgradeDataMap);
    }

    private void unwrapMap(Map<String, String> upgradeDataMap) {
        if (upgradeDataMap != null) {
            eligibleForUpgrade = Boolean.valueOf(upgradeDataMap.get(KEY_ELIGIBLE_FOR_UPGRADE));
            eligibleTariffMigrate = Boolean.valueOf(upgradeDataMap.get(KEY_ELIGIBLE_TARIFF_MIGRATE));
        }
    }

    public boolean isEligibleForUpgrade() {
        return eligibleForUpgrade;
    }

    public void setEligibleForUpgrade(boolean eligibleForUpgrade) {
        this.eligibleForUpgrade = eligibleForUpgrade;
    }

    public boolean isEligibleTariffMigrate() {
        return eligibleTariffMigrate;
    }

    public void setEligibleTariffMigrate(boolean eligibleTariffMigrate) {
        this.eligibleTariffMigrate = eligibleTariffMigrate;
    }

    public String get(String key) {
        return upgradeDataMap.get(key);
    }
}
