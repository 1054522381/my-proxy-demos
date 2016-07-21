package cn.com.fubon.proxy.demo05.service.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import cn.com.fubon.proxy.demo05.domain.Person;
import cn.com.fubon.proxy.demo05.domain.PersonRowMapper;
import cn.com.fubon.proxy.demo05.service.PersonService;

@Transactional
public class PersonServiceImpl implements PersonService {

	private JdbcTemplate jdbcTemplate;
	
	public PersonServiceImpl(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public void save(Person person) {
		jdbcTemplate.update("insert into person(name) values(?)", person.getName());
		int i = 2/0; // 指定@Transactional后,抛出异常,方法的事务不会提交
	}

	@Override
	public List<Person> getPersons() {
		List<Person> result = jdbcTemplate.query("select * from person", new PersonRowMapper());
		return result;
	}

	@Override
	public Person getPerson(Integer id) {
		Person p = jdbcTemplate.queryForObject("select * from person where id=?",new PersonRowMapper() ,id);
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
