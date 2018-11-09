package com.example.alexlember.hello.main.controler;

import android.content.SharedPreferences;

import static com.example.alexlember.hello.MainActivity.PREF_IS_ONBOARDING_COMPLETED;

public class MainController {

    /**
     * Метод проверяет, были ли уже выполнены действия по
     * "регистрации в системе".
     * @return true - уже были выполнены, false - еще не были выполнены.
     */
    public boolean isOnboardingCompleted(SharedPreferences prefs) {
        //return false;
        return prefs.getBoolean(PREF_IS_ONBOARDING_COMPLETED, false);
    }
}
