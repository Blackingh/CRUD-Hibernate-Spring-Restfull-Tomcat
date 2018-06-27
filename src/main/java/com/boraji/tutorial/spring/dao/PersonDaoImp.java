package com.boraji.tutorial.spring.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.boraji.tutorial.spring.model.Person;


@Repository
public class PersonDaoImp implements PersonDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public long save(Person person) {
		sessionFactory.getCurrentSession().save(person);
		return person.getIdentification();
	}

	@Override
	public Person get(long id) {
		return sessionFactory.getCurrentSession().get(Person.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Person> list() {
		Session session = sessionFactory.getCurrentSession();
		List<Person> person = session.createQuery("from Person").list();
	    return person;
	}

	@Override
	public void update(long id, Person person) {
		Session session = sessionFactory.getCurrentSession();
	    Person person2 = session.byId(Person.class).load(id);
	    person2.setIdentification(person.getIdentification());
	    person2.setName(person.getName());
	    person2.setLastName(person.getLastName());
	    person.setAge(person.getAge());
	    session.flush();		
	}

	@Override
	public void delete(long id) {
		Session session = sessionFactory.getCurrentSession();
	    Person person = session.byId(Person.class).load(id);
	    session.delete(person);
	}

}
