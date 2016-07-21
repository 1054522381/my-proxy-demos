package cn.com.fubon.proxy.demo03;

import cn.com.fubon.proxy.demo03.service.impl.PersonServiceImpl;

public class PersonServiceProxyTest {
	public static void main(String[] args) {
		PersonServiceProxyFactory factory = new PersonServiceProxyFactory();
		PersonServiceImpl target = new PersonServiceImpl("zhangsan");
		PersonServiceImpl proxy = (PersonServiceImpl)factory.createPersonServiceProxy(target);
		proxy.save();
	}
}
