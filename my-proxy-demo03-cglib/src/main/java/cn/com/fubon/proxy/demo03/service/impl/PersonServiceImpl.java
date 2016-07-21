package cn.com.fubon.proxy.demo03.service.impl;

public class PersonServiceImpl /*implements PersonService*/ {

	private String username;
	
	public PersonServiceImpl(){
	}

	public PersonServiceImpl(String username){
		this.username = username;
	}
	
	public String getUsername(){
		return username;
	}
	
	//@Override
	public void save() {
		System.out.println("saved!");
	}

}
