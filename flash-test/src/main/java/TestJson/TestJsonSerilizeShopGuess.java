package TestJson;

import com.flash.base.web.dto.GuessShop;
import com.flash.commons.json.JsonHelper;

public class TestJsonSerilizeShopGuess {
	public static void main(String[] args) {
		GuessShop shop = new GuessShop();
		
		shop.setOpenTime("8:00");
		shop.setCloseTime("23:00");
		shop.setId(1);
		String jsonShop = JsonHelper.transObjToJsonString(shop);
		System.out.println(jsonShop);
		
		String json = "{\"id\":1,\"name\":null,\"address\":null,\"allday\":false,\"status\":0,\"open_time\":\"8:00\",\"close_time\":\"23:00\"}";
		GuessShop guessShop = JsonHelper.transJsonStringToObj(json, GuessShop.class);
		System.out.println(guessShop);
		
		System.out.println(System.currentTimeMillis());
		
	}
}
