package com.davidrivett.workouttracker.data;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * User Model for Realm DB
 */
public class User extends RealmObject
{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
