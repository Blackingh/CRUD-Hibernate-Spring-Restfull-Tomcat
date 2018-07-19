package com.boraji.tutorial.spring.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.boraji.tutorial.spring.model.Book;
import com.boraji.tutorial.spring.model.Rental;

@Repository
public class RentalDaoImp implements RentalDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public Rental save(Rental rental) {
      sessionFactory.getCurrentSession().save(rental);
      return rental;
   }

   @Override
   public Rental get(int id) {
      return sessionFactory.getCurrentSession().get(Rental.class, id);
   }
   
   @SuppressWarnings("unchecked")
   @Override
   public List<Rental> getPersonId(long id) {
	   Session session = sessionFactory.getCurrentSession();
	   List<Rental> rentals = session.createQuery("from Rental where person_id= :uid").setParameter("uid", id).list();
	   return rentals;
   }

   @SuppressWarnings("unchecked")
   @Override
   public List<Rental> list() {
      Session session = sessionFactory.getCurrentSession();
      List<Rental> rentals = session.createQuery("from Rental").list();
      return rentals;
   }

   @Override
   public void update(int id, Rental rental) {
      Session session = sessionFactory.getCurrentSession();
      Rental rental2 = session.byId(Rental.class).load(id);
      rental2.setBooks(rental.getBooks());
      rental2.setPerson(rental.getPerson());
      rental2.setDetail(rental.getDetail());
      rental2.setDateReturn(rental.getDateReturn());
      session.flush();
   }

   @Override
   public void delete(int id) {
      Session session = sessionFactory.getCurrentSession();
      Rental rental = session.byId(Rental.class).load(id);
      session.delete(rental);
   }
   
   @Override
   public void deleteBooksPerson(int id, List<Book> bookList) {
	   Session session = sessionFactory.getCurrentSession();
	   Rental rental = session.byId(Rental.class).load(id);
	   List<Book> bookListRemoved = new ArrayList<>();
	   for(Book book : bookList) {
		   bookListRemoved = rental.getBooks()
				   .stream()
				   .filter(item -> item.getId() != book.getId())
				   .collect(Collectors.toList());
		   rental.getBooks().clear();
		   rental.getBooks().addAll(bookListRemoved);
		   			
	   }
	   session.save(rental);
   }
}
