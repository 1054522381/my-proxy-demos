package cn.com.fubon.proxy.demo01;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import cn.com.fubon.proxy.demo01.service.PersonService;
import cn.com.fubon.proxy.demo01.service.impl.PersonServiceImpl;

public class PersonServiceProxyTest {
	public static void main(String[] args) {
		PersonService target = new PersonServiceImpl();
		InvocationHandler handler = new PersonServiceProxy(target);
		PersonService proxy = (PersonService)Proxy.newProxyInstance(
				target.getClass().getClassLoader(), 
				target.getClass().getInterfaces(),
				handler);
		proxy.save();
		System.out.println("------------------");
		for(Class inter : target.getClass().getInterfaces()){
			System.out.println(inter.getName());
			
		}
	}
}
