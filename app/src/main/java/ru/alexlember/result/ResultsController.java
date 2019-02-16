package ru.alexlember.result;

import android.util.Log;
import lombok.val;
import ru.alexlember.ResultsActivity;
import ru.alexlember.https.SendRequestTask;

public class ResultsController {

    /**
     * Отправка результатов на сервер
     * @param email
     * @param textToSend
     */
    public void sendResults(ResultsActivity activity, String email, String textToSend, int numberOfCorrectAnswers) {
        val request = new LogEntity();
        request.setText("Results for email: " + email +
        "\ntext to send: " + textToSend +
        "\nnumber of correct answers: " + numberOfCorrectAnswers);

        Log.i("results: ", request.toString());
        new SendRequestTask(activity, request).execute(); //TODO закомментить для запрета отправки данных на сервер
    }
}
