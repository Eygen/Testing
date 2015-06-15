package com.epam.zt.testing.model;

import java.util.List;
import java.util.UUID;

public class Test extends BaseEntity {
    private Subject subject;
    private Student student;
    private Tutor author;
    private List<Question> questions;
    private int questionAmount;
    private boolean active;
    private Mark mark;

    public Test() {
    }

    public Test(UUID uuid) {
        super(uuid);
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public int getQuestionAmount() {
        return questionAmount;
    }

    public void setQuestionAmount(int questionAmount) {
        this.questionAmount = questionAmount;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Tutor getAuthor() {
        return author;
    }

    public void setAuthor(Tutor author) {
        this.author = author;
    }

    public Mark getMark() {
        return mark;
    }

    public void setMark(Mark mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "Test{" +
                "subject=" + subject +
                '}';
    }
}
