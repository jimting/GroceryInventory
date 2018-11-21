package groceryInventory;

public class GroceryInventory {
	public static String getGrocery(String userID) 
	{
		//給user拿到所有的資訊
		//先把資料結構預設出來
		//{"status":"404代表未登入/200代表已登入", "name":"使用者名字", "notification":[{"content":"通知內容", "status":"unread / read 有沒有看過此通知"},{...},...], "items":[{"ID":"商品編號", "name":"商品名稱", "content":"商品介紹", "quantity":"剩餘數量", "pic":"商品照片url"},{...},...]}
		String item = getItem();
		String not = getNotification();
		String result = "{\"status\":\"200\", \"name\":\"使用者名字\", \"notification\":"+not+", \"items\":"+item+"}";
		return result;
	}
	public static String getItem() 
	{
		//拿取所有商品資訊
		String result = "[{\"ID\":\"1\", \"name\":\"商品名稱1\", \"content\":\"商品介紹1\", \"quantity\":\"30\", \"pic\":\"商品照片url\"},{\"ID\":\"1\", \"name\":\"商品名稱2\", \"content\":\"商品介紹2\", \"quantity\":\"40\", \"pic\":\"商品照片url\"},{\"ID\":\"1\", \"name\":\"商品名稱2\", \"content\":\"商品介紹2\", \"quantity\":\"50\", \"pic\":\"商品照片url\"}]";
		return result;
	}
	public static String getNotification() 
	{
		//拿取所有通知
		String result = "[{\"content\":\"通知內容1\", \"status\":\"unread \"},{\"content\":\"通知內容2\", \"status\":\"unread \"},{\"content\":\"通知內容3\", \"status\":\"unread \"}]";
		return result;
	}
	

}
