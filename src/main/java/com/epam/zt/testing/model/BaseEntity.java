package com.epam.zt.testing.model;

import java.util.UUID;

public abstract class BaseEntity {
    private final UUID uuid;
    private Integer id;
    private boolean deleted;

    public BaseEntity() {
        uuid = UUID.randomUUID();
    }

    public BaseEntity(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseEntity entity = (BaseEntity) o;

        return uuid.equals(entity.uuid);

    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }
}
