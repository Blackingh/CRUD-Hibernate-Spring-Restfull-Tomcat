package com.boraji.tutorial.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boraji.tutorial.spring.dao.PersonDao;
import com.boraji.tutorial.spring.model.Person;

@Service
@Transactional(readOnly = true)
public class PersonServiceImp implements PersonService{

	@Autowired
	private PersonDao personDao;
	
	@Override
	public long save(Person person) {
		return personDao.save(person);
	}

	@Override
	public Person get(long id) {
		return personDao.get(id);
	}

	@Override
	public List<Person> list() {
		return personDao.list();
	}

	@Transactional
	@Override
	public void update(long id, Person person) {
		personDao.update(id, person);		
	}
	
	@Transactional
	@Override
	public void delete(long id) {
		personDao.delete(id);		
	}

}
