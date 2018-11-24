package ru.alexlember.onboarding.controller;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import lombok.val;
import ru.alexlember.OnboardingActivity;
import ru.alexlember.https.SendRequestTask;
import ru.alexlember.main.controler.MainController;
import lombok.Getter;
import ru.alexlember.result.LogEntity;


import static ru.alexlember.main.controler.MainController.*;

public class OnboardingController {

    @Getter
    private Message message = new Message();

    public void sendRequestWithDataMessage(OnboardingActivity activity) {

        // сохранение введенного email, чтобы оперировать с ним как с id пользователя.
        MainController.saveToSharedPrefs(activity, PREF_EMAIL, message.getEmail());

        val request = new LogEntity();
        request.setText("Onboarding completed: " + message);

        new SendRequestTask(activity, request).execute();

    }


}
