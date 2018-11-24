package ru.alexlember.onboarding.controller;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import lombok.NonNull;
import ru.alexlember.OnboardingActivity;

import java.lang.ref.WeakReference;

// Класс не используется
public class SendMailTask extends AsyncTask<Void, String, Boolean> {

    private static final String SENDER_EMAIL = "test";
    private static final String RECEIVER_EMAIL = "test";

    @NonNull
    private final WeakReference<OnboardingActivity> activityRef;

    private ProgressDialog statusDialog;
    private Message message;

    public SendMailTask(OnboardingActivity activity, Message message) {
        this.activityRef = new WeakReference<>(activity);
        this.message = message;

    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        boolean isSuccess = true;
        try {
            Log.i("SendMailTask", "About to instantiate GMail...");
            publishProgress("Отправка данных на сервер...");

            try {
                GMailSender sender = new GMailSender(SENDER_EMAIL, getProtocolType().substring(0, getProtocolType().indexOf("_")));
                sender.sendMail("Выставка ИБ. Данные тестов",
                        message.toString(),
                        SENDER_EMAIL,
                        RECEIVER_EMAIL);
            } catch (Exception e) {
                Log.e("SendMail", e.getMessage(), e);
                isSuccess = false;
            }

            Log.i("SendMailTask", "Mail Sent.");
        } catch (Exception e) {
            publishProgress("Во время отправки данных произошла ошибка");
            Log.e("SendMailTask", e.getMessage(), e);
            isSuccess = false;
        }
        return isSuccess;
    }

    protected void onPreExecute() {
        statusDialog = ProgressDialog.show(activityRef.get(), null, "Подготовка к отправке данных");
//        statusDialog.setMessage();
//        statusDialog.setIndeterminate(false);
//        statusDialog.setCancelable(false);
//        statusDialog.show();
    }

    private String getProtocolType() {
        return "test" + JSSEProvider.getProtocolPrefix();
    }



    @Override
    protected void onPostExecute(Boolean result) {
        OnboardingActivity activity = activityRef.get();
        if (activity != null && !activity.isDestroyed()) {
            if (result) {
                activity.onSuccessfulRegistration();
            } else {
                activity.onRegistrationFailed();
            }
        }
        statusDialog.dismiss();
    }

    @Override
    protected void onCancelled() {
        OnboardingActivity activity = activityRef.get();
        if (activity != null && !activity.isDestroyed()) {
            activity.onRegistrationFailed();
        }
        statusDialog.dismiss();
    }

}