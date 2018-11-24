package ru.alexlember.quizz.model;

import lombok.Getter;
import lombok.NonNull;

import java.util.Collections;
import java.util.List;

/**
 * Класс, который является моделью вопроса.
 * Вся информация содержится тут.
 */
public class Question {

    @Getter
    private final String question;

    @Getter
    private final List<String> answerOptions;

    @Getter
    private final String properAnswer;

    Question(@NonNull String question, @NonNull List<String> answerOptions, @NonNull String properAnswer) throws QuestionModelException {

        System.out.println("Создание вопроса. question: " + question + " answerOptions: " + answerOptions + " properAnswer: " + properAnswer);

        this.question = question;
        if (answerOptions.size() != 4) {// TODO возможно вынести в конфиг.
            throw new QuestionModelException("Должно быть 4 варианта ответа ровно");
        }
        Collections.shuffle(answerOptions);
        this.answerOptions = Collections.unmodifiableList(answerOptions);

        if (!answerOptions.contains(properAnswer)) {
            throw new QuestionModelException("Среди ответов нет правильного");
        }
        this.properAnswer = properAnswer;

        System.out.println("Создание вопроса успешно завершено.");

    }

}
