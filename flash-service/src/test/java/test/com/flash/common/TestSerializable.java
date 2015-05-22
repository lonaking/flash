package test.com.flash.common;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

import com.flash.base.dao.impl.CommonDaoImpl;
import com.flash.domain.Product;

public class TestSerializable {
	public static void main(String[] args) {
		
		Integer abc = 1;
		String string = abc.toString();
		System.out.println(string);
		TestABC<Product> abc2 = new TestABC<Product>();
		Class<? extends TestABC> class1 = abc2.getClass();
		System.out.println(class1);
		TypeVariable<?>[] typeParameters = class1.getTypeParameters();
		
		System.out.println(typeParameters[0]);
		ParameterizedType type = (ParameterizedType) class1.getGenericSuperclass();
		System.out.println(type);
	}
}
