package com.brotherlogic.shopping.atoms;

import com.brotherlogic.shopping.db.Key;

public class User {

	/** This is the twitter id */
	private long userId;

	/** Screen name of the user - may change as twitter updates */
	private String userName;

	/** URL that points to the user image */
	private String imageURL;

	@Key
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
}
