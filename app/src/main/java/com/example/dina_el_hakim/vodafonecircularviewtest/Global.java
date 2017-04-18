package com.example.dina_el_hakim.vodafonecircularviewtest;


import static com.example.dina_el_hakim.vodafonecircularviewtest.BuildConfig.DEBUG;
import static com.example.dina_el_hakim.vodafonecircularviewtest.BuildConfig.FLAVOR;

public class Global {

    //This should be same as is in strings - in here as sometimes it seemed to have a bug when retrieving
    // from package manager
    public final static String VERSION = "6.2";

    public static final String KEY_PROD_MSISDN = "Vo41Da56be9es";
    private static final boolean LOG_REQUESTS = true;

//    public static final VodafoneRequest.RIGTestMode DEFAULT_RIG_TEST_MODE = VodafoneRequest.RIGTestMode.CACHED;

    public static boolean isConfigurableBuild() {
        return FLAVOR.equalsIgnoreCase("configurable");
    }

    public static boolean isStagingBuild() {
        return FLAVOR.equalsIgnoreCase("staging");
    }

    public static boolean isProductionBuild() {
        return FLAVOR.equalsIgnoreCase("production");
    }

    public static boolean isRetailBuild() {
        return FLAVOR.equalsIgnoreCase("retail");
    }

    public static boolean isDebug() {
        return DEBUG;
    }

    public static boolean shouldLogRequests() {
        return LOG_REQUESTS;
    }

}
