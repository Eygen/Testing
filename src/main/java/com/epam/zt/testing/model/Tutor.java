package com.epam.zt.testing.model;

import java.util.UUID;

public class Tutor extends User {
    private Subject subject;

    public Tutor() {
    }

    public Tutor(UUID uuid) {
        super(uuid);
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }


}
