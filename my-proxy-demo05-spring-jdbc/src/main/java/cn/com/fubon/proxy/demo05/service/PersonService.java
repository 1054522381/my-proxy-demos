package cn.com.fubon.proxy.demo05.service;

import java.util.List;

import cn.com.fubon.proxy.demo05.domain.Person;

public interface PersonService {
	void save(Person person);

	List<Person> getPersons();

	Person getPerson(Integer id);

	void update(Person person);

	void delete(Integer id);
}
