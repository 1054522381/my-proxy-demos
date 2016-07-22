package cn.com.fubon.proxy.demo05;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.com.fubon.proxy.demo05.domain.Person;
import cn.com.fubon.proxy.demo05.service.PersonService;

public class SpringJdbcTest {
	private static PersonService personService;
	private static ApplicationContext cxt;
	@BeforeClass
	public static void beforeClass (){
		cxt = new ClassPathXmlApplicationContext("beans.xml");
		personService = cxt.getBean(PersonService.class);
	}
	
	@Test
	public void testSave() throws Exception{
		personService.save(new Person("lisi"));
	}
	
	@Test
	public void testUpdate(){
		Person person = new Person("wangwu");
		person.setId(2);
		personService.update(person);
	}
	
	@Test
	public void getPersons(){
		List<Person> persons = personService.getPersons();
		for(Person person : persons){
			System.out.println(person);
		}
	}
	
	@Test
	public void getPerson(){
		Person p = personService.getPerson(1);
		System.out.println(p);
	}
	
	@Test
	public void testDelete(){
		personService.delete(2);
	}
}
