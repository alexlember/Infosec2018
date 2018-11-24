package ru.alexlember.https;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import lombok.NonNull;
import lombok.val;
import org.json.JSONObject;
import ru.alexlember.MainActivity;
import ru.alexlember.OnboardingActivity;
import ru.alexlember.result.LogEntity;


import java.lang.ref.WeakReference;
import static ru.alexlember.https.HttpUtils.sendPostRequest;

/**
 * Класс для отправки https команд.
 */
public class SendRequestTask extends AsyncTask<String, String, Boolean> {

    @NonNull
    private final WeakReference<AppCompatActivity> activityRef;
    private final LogEntity entityToSend;
    private ProgressDialog statusDialog;

    public SendRequestTask(AppCompatActivity activity, LogEntity entityToSend) {
        this.activityRef = new WeakReference<>(activity);
        this.entityToSend = entityToSend;
    }

    protected Boolean doInBackground(String... arg0) {

        try {
            publishProgress("Отправка данных на сервер...");

            JSONObject postDataParams = new JSONObject();
            postDataParams.put("text", entityToSend.getText());

            val url = MainActivity.getUrl();

            Log.i("http post request: ", String.valueOf(entityToSend)
            + " to url: " + url);

            JSONObject response = sendPostRequest(url, postDataParams);

            Log.i("response: ", String.valueOf(response));

        } catch (Exception e) {
            publishProgress("Во время отправки данных произошла ошибка");
            Log.e("SendRequestTask", "Exception: " + e.getMessage());
            return false;
        }

        return true;
    }

    protected void onPreExecute() {
        statusDialog = ProgressDialog.show(activityRef.get(), null, "Отправка данных на сервер");
    }

    @Override
    protected void onPostExecute(Boolean result) {
        AppCompatActivity activity = activityRef.get();
        if (activity instanceof OnboardingActivity) {
            if (!activity.isDestroyed()) {
                if (result) {
                    ((OnboardingActivity)activity).onSuccessfulRegistration();
                } else {
                    ((OnboardingActivity)activity).onRegistrationFailed();
                }
            }
        }
        statusDialog.cancel();
    }

    @Override
    protected void onCancelled() {
        AppCompatActivity activity = activityRef.get();
        if (activity instanceof OnboardingActivity) {
            if (!activity.isDestroyed()) {
                ((OnboardingActivity)activity).onRegistrationFailed();
            }
        }
        statusDialog.cancel();
    }

}