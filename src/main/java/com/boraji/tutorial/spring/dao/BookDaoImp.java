package com.boraji.tutorial.spring.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.boraji.tutorial.spring.model.Book;

@Repository
public class BookDaoImp implements BookDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public Book save(Book book) {
      sessionFactory.getCurrentSession().save(book);
      return book;
   }

   @Override
   public Book get(long id) {
      return sessionFactory.getCurrentSession().get(Book.class, id);
   }

   @SuppressWarnings("unchecked")
   @Override
   public List<Book> list() {
      Session session = sessionFactory.getCurrentSession();
      List<Book> books = session.createQuery("from Book").list();
      return books;
   }

   @Override
   public void update(long id, Book book) {
      Session session = sessionFactory.getCurrentSession();
      Book book2 = session.byId(Book.class).load(id);
      book2.setTitle(book.getTitle());
      book2.setAuthor(book.getAuthor());
      session.flush();
   }

   @Override
   public void delete(long id) {
      Session session = sessionFactory.getCurrentSession();
      Book book = session.byId(Book.class).load(id);
      session.delete(book);
   }

}
