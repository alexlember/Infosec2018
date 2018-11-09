package com.example.alexlember.hello.main.controler;

import android.app.Activity;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class MainController {

    public static final String MY_PREFS_NAME = "MyPrefsFile";
    public static final String PREF_IS_ONBOARDING_COMPLETED = "isOnboardingCompleted";
    public static final String PREF_HAS_AT_LEAST_ONE_VICTORY = "atLeastOneVictory";


    /**
     * Метод проверяет, были ли уже выполнены действия по
     * "регистрации в системе".
     * @return true - уже были выполнены, false - еще не были выполнены.
     */
    public static boolean isOnboardingCompleted(SharedPreferences prefs) {
        //return false;
        return prefs.getBoolean(PREF_IS_ONBOARDING_COMPLETED, false);
    }

    /**
     * Метод проверяет, были ли уже выполнены действия по
     * "регистрации в системе".
     * @return true - уже были выполнены, false - еще не были выполнены.
     */
    public static boolean hasAtLeastOneVictory(SharedPreferences prefs) {
        //return false;
        return prefs.getBoolean(PREF_HAS_AT_LEAST_ONE_VICTORY, false);
    }

    public static void saveToSharedPrefs(Activity activity, String prefKey, Object prefValue) {
        SharedPreferences.Editor editor = activity.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();

        if (prefValue != null && prefKey != null) {
            if (prefValue instanceof Boolean) {
                editor.putBoolean(prefKey, (Boolean) prefValue);
            }
            editor.apply();
        }
    }
}
