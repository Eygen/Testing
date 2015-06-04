package com.epam.zt.testing.model;

import java.util.List;
import java.util.UUID;

public class Question extends Test {
    private String description;
    private Test test;
    private List<Answer> answers;

    public Question() {
    }

    public Question(UUID uuid) {
        super(uuid);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
