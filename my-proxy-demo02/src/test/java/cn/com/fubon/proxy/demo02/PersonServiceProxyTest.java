package cn.com.fubon.proxy.demo02;

import cn.com.fubon.proxy.demo02.service.PersonService;
import cn.com.fubon.proxy.demo02.service.impl.PersonServiceImpl;

public class PersonServiceProxyTest {
	
	public static void main(String[] args) {
		PersonServiceProxyFactory factory = new PersonServiceProxyFactory();
		PersonService target = new PersonServiceImpl();
		PersonService proxy1 = (PersonService)factory.createPersonServiceProxy(target);
		proxy1.save();
		
		System.out.println("----------------");
		target = new PersonServiceImpl("zhangsan");
		PersonService proxy2 = (PersonService)factory.createPersonServiceProxy(target);
		proxy2.save();
		
	}
}
