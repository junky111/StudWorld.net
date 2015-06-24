
package net.studworld.models;

/**
 * Model Blog with necessary fields. The instance of this class is able to convert to JSON .
 * @extends MongoDBObject
 * @author Ярослав Кузнецов
 */
public class Blog extends MongoDBObject  {

    /**
     * @return the collection_name
     */
    public String getCollectionName() {
        return "blogs";
    }

}
