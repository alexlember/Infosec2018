package com.example.alexlember.hello.onboarding.controller;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import com.example.alexlember.hello.main.controler.MainController;
import lombok.Getter;

import java.util.concurrent.ExecutionException;

import static com.example.alexlember.hello.main.controler.MainController.PREF_IS_ONBOARDING_COMPLETED;

public class OnboardingController {

    @Getter
    private Message message = new Message();

    public void sendEmailWithDataMessage(Activity activity) {

        AsyncTask<Void, String, Boolean> task = new SendMailTask(activity, message).execute();
        try {
            boolean isSuccess = task.get();
            if (isSuccess) {
                MainController.saveToSharedPrefs(activity, PREF_IS_ONBOARDING_COMPLETED, true);
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
            Log.e("OnboardingController", e.getMessage());
        } catch (InterruptedException e) {
            Log.e("OnboardingController", e.getMessage());
        }

    }


}
