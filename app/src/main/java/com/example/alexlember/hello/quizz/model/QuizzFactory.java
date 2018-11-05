package com.example.alexlember.hello.quizz.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class QuizzFactory {

    public static List<Question> initQuestions() {

        List<Question> questions = new ArrayList<>();

        try {
            // 1
            String question = "В чем смысл жизни?";
            List<String> answerOptions = Arrays.asList(
                    "Посадить дерево",
                    "Построить дом",
                    "Вырастить сына",
                    "Вырастить дочь");
            String properAnswer = "Посадить дерево";


            questions.add(new Question(question, answerOptions, properAnswer));


            // 2
            question = "Что важнее?";
            answerOptions = Arrays.asList(
                    "Семья",
                    "Любовь",
                    "Деньги",
                    "Власть");
            properAnswer = "Любовь";
            questions.add(new Question(question, answerOptions, properAnswer));

            // 3
            question = "Какая порода кошек?";
            answerOptions = Arrays.asList(
                    "Сиамская",
                    "Абиссинская",
                    "Сфинкс",
                    "Британская");
            properAnswer = "Сфинкс";
            questions.add(new Question(question, answerOptions, properAnswer));

        } catch (QuestionModelException e) {
            System.out.println("Не удалось построить модель вопросов: " + e);
        }

        Collections.shuffle(questions);

        return Collections.unmodifiableList(questions);
    }
}
