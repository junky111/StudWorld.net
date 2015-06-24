
package net.studworld.system;


import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.ServerAddress;
import com.mongodb.util.JSON;
import java.net.UnknownHostException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.studworld.models.JSONObject;
import net.studworld.models.MongoDBObject;
import org.bson.types.ObjectId;


/**
 * Class MongoDBJDBC  functional for working with MongoDB.
 * @author Ярослав
 */
public class MongoDBJDBC {
    Mongo mongo;
    DB db;
    private ServerAddress[] list; 
    
    /**
     *
     */
    public MongoDBJDBC() {
        try {
            this.mongo = new Mongo(StudWorld.ServerConfig.mongoAccess.host, StudWorld.ServerConfig.mongoAccess.port);
            
            this.db = this.mongo.getDB(StudWorld.ServerConfig.mongoAccess.database);
        } catch (UnknownHostException ex) {
            Logger.getLogger(MongoDBJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * get the list of databases' names.
     * @return databases' names list.
     */
    public List<String> getDBList(){
        return this.mongo.getDatabaseNames();
    }
    
    /**
     * get the DB object.
     * @param db_name the name of DB
     * @return DB object.
     */
    public DB getDB(String db_name){
        return this.mongo.getDB(db_name);
    }
    
    /**
     * Check if the user is authorized.
     * @param myUserName 
     * @param myPassword
     * @return  boolean is authorised or not.
     */
    public boolean isAuth(String myUserName, char[] myPassword){
        return this.db.authenticate(myUserName, myPassword);
    }
    
    /**
     * Create the collection with name collection_name.
     * @param collection_name the name of collection.
     * @return DBCollection the created collection.
     */
    public DBCollection createCollection(String collection_name){
        return this.db.createCollection(collection_name, null);
    }
    
    /**
     * Create collecion with name collection_name and options 
     * @param collection_name
     * @param options
     * @return DBCollection the created collection.
     */
    public DBCollection createCollection(String collection_name, DBObject options){
        return this.db.createCollection(collection_name, options);
    }
    
    /**
     * Get the collection by name.
     * @param collection_name the collection name.
     * @return DBCollection the created collection
     */
    public DBCollection getCollection(String collection_name){
        return this.db.getCollection(collection_name);
    }
  
      /**
     * Create the document using the object obj.
     * @param obj the document.
     */
    public void createDocument(MongoDBObject obj){
        DBObject dbo = this.toDBObject(obj, false);
        this.getCollection(obj.getCollectionName()).insert(dbo);
        obj._id = dbo.get("_id").toString();
    }
    
    /**
     *
     * @param obj
     */
    public void updateDocument(MongoDBObject obj){
        /*BasicDBObject query = new BasicDBObject();
        query.put("id", obj.getId());
        this.getCollection(obj.getCollectionName()).findAndModify(query, (DBObject)obj);*/
    }
    
      
      /**
     * Delete the document obj.
     * @param obj the document.
     */
    public void deleteDocument(MongoDBObject obj){
        DBCollection c = this.getCollection(obj.getCollectionName());
        c.remove(this.toDBObject(obj, true));
    }
    
      
      /**
     * Show the document using the Class c and MongoDBObject obj 
     * @param <T>
     * @param c 
     * @param obj 
     * @param idOnly
     * @return instance of JSONObject's child
     */
    public <T extends JSONObject> T showDocument(Class<T> c, MongoDBObject obj, boolean idOnly){ 
        return (T)this.showDocument(c, obj.getCollectionName(), this.toDBObject(obj, idOnly));
    }
          /**
     * Show the document using the Class c, collection_name and query
     * @param <T>
     * @param c 
     * @param collectionName 
     * @param query
     * @return instance of JSONObject's child
     */
    public <T extends JSONObject> T showDocument(Class<T> c, String collectionName, DBObject query){ 
        return (T)this.toMongoDBObject(c, this.getCollection(collectionName).find(query).next());
    }
              /**
     * Show the documents using  collectionName and query
     * @param collectionName 
     * @param query
     * @return the instance of DBCursor class
     */
    public DBCursor showDocuments(String collectionName, BasicDBObject query){ 
        return this.getCollection(collectionName).find(query);
    }
   /**
     * Show the documents using  obj
     * @param obj
     * @return the instance of DBCursor class
     */
    public DBCursor showDocuments(MongoDBObject obj){ 
        return this.getCollection(obj.getCollectionName()).find(this.toDBObject(obj, false));
    }
    
    /**
     *
     * @param collectionName
     * @param query
     * @return
     */
    public boolean persist(String collectionName, BasicDBObject query){
        return this.getCollection(collectionName).find(query).length() != 0;
    }
    
    /**
     *
     * @param obj
     * @param idOnly
     * @return
     */
    public boolean persist(MongoDBObject obj, boolean idOnly){
        return this.getCollection(obj.getCollectionName()).find(this.toDBObject(obj, idOnly)).length() != 0;
    }
    /**
     * @return the list
     */
    public ServerAddress[] getList() {
        return this.list;
    }

    /**
     * @param list the list to set
     */
    public void setList(ServerAddress[] list) {
        this.list = list;
    }

    /**
     *
     * @param obj
     * @param idOnly
     * @return
     */
    public DBObject toDBObject(MongoDBObject obj, boolean idOnly) {
        DBObject s;
        if(idOnly) {
            s = (DBObject) JSON.parse("{}");
            s.put("_id", new ObjectId(obj._id));
        } else {
            s = (DBObject) JSON.parse(obj.toJson());
            s.removeField("id");
            s.removeField("_id");
        }
        return s;
    }

    /**
     *
     * @param <T>
     * @param c
     * @param obj
     * @return
     */
    public <T extends JSONObject> T toMongoDBObject(Class<T> c, DBObject obj) {
        obj.put("_id", obj.get("_id").toString());
        return (T) StudWorld.Gson.fromJson(obj.toString(), c);
    }
}
