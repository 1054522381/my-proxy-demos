package cn.com.fubon.proxy.demo02.service.impl;

import cn.com.fubon.proxy.demo02.service.PersonService;

public class PersonServiceImpl implements PersonService {
	private String username;
	
	public PersonServiceImpl(){
	}
	
	public PersonServiceImpl(String username){
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}

	@Override
	public String save() {
		return "saved!";
	}

}
