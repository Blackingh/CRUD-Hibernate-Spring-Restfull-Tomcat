package com.boraji.tutorial.spring.dao;

import java.util.List;

import com.boraji.tutorial.spring.model.Book;
import com.boraji.tutorial.spring.model.Rental;

public interface RentalDao {

   Rental save(Rental rental);

   Rental get(int id);
   
   List<Rental> getPersonId(long id);

   List<Rental> list();

   void update(int id, Rental rental);

   void delete(int id);
   
   void deleteBooksPerson(int id, List<Book> bookList);

}
