package GroceryInventory;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class GroceryInventory {
	public static String getGrocery(String userID) {
		try {  
            
			System.out.println("MongoDBConnect to database begin");
            //連線到MongoDB服務 如果是遠端連線可以替換“localhost”為伺服器所在IP地址
			
            //通過連線認證獲取MongoDB連線
            MongoClient mongoClient = MongoClients.create("mongodb://cinema:cinema@140.121.196.23:4118");
            
            //連線到資料庫(schema)
            MongoDatabase mongoDatabase = mongoClient.getDatabase("Grocery");
            System.out.println("MongoDBConnect to database successfully");

            //建立集合
//            mongoDatabase.createCollection("test");
//            System.out.println("集合建立成功");
//選擇集合	
            String result = "[";
            MongoCollection<Document> collection = mongoDatabase.getCollection("grocery");
            FindIterable<Document> fi = collection.find();
            MongoCursor<Document> cursor = fi.iterator();
            while(cursor.hasNext()) 
            {
            	result += cursor.next().toJson();
            	if(cursor.hasNext())
            		result += ",";
            }
            result += "]";
            System.out.println("Connect to database successfully");
            return result;
            
        } catch (Exception e) {  
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            return "{}";
        }
	}
}
