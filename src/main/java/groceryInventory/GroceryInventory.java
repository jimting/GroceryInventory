package groceryInventory;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.mongodb.DB;
import com.mongodb.MongoClient;

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
			MongoClient mongoClient = new MongoClient( "140.121.196.23" , 4118 );
			DB db = mongoClient.getDB("Grocery");
			boolean auth = db.authenticate("cinema", "cinema".toCharArray());
			List<String> dbs = mongoClient.getDatabaseNames();
			for(String item : dbs){
				System.out.println(item);
			}
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
	public static void main()
	{
		String result = getGrocery("1");
	}
}
