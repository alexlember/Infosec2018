package com.example.alexlember.hello.onboarding.controller;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import lombok.Getter;

import java.util.concurrent.ExecutionException;

import static android.content.Context.MODE_PRIVATE;
import static com.example.alexlember.hello.MainActivity.MY_PREFS_NAME;
import static com.example.alexlember.hello.MainActivity.PREF_IS_ONBOARDING_COMPLETED;

public class OnboardingController {

    @Getter
    private Message message = new Message();

    public void sendEmailWithDataMessage(Activity activity) {

        AsyncTask<Void, String, Boolean> task = new SendMailTask(activity, message).execute();
        try {
            boolean isSuccess = task.get();
            if (isSuccess) {
                saveToSharedPrefs(activity);
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
            Log.e("OnboardingController", e.getMessage());
        } catch (InterruptedException e) {
            Log.e("OnboardingController", e.getMessage());
        }

    }

    private void saveToSharedPrefs(Activity activity) {
        SharedPreferences.Editor editor = activity.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putBoolean(PREF_IS_ONBOARDING_COMPLETED, true);
        editor.apply();
    }
}
