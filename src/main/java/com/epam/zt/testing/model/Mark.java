package com.epam.zt.testing.model;

import java.util.UUID;

public class Mark extends BaseEntity {
    private Student student;
    private Test test;
    private int value;

    public Mark() {
    }

    public Mark(UUID uuid) {
        super(uuid);
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
