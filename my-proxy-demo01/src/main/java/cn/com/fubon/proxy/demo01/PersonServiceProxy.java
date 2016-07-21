package cn.com.fubon.proxy.demo01;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import cn.com.fubon.proxy.demo01.service.PersonService;

public class PersonServiceProxy implements InvocationHandler{
	private PersonService personService;
	public PersonServiceProxy(PersonService personService){
		this.personService = personService;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("before proxy invoke");
		Object result = method.invoke(personService, args);
		System.out.println("after proxy invoke");
		return result;
	}
	
	
}