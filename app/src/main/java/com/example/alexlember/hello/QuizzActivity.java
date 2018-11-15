package com.example.alexlember.hello;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.example.alexlember.hello.quizz.controller.QuizzController;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class QuizzActivity extends AppCompatActivity {

    TextView questionNumberLabel, questionText;
    RadioGroup answersRadioGroup;
    RadioButton radioButton, radioButton2, radioButton3, radioButton4;
    Button submitButton, resetButton;
    ProgressBar progressBar;
    ImageView backgroundImageView;

    QuizzController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz);

        questionNumberLabel = findViewById(R.id.questionNumberLabel);
        questionText = findViewById(R.id.questionText);

        answersRadioGroup = findViewById(R.id.answersRadioGroup);
        radioButton = findViewById(R.id.radioButton);
        radioButton2 = findViewById(R.id.radioButton2);
        radioButton3 = findViewById(R.id.radioButton3);
        radioButton4 = findViewById(R.id.radioButton4);

        submitButton = findViewById(R.id.submitButton);
        resetButton = findViewById(R.id.resetButton);
        progressBar = findViewById(R.id.progressBar);
        backgroundImageView = findViewById(R.id.backgroundImageView);

        init();

        answersRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                controller.setSelectedAnswer(getAnswer(checkedId));
                updateButtonEnabled();
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.makeAnswer();
                updateView();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init();
            }
        });


    }

    private void init() {
        Drawable background = backgroundImageView.getDrawable();
        background.setAlpha(70);

        controller = new QuizzController();
        updateView();
        updateButtonEnabled();
    }

    private void updateButtonEnabled() {
        boolean isValid = answersRadioGroup.getCheckedRadioButtonId() != -1;
        submitButton.setEnabled(isValid);
    }

    private void updateView() {

        progressBar.setProgress(controller.getCurrentProgress());

        if (!controller.isFinish()) {

            Log.i("QuizzActivity", "Test in progress. Question: "
                    + (controller.getCurrentPosition() + 1)
                    + "/" + controller.getQuestionSize()
                    + ". Number of correct answers: " + controller.getNumberOfCorrectAnswers());

            questionNumberLabel.setText(getString(R.string.questionNumberText, controller.getCurrentPosition() + 1));
            questionText.setText(controller.getCurrentQuestion());
            setAnswers();

        } else {

            Log.i("QuizzActivity","Finish test. Number of correct answers: "
                    + controller.getNumberOfCorrectAnswers()
                    + "/" + controller.getQuestionSize());

            Intent myIntent = new Intent(QuizzActivity.this, ResultsActivity.class);
            myIntent.putExtra("isSuccess", controller.isSuccess()); //Optional parameters
            QuizzActivity.this.startActivity(myIntent);
        }

    }

    /**
     * Метод проставляет варианты ответа в радиогруппу.
     */
    private void setAnswers() {
        answersRadioGroup.clearCheck();
        List<String> answers = controller.getCurrentAnswerOptions();
        radioButton.setText(answers.get(0));
        radioButton2.setText(answers.get(1));
        radioButton3.setText(answers.get(2));
        radioButton4.setText(answers.get(3));
    }

    /**
     * Метод извлекает ответа выбранной радиокнопке.
     * @param checkedId выбранный id кнопки.
     * @return значение, которое хранится в радиогруппе.
     */
    @Nullable
    private String getAnswer(int checkedId) {
        String answer = null;
        switch (checkedId) {
            case R.id.radioButton:
                answer = String.valueOf(radioButton.getText());
                break;
            case R.id.radioButton2:
                answer = String.valueOf(radioButton2.getText());
                break;
            case R.id.radioButton3:
                answer = String.valueOf(radioButton3.getText());
                break;
            case R.id.radioButton4:
                answer = String.valueOf(radioButton4.getText());
                break;
        }
        return answer;
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed()
    {
        // disable back button
    }
}
