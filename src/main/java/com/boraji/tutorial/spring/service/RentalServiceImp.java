package com.boraji.tutorial.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boraji.tutorial.spring.dao.RentalDao;
import com.boraji.tutorial.spring.model.Book;
import com.boraji.tutorial.spring.model.Rental;

@Service
@Transactional(readOnly = true)
public class RentalServiceImp implements RentalService {

   @Autowired
   private RentalDao rentalDao;

   @Transactional
   @Override
   public Rental save(Rental rental) {
      return rentalDao.save(rental);
   }

   @Override
   public Rental get(int id) {
      return rentalDao.get(id);
   }
   
   @Override
   public List<Rental> getPersonId(long id){
	   return rentalDao.getPersonId(id);
   }

   @Override
   public List<Rental> list() {
      return rentalDao.list();
   }

   @Transactional
   @Override
   public void update(int id, Rental rental) {
	   rentalDao.update(id, rental);
   }

   @Transactional
   @Override
   public void delete(int id) {
	   rentalDao.delete(id);
   }
   
   @Transactional
   @Override
   public void deleteBooksPerson(int id, List<Book> bookList) {
	   rentalDao.deleteBooksPerson(id, bookList);
   }
}
