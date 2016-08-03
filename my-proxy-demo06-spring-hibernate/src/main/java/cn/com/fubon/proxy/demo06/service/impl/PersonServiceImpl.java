package cn.com.fubon.proxy.demo06.service.impl;

import java.util.List;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.com.fubon.proxy.demo06.domain.Person;
import cn.com.fubon.proxy.demo06.domain.PersonRowMapper;
import cn.com.fubon.proxy.demo06.service.PersonService;


/**
 * 事务传播属性:
 * REQUIRED(默认)：业务方法需要在一个事务中运行。如果方法运行时，已经处在一个事务中，那么加入到该事务，否则为自己创建一个新的事务。
 * NOT_SUPPORTED：声明方法不需要事务。如果方法没有关联到一个事务，容器不会为它开启事务。如果方法在一个事务中被调用，该事务会被挂起，在方法调用结束后，原先的事务便会恢复执行。
 * REQUIRESNEW：属性表明不管是否存在事务，业务方法总会为自己发起一个新的事务。如果方法已经运行在一个事务中，则原有事务会被挂起，新的事务会被创建，直到方法执行结束，新事务才算结束，原先的事务才会恢复执行。
 * MANDATORY：该属性指定业务方法只能在一个已经存在的事务中执行，业务方法不能发起自己的事务。如果业务方法在没有事务的环境下调用，容器就会抛出例外。
 * SUPPORTS：这一事务属性表明，如果业务方法在某个事务范围内被调用，则方法成为该事务的一部分。如果业务方法在事务范围外被调用，则方法在没有事务的环境下执行。
 * Never：指定业务方法绝对不能在事务范围内执行。如果业务方法在某个事务中执行，容器会抛出例外，只有业务方法没有关联到任何事务，才能正常执行。
 * NESTED：如果一个活动的事务存在，则运行在一个嵌套的事务中. 如果没有活动事务, 则按REQUIRED属性执行.它使用了一个单独的事务， 这个事务拥有多个可以回滚的保存点。内部事务的回滚不会对外部事务造成影响。它只对DataSourceTransactionManager事务管理器起效
 * 
 * 数据库系统提供的四种事务隔离级别:
 * Read Uncommited：读未提交数据(会出现脏读,不可重复读和幻读)。
 * Read Commited：读已提交数据(会出现不可重复读和幻读)
 * Repeatable Read：可重复读(会出现幻读)
 * Serializable：串行化

 * 脏读：在多个并发事务中,一个事务读取到另一事务未提交的更新新据。
 * 不可重复读：在多个并发事务中,在同一事务多次读取同一数据返回的结果有所不同。换句话说就是，后续读取可以读到另一事务已提交的更新数据。相反，“可重复读”在同一事务中多次读取数据时，能够保证所读数据一样，也就是，后续读取不能读到另一事务已提交的更新数据。
 * 幻读：一个事务读取到另一事务已提交的insert数据。
 * 
 * @author guo
*/

@Transactional // 指定当前bean中的所有方法要执行事务管理
public class PersonServiceImpl extends HibernateDaoSupport implements PersonService {
	private JdbcTemplate jdbcTemplate;
	
	public PersonServiceImpl(DataSource dataSource,SessionFactory sessionFactory){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.setHibernateTemplate(new HibernateTemplate(sessionFactory));
		// 构造方法中用的是默认的事务管理,@Transactional事务管理没有生效,每个dml为单独事务.
		//jdbcTemplate.update("insert into person(name) values(?)", "constructor1");
		//jdbcTemplate.update("insert into person(name) values(?)", "constructor11");
		//throw new RuntimeException(); // 事务没有回滚
	}
	
	@Transactional(rollbackFor=Exception.class/*,noRollbackFor=RuntimeException.class*/)
	@Override
	public void save(Person person) throws Exception {
		jdbcTemplate.update("insert into person(name) values(?)", person.getName());
		//throw new RuntimeException(); // 指定@Transactional后,抛出运行时异常,方法的事务回滚,如果是抛出checked异常,不会回滚事务.
		throw new Exception();
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	@Override
	public List<Person> getPersons() {
		List<Person> result = this.getHibernateTemplate().loadAll(Person.class);
		return result;
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	@Override
	public Person getPerson(Integer id) {
		Person p = this.getHibernateTemplate().get(Person.class, id);
		return p;
	}

	@Override
	public void update(Person person) {
		jdbcTemplate.update("update person set name=? where id=?", person.getName(),person.getId());
	}

	@Override
	public void delete(Integer id) {
		jdbcTemplate.update("delete from person where id=?", id);
	}

}

