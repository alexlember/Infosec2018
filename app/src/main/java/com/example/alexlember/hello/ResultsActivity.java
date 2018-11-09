package com.example.alexlember.hello;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.alexlember.hello.main.controler.MainController;

import java.util.Random;

import static com.example.alexlember.hello.main.controler.MainController.MY_PREFS_NAME;
import static com.example.alexlember.hello.main.controler.MainController.PREF_HAS_AT_LEAST_ONE_VICTORY;

public class ResultsActivity extends AppCompatActivity {

    TextView resultsText;
    Button resetButton;
    ImageView backgroundImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Intent intent = getIntent();
        boolean isSuccess = intent.getBooleanExtra("isSuccess", false);

        resultsText = findViewById(R.id.resultsText);
        resetButton = findViewById(R.id.resetButton);
        backgroundImageView = findViewById(R.id.backgroundImageView);

        Drawable background = backgroundImageView.getDrawable();
        background.setAlpha(70);

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(ResultsActivity.this, QuizzActivity.class);
                ResultsActivity.this.startActivity(myIntent);
            }
        });

        String text;
        if (!isSuccess) {
            text = "К сожалению, вы не прошли тест. Попробуйте еще.";
        } else {
            boolean hasAtLeaseOneVictory = MainController.hasAtLeastOneVictory(getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE));
            if (!hasAtLeaseOneVictory) {
                text = "Поздравляем, вы прошли тест! Покажите организаторам промокод с экрана телефона для получения подарка: " +  getRandomPromoCode();
                MainController.saveToSharedPrefs(ResultsActivity.this, PREF_HAS_AT_LEAST_ONE_VICTORY, true);
            } else {
                text = "Поздравляем, вы прошли тест! И уже даже не в первый раз? Сразу видно, что у вас очень сильные знания в области ИБ :)";
            }
        }
        resultsText.setText(text);
    }

    private int getRandomPromoCode() {
        Random r = new Random();
        return r.nextInt((6000 - 1000) + 1) + 1000;
    }
}
