package com.brotherlogic.shoppingserver;

import com.mongodb.DBObject;

public class User {
    private String name;
    private int id;
    private String avatarURL;

    public User(DBObject userObject) {
        this.name = (String) userObject.get("name");
        this.id = (Integer) userObject.get("id");
        this.avatarURL = (String) userObject.get("avatarURL");
    }
}
