package groceryInventory;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.mongodb.MongoCredential;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class GroceryInventory {
	public static String getGrocery(String userID) 
	{
		//給user拿到所有的資訊
		//先把資料結構預設出來
		//{"status":"404代表未登入/200代表已登入", "name":"使用者名字", "notification":[{"content":"通知內容", "status":"unread / read 有沒有看過此通知"},{...},...], "items":[{"ID":"商品編號", "name":"商品名稱", "content":"商品介紹", "quantity":"剩餘數量", "pic":"商品照片url"},{...},...]}
		//String item = getItem();
		String not = getNotification();
		//String result = "{\"status\":\"200\", \"name\":\"使用者名字\", \"notification\":"+not+", \"items\":"+item+"}";
		try {  
            
			System.out.println("MongoDBConnect to database begin");
            //連線到MongoDB服務 如果是遠端連線可以替換“localhost”為伺服器所在IP地址
			
            //MongoCredential.createScramSha1Credential()三個引數分別為 使用者名稱 資料庫名稱 密碼
            MongoCredential credential = MongoCredential.createScramSha1Credential("cinema", "Movies", "cinema".toCharArray());
            List<MongoCredential> credentials = new ArrayList<MongoCredential>();
            credentials.add(credential);

            MongoClientSettings settings = MongoClientSettings.builder()
                    .credential(credential)
                    .applyToSslSettings(builder -> builder.enabled(true))
                    .applyToClusterSettings(builder -> 
                        builder.hosts(Arrays.asList(new ServerAddress("140.121.196.23", 4118))))
                    .build();

            
            //通過連線認證獲取MongoDB連線
            MongoClient mongoClient = MongoClients.create(settings);
            
            //連線到資料庫(schema)
            MongoDatabase mongoDatabase = mongoClient.getDatabase("Movies");
            System.out.println("MongoDBConnect to database successfully");

            //建立集合
//            mongoDatabase.createCollection("test");
//            System.out.println("集合建立成功");
//選擇集合
            MongoCollection collection = mongoDatabase.getCollection("Movie");
			
			
			
            System.out.println("Connect to database successfully");
            return "Connect to database successfully:\n" + collection.find(Filters.text("A Land Imaged"));
        } catch (Exception e) {  
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            return "Connect to database falily: "+e;
        }
		
		return "hello";
	}
	public static String getItem() 
	{
		
		
		//拿取所有商品資訊
		String result = "[{\"ID\":\"1\", \"name\":\"商品名稱1\", \"content\":\"商品介紹1\", \"quantity\":\"30\", \"pic\":\"商品照片url\"},{\"ID\":\"1\", \"name\":\"商品名稱2\", \"content\":\"商品介紹2\", \"quantity\":\"40\", \"pic\":\"商品照片url\"},{\"ID\":\"1\", \"name\":\"商品名稱2\", \"content\":\"商品介紹2\", \"quantity\":\"50\", \"pic\":\"商品照片url\"}]";
		return result;
	}
	public static String getNotification() 
	{
		try {//拿取所有通知
			String toiletUrl = "http://140.121.197.134:4091/getNotification?userID=1";
			Connection con = Jsoup.connect(toiletUrl).timeout(10000);
			Connection.Response resp;
		
			resp = con.execute();
		
			Document doc = null;
			if (resp.statusCode() == 200) {
				doc = con.get();
			}
			String result = doc.select("body").html().toString();
			return result;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "[]";
	}
}
