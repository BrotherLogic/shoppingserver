package com.brotherlogic.shopping.atoms;

import com.mongodb.DB;

public class DatabaseFactory {

	private DB database;

	public <X> Database<X> getDatabase(Class<X> clz) {
		return new MongoCollectionBridge<>(clz, database);
	}

}
