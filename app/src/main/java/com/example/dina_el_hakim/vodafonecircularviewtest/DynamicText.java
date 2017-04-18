package com.example.dina_el_hakim.vodafonecircularviewtest;

import android.content.Context;
import android.support.annotation.StringRes;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;

import com.google.gson.Gson;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.ReadContext;
import com.jayway.jsonpath.internal.JsonReader;

import net.minidev.json.JSONValue;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DynamicText {

    private static final String TAG = "DynamicText";//DynamicText.class.getSimpleName();

    private static final boolean DEBUG = false;

    private static Context sContext;
    private static Map<String, Object> sContentMap;
    private static HashMap<String, Integer> sKeyToResId;
    private static SparseArray<String> sResIdToKey;
    private static JsonReader sJsonReader;
    private static ReadContext sJsonReadContext;
    //json reader for upgrade info json
    private static JsonReader jsonReaderUpgrade;
    private static ReadContext upgradeJsonReadContext;

    public static synchronized void initialize(Context context, Field[] fields) {
        sContext = context.getApplicationContext();

        // Map R.strings generated IDs to their ContentDetail name
        // http://developer.android.com/reference/java/lang/Class.html#getDeclaredFields()
        // getDeclaredFields return array (should be primitive type) but for some reason (0 index) field is
        // public static transient volatile com.android.tools.fd.runtime.IncrementalChange
        // what crashing Dynamic text based on getInt method so we checking if field is primitive type
        // if not skip (as we don't want to getInt from different object type (that crashes)


        sResIdToKey = new SparseArray<>(fields.length);
        sKeyToResId = new HashMap<>(fields.length);

        for (Field field : fields) {
            try {
                // check if field is int type (primitive class member)
                // due
                if (field.getType().isPrimitive()) {
                    int fieldId = field.getInt(null);
                    String fieldName = field.getName();

                    sResIdToKey.put(fieldId, fieldName);
                    sKeyToResId.put(fieldName, fieldId);

                    if (DEBUG) {
                        Log.v(TAG, String.format("Mapping string id [%d] to field name [%s]", fieldId, fieldName));
                    }
                }
            } catch (IllegalArgumentException e) {
                Log.e(TAG, "IllegalArgumentException Error mapping string ids", e);
            } catch (Exception e) {
                Log.e(TAG, "Error mapping string ids", e);
            }
        }

        sJsonReader = new JsonReader(Configuration.defaultConfiguration());
        jsonReaderUpgrade = new JsonReader(Configuration.defaultConfiguration());

    }


    /**
     * Returns an HTML formatted CharSequence from the Strings in ContentDetail or the apps resources if not found
     * Strings containing JSON paths (tokenized by #value#), will be replaced by their respective value in
     * AccountInfo using JsonPath.
     *
     * @param stringRes A valid String resource
     */
    public static CharSequence textFromId(@StringRes int stringRes) {
        return textFromId(stringRes, true, true);
    }

    public static CharSequence textFromId(@StringRes int stringRes, boolean substitute, boolean formatHtml) {
        if (DEBUG) {
            Log.v(TAG, String.format("Lookup dynamicTextId [%d]...", stringRes));
        }

//        // Only show the string keys if on Configurable build
//        if (Global.isConfigurableBuild() && VodafoneAppConfig.get().shouldShowStringKeys()) {
//            return sResIdToKey.get(stringRes);
//        }

        CharSequence text = null;
        if (stringRes != 0) {
            if (sContentMap != null) {
                String key = sResIdToKey.get(stringRes);
                if (!TextUtils.isEmpty(key)) {
                    if (DEBUG) {
                        Log.v(TAG, String.format("Resolved dynamicTextId [%d] to key [%s]", stringRes, key));
                    }

                    // Look for the text in the content detail map
                    Object value = sContentMap.get(key);
                    if (value != null && value instanceof String) {
                        text = (String) value;
                    } else {
                        // Just some dev debug
                        if (value != null) {
                            Log.w(TAG, String.format("DYNTEXT FAILURE!!! Expected String when resolving dynamicTextId with key [%s] got type [%s]", key, text.getClass().getSimpleName()));
                        }
                    }
                } else {
                    if (DEBUG) {
                        Log.v(TAG, String.format("Unresolved dynamicTextId [%d], returning default value from resource strings", stringRes));
                    }
                }
            }

            // Fallback if no suitable replacement could be found
            if (TextUtils.isEmpty(text)) {
                if (DEBUG) {
                    Log.w(TAG, "No content detail set, returning default value from resource strings");
                }
                text = sContext.getString(stringRes);
            }
        } else {
            if (DEBUG) {
                Log.w(TAG, String.format("Invalid dynamicTextId [%d]", stringRes));
            }
        }

        //if substitution comes true
        if (substitute && text != null) {
            text = substituteText(text.toString());
        }

        if (formatHtml && text != null) {
            String textStr = text.toString();
            textStr = textStr.replace("\n", "<br/>");
            textStr = textStr.replace("\\n", "<br/>");
            text = Html.fromHtml(textStr);
        }

        return text;
    }

    private static String substituteText(String text) {
        Matcher matcher;
        ReadContext readContext;

        if (text == null) {
            return null;
        } else {
            //check on the substitution to update the value with either the subscription info json or upgrade info json
            if (text.contains("#") && sJsonReadContext != null && sJsonReadContext.json() != null) {
                matcher = Pattern.compile("#([^#]*)#").matcher(text);
                readContext = sJsonReadContext;
            } else if (text.contains("$~upgrades.") && upgradeJsonReadContext != null && upgradeJsonReadContext.json() != null) {
                matcher = Pattern.compile("\\$~upgrades\\.([^\\$~]*)\\$~").matcher(text);
                readContext = upgradeJsonReadContext;
            } else {
                return text;
            }
        }

        while (matcher.find()) {

            String pathWithToken = matcher.group(0);
            String path = matcher.group(1);

            try {
                String value = null;
                if (readContext != null) {
                    value = String.valueOf(readContext.read("$." + path));
                }
                //replace the path in the json with the corresponding
                if (value != null) {
                    text = text.replace(pathWithToken, value);
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.w(TAG, "No value for " + path);
            }
        }

        return text;
    }

    public static boolean doesKeyExistForResId(String key) {
        if (sKeyToResId.containsKey(key)) {
            return true;
        }

        return false;
    }

    public static Integer getResIdFromKey(String key) {
        return sKeyToResId.get(key);
    }

    public static CharSequence textFromString(String string) {
        return textFromString(string, true);
    }

    public static CharSequence textFromString(String string, boolean formatHtml) {
        if (TextUtils.isEmpty(string)) {
            if (DEBUG) {
                Log.w(TAG, "NULL String provided when calling textFromString");
            }
            return string;
        }

        if (sJsonReadContext == null || sJsonReadContext.json() == null) {
            if (DEBUG) {
                Log.w(TAG, "NULL JsonReadContext");
            }
            return string;
        }
        //replace this method with substituteText
        String substitutedString = substituteText(string);
        ;

        if (TextUtils.isEmpty(substitutedString)) {
            if (DEBUG) {
                Log.w(TAG, "Failed to substitute source text with AccountInfo");
            }
            return string;
        }

        CharSequence resultString;
        if (formatHtml) {
            String textStr = substitutedString.toString();
            textStr = textStr.replace("\n", "<br/>");
            textStr = textStr.replace("\\n", "<br/>");
            resultString = Html.fromHtml(textStr);
        } else {
            resultString = substitutedString;
        }

        return resultString;
    }

    public static CharSequence textFromStringKey(String key) {
        if (TextUtils.isEmpty(key)) {
            Log.d(TAG, "textFromStringKey() called with: key = [" + key + "]");
            return null;
        } else if (doesKeyExistForResId(key)) {
            return textFromId(getResIdFromKey(key));
        } else if (sContentMap != null && sContentMap.containsKey(key)) {
            Object value = sContentMap.get(key);
            if (value != null && value instanceof String) {
                return textFromString((String) value);
            }
        }
        return key;
    }

//    public static String substituteTextWithAccountInfo(String text) {
//        if (text == null) {
//            return null;
//        }
//
//        if (sJsonReadContext == null || sJsonReadContext.json() == null) {
//            return text;
//        }
//
//        Matcher matcher = Pattern.compile("#([^#]*)#").matcher(text);
//
//        while (matcher.find()) {
//
//            String pathWithToken = matcher.group(0);
//            String path = matcher.group(1);
//
//            try {
//                String value = String.valueOf(sJsonReadContext.read("$." + path));
//
//                if (value != null) {
//                    text = text.replaceAll(pathWithToken, value);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//                Log.w(TAG, "No value for " + path);
//            }
//        }
//
//        return text;
//    }

    /**
     * Replace the content detail strings map for lookup
     */
    public static void setContentDetail(ContentDetail contentDetail) {
        sContentMap = contentDetail.getContent();
    }

    public static boolean isContentDetailInMemory() {
        return sContentMap != null && !sContentMap.isEmpty();
    }

    public static void setSubscriptionInfo(SubscriptionInfo subscriptionInfo) {
        if (subscriptionInfo == null) {
            Log.w(TAG, "AccountInfo is null, invalidating ReadContext");
            sJsonReadContext = null;
            return;
        }

        sJsonReadContext = sJsonReader.parse(new Gson().toJson(subscriptionInfo));
    }

    public static void setUpgradeInfo(UpgradeInfo upgradeInfo) {
        if (upgradeInfo == null) {
            upgradeJsonReadContext = null;
        }
        upgradeJsonReadContext = jsonReaderUpgrade.parse(new Gson().toJson(upgradeInfo));
    }

    public static PrivacyPolicy getPrivacyPolicyJson(@StringRes int stringRes) {
        if (stringRes != 0) {
            if (sContentMap != null) {
                String key = sResIdToKey.get(stringRes);
                if (!TextUtils.isEmpty(key)) {

                    Object jsonObject = sContentMap.get(key);
                    if (jsonObject != null) {
                        try {
                            String jsonText = JSONValue.toJSONString(jsonObject);
                            return new Gson().fromJson(jsonText, PrivacyPolicy.class);
                        } catch (Exception e) {
                            Log.e(TAG, "Error deserializing PrivacyPolicy from ContentDetail", e);
                        }
                    }
                }
            }
        }

        // Should never get here...
        return null;
    }
}
