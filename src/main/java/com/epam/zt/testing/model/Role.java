package com.epam.zt.testing.model;

import java.util.UUID;

public class Role extends BaseEntity {
    private String name;

    public Role() {
    }

    public Role(UUID uuid) {
        super(uuid);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + getId() + ", " +
                "uuid=" + getUuid() + ", " +
                "name='" + name + '\'' +
                '}';
    }
}
