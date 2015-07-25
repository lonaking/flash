package test.com.flash.common;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.TypeVariable;

import com.flash.baseproduct.domain.BaseProduct;

public class TestSerializable {
	public static void main(String[] args) {
		
		Integer abc = 1;
		String string = abc.toString();
		System.out.println(string);
		TestABC<BaseProduct> abc2 = new TestABC<BaseProduct>();
		Class<? extends TestABC> class1 = abc2.getClass();
		System.out.println(class1);
		TypeVariable<?>[] typeParameters = class1.getTypeParameters();
		
		System.out.println(typeParameters[0]);
		ParameterizedType type = (ParameterizedType) class1.getGenericSuperclass();
		System.out.println(type);
	}
}
