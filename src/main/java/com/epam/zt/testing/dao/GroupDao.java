package com.epam.zt.testing.dao;

import com.epam.zt.testing.model.Group;

public interface GroupDao extends Dao<Group> {
    Group findByName(String groupName);
}
