package com.example.alexlember.hello.onboarding.controller;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

public class SendMailTask extends AsyncTask<Void, String, Boolean> {

    private static final String SENDER_EMAIL = "test";
    private static final String SENDER_EMAIL_PASS = "pass";
    private static final String RECEIVER_EMAIL = "test@cnpo.ru";

    private ProgressDialog statusDialog;
    private Activity sendMailActivity;
    private Message message;

    public SendMailTask(Activity activity, Message message) {
        this.sendMailActivity = activity;
        this.message = message;

    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        boolean isSuccess = true;
        try {
            Log.i("SendMailTask", "About to instantiate GMail...");
            publishProgress("Отправка данных на сервер...");


            try {
                GMailSender sender = new GMailSender(SENDER_EMAIL, SENDER_EMAIL_PASS);
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
        statusDialog = new ProgressDialog(sendMailActivity);
        statusDialog.setMessage("Подготовка к отправке данных");
        statusDialog.setIndeterminate(false);
        statusDialog.setCancelable(false);
        statusDialog.show();
    }

}