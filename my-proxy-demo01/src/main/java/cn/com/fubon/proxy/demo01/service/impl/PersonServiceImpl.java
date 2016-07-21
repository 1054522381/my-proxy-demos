package cn.com.fubon.proxy.demo01.service.impl;

import cn.com.fubon.proxy.demo01.service.PersonService;

public class PersonServiceImpl implements PersonService {

	@Override
	public void save() {
		System.out.println("saved!");
	}

}
