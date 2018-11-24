package ru.alexlember;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import ru.alexlember.hello.R;
import ru.alexlember.main.controler.MainController;
import ru.alexlember.result.ResultsController;

import java.util.Random;

import static ru.alexlember.main.controler.MainController.MY_PREFS_NAME;
import static ru.alexlember.main.controler.MainController.PREF_HAS_AT_LEAST_ONE_VICTORY;

public class ResultsActivity extends AppCompatActivity {

    private static final int CORRECT_QUESTIONS_TO_WIN = 10;

    TextView resultsText;
    Button resetButton;
    ImageView backgroundImageView;

    ResultsController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Intent intent = getIntent();
        boolean allCorrect = intent.getBooleanExtra("isSuccess", false);
        int numberOfCorrectAnswers = intent.getIntExtra("result", 0);

        boolean isWinner = numberOfCorrectAnswers >= CORRECT_QUESTIONS_TO_WIN;

        resultsText = findViewById(R.id.resultsText);
        resetButton = findViewById(R.id.resetButton);
        backgroundImageView = findViewById(R.id.backgroundImageView);

        Drawable background = backgroundImageView.getDrawable();
        background.setAlpha(70);

        controller = new ResultsController();

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(ResultsActivity.this, QuizzActivity.class);
                ResultsActivity.this.startActivity(myIntent);
            }
        });

        String text;
        if (!isWinner) {
            text = "К сожалению, вы не прошли тест. Попробуйте еще.";
        } else {
            boolean hasAtLeaseOneVictory = MainController.hasAtLeastOneVictory(getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE));
            if (!hasAtLeaseOneVictory) {
                //int promoCode = getRandomPromoCode();

                text = "";
                if (allCorrect) {
                    text = "Превосходно! Ни одной ошибки! ";
                }

                text += "Поздравляем, вы прошли тест!";
                MainController.saveToSharedPrefs(ResultsActivity.this, PREF_HAS_AT_LEAST_ONE_VICTORY, true);
            } else {
                text = "Поздравляем, вы прошли тест! И уже даже не в первый раз? Сразу видно, что у вас очень сильные знания в области ИБ :) Так держать!";
            }
        }

        text += "\n" + getString(R.string.correctQuestionNumber, numberOfCorrectAnswers);

        String email = MainController.getEmailProp(getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE));

        controller.sendResults(ResultsActivity.this, email, text, numberOfCorrectAnswers);
        resultsText.setText(text);
    }

    private int getRandomPromoCode() {
        Random r = new Random();
        return r.nextInt((6000 - 1000) + 1) + 1000;
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed()
    {
        Intent myIntent = new Intent(ResultsActivity.this, MainActivity.class);
        ResultsActivity.this.startActivity(myIntent);
    }
}
