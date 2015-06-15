package com.epam.zt.testing.model;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Student extends User {
    private List<Test> tests;
    private Map<Subject, Mark> marks;
    private Group group;

    public Student() {
    }

    public Student(UUID uuid) {
        super(uuid);
    }

    public Map<Subject, Mark> getMarks() {
        return marks;
    }

    public void setMarks(Map<Subject, Mark> marks) {
        this.marks = marks;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstname=" + getFirstName() +
                ", lastname=" + getLastName() +
                ", group=" + group +
                '}';
    }
}
