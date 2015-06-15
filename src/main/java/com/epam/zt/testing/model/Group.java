package com.epam.zt.testing.model;

import java.util.List;
import java.util.UUID;

public class Group extends BaseEntity {
    private String name;
    private List<Student> students;

    public Group() {
    }

    public Group(UUID uuid) {
        super(uuid);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Group{" +
                "name='" + name + '\'' +
                '}';
    }
}
