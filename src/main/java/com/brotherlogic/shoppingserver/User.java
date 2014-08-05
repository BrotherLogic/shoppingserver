package com.brotherlogic.shoppingserver;

import com.mongodb.DBObject;

/**
 * A User within the system
 *
 * @author simon
 *
 */
public class User {
    private String name;
    private int id;
    private String avatarURL;

    /**
     * Constructor
     *
     * @param userObject
     *            The database object to build this user from
     */
    public User(final DBObject userObject) {
        this.name = (String) userObject.get("name");
        this.id = (Integer) userObject.get("id");
        this.avatarURL = (String) userObject.get("avatarURL");
    }
}
