package com.example.alexlember.hello.quizz.model;

/**
 * Исключение создания модели вопроса.
 */
public class QuestionModelException extends Exception {
    public QuestionModelException(String message) {
        super(message);
    }
}
