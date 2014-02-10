package com.brotherlogic.shopping.atoms;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;

public class MongoCollectionBridge<X> implements Database<X> {

	private final Class<X> underlyingClass;
	private final DB database;

	public MongoCollectionBridge(Class<X> underlyingClass, DB database) {
		this.underlyingClass = underlyingClass;
		this.database = database;
	}

	@Override
	public void save(X o) {

		BasicDBObject basicObject = new BasicDBObject();

		for (String attribute : getAttributes()) {
			basicObject.append(attribute, getAttribute(o, attribute));
		}

		getCollection().apply(basicObject);
	}

	private DBCollection getCollection() {
		return database.getCollection(underlyingClass.getSimpleName());
	}

	private Object getAttribute(X obj, String attribute) {
		try {
			Method m = underlyingClass.getMethod("get" + attribute,
					new Class[0]);
			Object ret = m.invoke(obj, new Object[0]);
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	private Collection<String> getAttributes() {
		List<String> attributes = new LinkedList<>();
		for (Method m : underlyingClass.getDeclaredMethods()) {
			if (m.getName().startsWith("get")) {
				String attribute = m.getName().substring(3);
				attributes.add(attribute);
			}
		}

		return attributes;
	}

	@Override
	public X load(Object index) {
		// TODO Auto-generated method stub
		return null;
	}

}
