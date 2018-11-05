package com.example.alexlember.hello.quizz.controller;

import com.example.alexlember.hello.quizz.model.Question;
import com.example.alexlember.hello.quizz.model.QuizzFactory;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class QuizzController {

    private List<Question> questions;

    @Getter
    private int currentPosition;
    @Getter
    private int numberOfCorrectAnswers;

    @Setter
    private String selectedAnswer;

    private Question currentQuestion;

    public QuizzController() {
        currentPosition = 0;
        numberOfCorrectAnswers = 0;
        questions = QuizzFactory.initQuestions();
        currentQuestion = questions.get(0);
    }

    /**
     * Метод осуществляет действие подтверждения выбора вопроса.
     */
    public void makeAnswer() {

        if (currentQuestion.getProperAnswer().equalsIgnoreCase(selectedAnswer)) {
            numberOfCorrectAnswers++;
        }

        currentPosition++;
        if (!isFinish()) {
            currentQuestion = questions.get(currentPosition);
        }
    }

    public boolean isSuccess() {
        return isFinish() && ((questions.size()) == numberOfCorrectAnswers);
    }

    public int getCurrentProgress() {
        return (currentPosition * 100) / questions.size();
    }

    public boolean isFinish() {
        return questions.size() == currentPosition;
    }

    public List<String> getCurrentAnswerOptions() {
        return currentQuestion.getAnswerOptions();
    }

    public String getCurrentQuestion() {
        return currentQuestion.getQuestion();
    }

    public int getQuestionSize() {
        return questions.size();
    }
}
