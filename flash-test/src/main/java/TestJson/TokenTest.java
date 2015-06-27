package TestJson;

import java.util.ArrayList;
import java.util.List;

import com.flash.commons.json.JsonHelper;
import com.flash.ucenter.domain.Privilege;
import com.flash.ucenter.domain.User;
import com.flash.ucenter.privilege.token.Token;



public class TokenTest {
	public static void main(String[] args) {
		User user = new User();
		user.setAddr("addr");
		Privilege privilege = new Privilege();
		privilege.setSign("sdfa");
		
		List<Privilege> privileges = new ArrayList<Privilege>();
		privileges.add(privilege);
		Token token = new Token("lsadjflads", user, null);
		System.out.println(token);
		
		String jsonString = JsonHelper.transObjToJsonString(token);
		System.out.println(jsonString);
		
		
	}
}
