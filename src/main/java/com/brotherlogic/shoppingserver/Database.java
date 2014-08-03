package com.brotherlogic.shoppingserver;

import java.net.UnknownHostException;

import org.bson.types.ObjectId;

import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;

public class Database {
    public synchronized static Database getDatabase() {
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

    DB db;

    static Database database;

    private void connect() throws UnknownHostException {
        MongoClient client = new MongoClient();
        db = client.getDB("shopping_server");
    }

    public DBObject getSingle(String collection, DBObject query) {
        return db.getCollection(collection).findOne(query);
    }

    public DBCursor runQuery(String collection, DBObject query) {
        return db.getCollection(collection).find(query);
    }

    public ObjectId store(String collection, DBObject obj) {
        WriteResult res = db.getCollection(collection).insert(obj);
        return (ObjectId) res.getField("Id");
    }
}
