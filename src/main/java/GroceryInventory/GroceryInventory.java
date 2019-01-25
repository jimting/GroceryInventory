package GroceryInventory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.types.ObjectId;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;

public class GroceryInventory {
	public static String getGrocery() {
		try {  
            
			System.out.println("MongoDBConnect to database begin");
            //連線到MongoDB服務 如果是遠端連線可以替換“localhost”為伺服器所在IP地址
			
            //通過連線認證獲取MongoDB連線
            MongoClient mongoClient = MongoClients.create("mongodb://cinema:cinema@140.121.196.23:4117");
            
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
	public static String getGroceryByID(String ID) {
		try {  
            
			System.out.println("MongoDBConnect to database begin");
            //連線到MongoDB服務 如果是遠端連線可以替換“localhost”為伺服器所在IP地址
			
            //通過連線認證獲取MongoDB連線
            MongoClient mongoClient = MongoClients.create("mongodb://cinema:cinema@140.121.196.23:4117");
            
            //連線到資料庫(schema)
            MongoDatabase mongoDatabase = mongoClient.getDatabase("Grocery");
            System.out.println("MongoDBConnect to database successfully");

            //建立集合
//            mongoDatabase.createCollection("test");
//            System.out.println("集合建立成功");
//選擇集合	
            String result = "[";
            MongoCollection<Document> collection = mongoDatabase.getCollection("grocery");
            BasicDBObject whereQuery = new BasicDBObject();
        	whereQuery.put("_id", new ObjectId(ID));
            FindIterable<Document> fi = collection.find(whereQuery);
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
	public static String getNotification(String ID) 
	{
		String result = "";
		//這邊利用Jsoup爬蟲 直接拿到Notification的資料
		try {
			URL url = new URL("http://140.121.196.23:4102/getNotification?userID="+ID);
			org.jsoup.nodes.Document xmlDoc =  Jsoup.parse(url, 3000); //使用Jsoup jar 去解析網頁
			result = xmlDoc.select("body").get(0).text();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return result;
	}
	
	public static String orderingGrocery(String ID, String quantity) 
	{
		String result = "";
		//這邊利用Jsoup爬蟲 直接拿到Notification的資料
		try {
			URL url = new URL("http://140.121.196.23:4105/newGroceryOrdering?groceryID="+ID+"&quantity="+quantity);
			org.jsoup.nodes.Document xmlDoc =  Jsoup.parse(url, 3000); //使用Jsoup jar 去解析網頁
			result = xmlDoc.select("body").get(0).text();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return result;
	}
	
	public static String getGroceryFromOrderList(String userID) 
	{
		String result = "[";
		String groceryData = "";
		//這邊利用Jsoup爬蟲 直接拿到已經購買的Grocery資料
		try {
			URL url = new URL("http://140.121.196.23:4105/getGroceryByOrderList?userID="+userID);
			org.jsoup.nodes.Document xmlDoc =  Jsoup.parse(url, 3000); //使用Jsoup jar 去解析網頁
			groceryData = xmlDoc.select("body").get(0).text();
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		//這邊要處理拿到的資料
		JSONArray groceryList = new JSONArray(groceryData);
		for(int i = 0; i < groceryList.length();i++) 
		{
			JSONObject groceryItem = groceryList.getJSONObject(i);
			String groceryID = groceryItem.getString("ObjectID");
			String quantity = groceryItem.getString("Quantity");
			String groceryTemp="";
			try {
				URL url = new URL("http://140.121.196.23:4103/getGroceryByID?ID="+groceryID);
				org.jsoup.nodes.Document xmlDoc =  Jsoup.parse(url, 3000); //使用Jsoup jar 去解析網頁
				groceryTemp = xmlDoc.select("body").get(0).text();
				
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			JSONArray groceryTempData = new JSONArray(groceryTemp);
			String name = groceryTempData.getJSONObject(0).getString("name");
			String img_url = groceryTempData.getJSONObject(0).getString("img_url");
			String price = groceryTempData.getJSONObject(0).getString("price");
			
			result += "{\"name\":\""+name+"\",\"quantity\":\""+quantity+"\",\"price\":\""+price+"\",\"img_url\":\""+img_url+"\"}";
			if(i != groceryList.length()-1)
				result += ",";
		}
		result += "]";
		return result;
	}
	public static void main() 
	{
		System.out.print(getGroceryFromOrderList("1"));
	}
}
