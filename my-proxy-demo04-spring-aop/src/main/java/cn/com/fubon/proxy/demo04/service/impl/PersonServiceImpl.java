package cn.com.fubon.proxy.demo04.service.impl;

import org.springframework.stereotype.Service;

import cn.com.fubon.proxy.demo04.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {
	private String username;
	
	public PersonServiceImpl(){
	}
	
	public PersonServiceImpl(String username){
		this.username = username;
	}
	
	public String getUsername() {
		username = "lisi";
		return username;
	}

	@Override
	public String save(String name) {
		System.out.println("saved!"); 
//		int i = 2/0;
		return "saved!";
	}

}
