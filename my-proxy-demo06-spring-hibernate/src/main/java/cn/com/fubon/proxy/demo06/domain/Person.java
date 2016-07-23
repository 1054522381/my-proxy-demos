package cn.com.fubon.proxy.demo06.domain;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cacheable(true)
// 为什么要用这个并发访问策略才可以呢?
/*
 * 缓存的方式有四种，分别为：
 * CacheConcurrencyStrategy.NONE
 * CacheConcurrencyStrategy.READ_ONLY，只读模式，在此模式下，如果对数据进行更新操作，会有异常；
 * CacheConcurrencyStrategy.READ_WRITE，读写模式在更新缓存的时候会把缓存里面的数据换成一个锁，其它事务如果去取相应的缓存数据，发现被锁了，直接就去数据库查询；
 * CacheConcurrencyStrategy.NONSTRICT_READ_WRITE，不严格的读写模式则不会的缓存数据加锁；
 * CacheConcurrencyStrategy.TRANSACTIONAL，事务模式指缓存支持事务，当事务回滚时，缓存也能回滚，只支持JTA环境。
 */
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;
	
	public Person(){
	}

	public Person(String name){
		this.name = name;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + "]";
	}
	
}
