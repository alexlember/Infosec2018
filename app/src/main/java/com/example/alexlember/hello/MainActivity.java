package com.example.alexlember.hello;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.example.alexlember.hello.main.controler.MainController;

public class MainActivity extends AppCompatActivity {

    public static final String MY_PREFS_NAME = "MyPrefsFile";
    public static final String PREF_IS_ONBOARDING_COMPLETED = "isOnboardingCompleted";

    Button startButton;

    MainController controller = new MainController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.startButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.startActivity(createProperIntent());
            }
        });

    }

    private Intent createProperIntent() {
        Class<?> clazz;
        if (controller.isOnboardingCompleted(getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE))) {
            clazz = QuizzActivity.class;
        } else {
            clazz = OnboardingActivity.class;
        }

        return new Intent(MainActivity.this, clazz);
    }

}