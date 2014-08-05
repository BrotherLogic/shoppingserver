package com.brotherlogic.shoppingserver;

import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * Defines operations on Lists
 *
 * @author simon
 *
 */
@Path("list/{listid}")
public final class ShoppingList {

    /**
     * Gets the shopping lists for a particular user
     *
     * @param userID
     *            the ID of the user to retrieve for
     * @return A List of the ShoppingLists for this user
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public static List<ShoppingList> getLists(
            @PathParam("listid") final long userID) {

        List<ShoppingList> lists = new LinkedList<ShoppingList>();

        BasicDBObject query = new BasicDBObject();
        query.append("userid", userID);

        DBObject cursor = Database.getDatabase().getSingle("List", query);
        if (cursor != null) {
            BasicDBList list = (BasicDBList) cursor.get("lists");
            ShoppingList sList = new ShoppingList();
            for (Object obj : list) {
                BasicDBList innerList = (BasicDBList) obj;
                for (Object user : innerList)
                    sList.users.add(new User((BasicDBObject) user));
            }
            lists.add(sList);
        } else {
            // Create a default list and generate the id
            ShoppingList list = new ShoppingList();

            BasicDBObject obj = new BasicDBObject();
            BasicDBList list1 = new BasicDBList();
            list1.add(new BasicDBList());
            obj.put("lists", list1);
            obj.put("userid", userID);
            list.id = Database.getDatabase().store("List", obj);

            lists.add(list);
        }

        return lists;
    }

    private ObjectId id;

    private List<User> users = new LinkedList<User>();

    /**
     * Blocking Constructor
     */
    private ShoppingList() {

    }

    /**
     * @return The id of this list
     */
    public ObjectId getId() {
        return id;
    }

    /**
     * @return The Users for this list
     */
    public List<User> getUsers() {
        return users;
    }
}
