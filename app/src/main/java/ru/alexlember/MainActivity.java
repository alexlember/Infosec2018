package ru.alexlember;


import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import lombok.Getter;
import lombok.val;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.Nullable;
import ru.alexlember.hello.R;
import ru.alexlember.https.HttpUtils;
import ru.alexlember.main.controler.MainController;

import java.net.URL;

import static ru.alexlember.main.controler.MainController.MY_PREFS_NAME;

public class MainActivity extends AppCompatActivity {

    Button startButton;

    MainController controller = new MainController();

    @Getter
    private static URL url;

    @Getter
    private static OkHttpClient httpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        val resources = getResources(); // TODO закомментить для запрета отправки запроса
        url = buildHttpPath(resources);
        httpClient = buildHttpClient(resources);

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

    /**
     * Метод генирирует корректный URL до сервера.
     * @param res ресурсы приложения
     * @return путь URL
     */
    @Nullable
    private URL buildHttpPath(final Resources res) {
        return HttpUtils.buildHttpPath(res);

    }

    @Nullable
    private OkHttpClient buildHttpClient(final Resources res) {
        return HttpUtils.buildHttpClient(res);
    }
}