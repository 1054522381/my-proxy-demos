package cn.com.fubon.proxy.demo02;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.apache.commons.lang3.StringUtils;

import cn.com.fubon.proxy.demo02.service.PersonService;

public class PersonServiceProxyFactory {
	private PersonService personService;
	public Object createPersonServiceProxy(final PersonService personService){
		this.personService = personService;
		return Proxy.newProxyInstance(
				personService.getClass().getClassLoader(),
				personService.getClass().getInterfaces(),
				new InvocationHandler() {
					
					@Override
					public Object invoke(Object proxy, Method method, Object[] args)
							throws Throwable {
						System.out.println("before proxy invoke");
						Object result = null;
						if(StringUtils.isNotBlank(personService.getUsername())){
							result = method.invoke(personService, args);
							System.out.println(result);
						}
						System.out.println("after proxy invoke");
						return result;
					}
				});
	}
	
}