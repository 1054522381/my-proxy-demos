package cn.com.fubon.proxy.demo04;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import cn.com.fubon.proxy.demo04.service.PersonService;

public class SpringAOPTest {
	
	public static void main(String[] args) {
		ApplicationContext cxt = new AnnotationConfigApplicationContext("cn.com.fubon.proxy.demo04");
		PersonService personService = cxt.getBean(PersonService.class);
//		personService.getUsername();
		try{
			personService.save("zhangsan");
		} catch(Exception e){
			System.out.println("exception catched!");
		}
	}
}
