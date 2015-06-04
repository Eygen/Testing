package com.epam.zt.testing.model;

import java.util.UUID;

public class Answer extends Test {
    private Test test;
    private Question question;
    private String description;
    private boolean correct;

    public Answer() {
    }

    public Answer(UUID uuid) {
        super(uuid);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }
}
