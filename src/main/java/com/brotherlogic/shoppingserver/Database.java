package com.brotherlogic.shoppingserver;

import java.net.UnknownHostException;

import org.bson.types.ObjectId;

import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;

public class Database {

    /**
     * @return Gets the database for this project
     */
    public static synchronized Database getDatabase() {
        if (database == null) {
            database = new Database();
            try {
                database.connect();
            } catch (UnknownHostException e) {
                e.printStackTrace();
                return null;
            }
        }

        return database;
    }

    private DB db;

    private static Database database;

    /**
     * Connects to the database
     *
     * @throws UnknownHostException
     *             if we can't reach the database
     */
    private void connect() throws UnknownHostException {
        MongoClient client = new MongoClient();
        db = client.getDB("shopping_server");
    }

    /**
     * @param collection
     *            The collection to search
     * @param query
     *            The query to make
     * @return A single DBObject from the database
     */
    public final DBObject getSingle(final String collection,
            final DBObject query) {
        return db.getCollection(collection).findOne(query);
    }

    /**
     * @param collection
     *            The collection to search
     * @param query
     *            The query to make
     * @return A {@link DBCursor} to the search results
     */
    public final DBCursor runQuery(final String collection, final DBObject query) {
        return db.getCollection(collection).find(query);
    }

    /**
     * Stores an object in the database
     *
     * @param collection
     *            The collection to store the object in
     * @param obj
     *            The database object to store
     * @return The id of the stored object
     */
    public final ObjectId store(final String collection, final DBObject obj) {
        WriteResult res = db.getCollection(collection).insert(obj);
        return (ObjectId) res.getField("Id");
    }
}
