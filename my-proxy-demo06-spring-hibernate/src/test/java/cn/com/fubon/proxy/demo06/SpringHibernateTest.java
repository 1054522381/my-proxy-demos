package cn.com.fubon.proxy.demo06;

import java.util.List;

import org.hibernate.CacheMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.QueryHints;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.com.fubon.proxy.demo06.domain.Person;
import cn.com.fubon.proxy.demo06.service.PersonService;

public class SpringHibernateTest {
	private static PersonService personService;
	private static ApplicationContext cxt;
	private static SessionFactory sessionFactory;
	private static Session session;
	
	@BeforeClass
	public static void beforeClass (){
		cxt = new ClassPathXmlApplicationContext("beans.xml");
		personService = cxt.getBean(PersonService.class);
		sessionFactory = cxt.getBean(SessionFactory.class);
		session = sessionFactory.openSession();
	}
	
	@Test
	public void getPersonHibernate() throws Exception{
		Person p = personService.getPerson(1);
		System.out.println(p);
	}
	
	/**
	 * 查询缓存,只产生一条select语句
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void getPersonsHibernateQueryCache() {
		Query query = session.createQuery("from Person");
		query.setCacheable(true);
		List<Person> persons = query.list();
		for(Person person : persons){
			System.out.println(person);
		}
		System.out.println("======请关闭数据库服务=====");
		try {
			Thread.sleep(15*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		query = session.createQuery("from Person");
		query.setCacheable(true);
		persons = query.list();
		for(Person person : persons){
			System.out.println(person);
		}
	}
	
	/**
	 * 一级缓存,session级别的缓存
	 */
	@Test
	public void getPersonsHibernateSessionCache() {
		Person p1 = session.get(Person.class, 1);
		System.out.println("----只有一条select语句------");
		Person p2 = session.get(Person.class, 1);
		System.out.println("两次获取的Person是否是同一个对象:" + (p1 == p2));
	}

	/**
	 * 二级缓存,应用程序级别的缓存,也是sessionFactory级别缓存,不同session的缓存
	 * 为什么二级缓存没有生效??? 为什么在实体上配置CacheConcurrencyStrategy.NONSTRICT_READ_WRITE策略就可以??
	 */
	@Test
	public void getPersonsHibernateSessionFactoryCache() {
		Session session1 = sessionFactory.openSession();
		Session session2 = sessionFactory.openSession();
		System.out.println("两个session是否是同一个对象:" + (session1 == session2));
		
		Person p1 = session1.get(Person.class, 1);
		System.out.println("-----------");
		Person p2 = session2.get(Person.class, 1);
		System.out.println("两次获取的Person是否是同一个对象:" + (p1 == p2));
	}
}
