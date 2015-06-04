package com.epam.zt.testing.model;

import java.util.List;
import java.util.UUID;

public class Subject extends BaseEntity {
    private String name;
    private Tutor tutor;
    private List<Group> groups;

    public Subject() {
    }

    public Subject(UUID uuid) {
        super(uuid);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }
}
