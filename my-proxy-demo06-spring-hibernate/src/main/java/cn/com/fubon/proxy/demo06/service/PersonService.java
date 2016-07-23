package cn.com.fubon.proxy.demo06.service;

import java.util.List;

import cn.com.fubon.proxy.demo06.domain.Person;


public interface PersonService {
	void save(Person person) throws Exception;

	List<Person> getPersons();

	Person getPerson(Integer id);

	void update(Person person);

	void delete(Integer id);
}