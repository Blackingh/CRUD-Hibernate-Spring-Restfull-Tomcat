package com.boraji.tutorial.spring.dao;

import java.util.List;

import com.boraji.tutorial.spring.model.Person;


public interface PersonDao {

	   Person save(Person person);

	   Person get(long id);

	   List<Person> list();

	   void update(long id, Person person);

	   void delete(long id);
	
}
