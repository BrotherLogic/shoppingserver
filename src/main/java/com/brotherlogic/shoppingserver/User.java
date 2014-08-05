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

    /**
     * @return The URL to the avatar of the user
     */
    public final String getAvatarURL() {
        return avatarURL;
    }

    /**
     * @return The ID of the list
     */
    public final int getId() {
        return id;
    }

    /**
     * @return The name of the user
     */
    public final String getName() {
        return name;
    }
}
