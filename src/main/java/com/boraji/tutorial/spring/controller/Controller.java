package com.boraji.tutorial.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.boraji.tutorial.spring.model.Book;
import com.boraji.tutorial.spring.model.Person;
import com.boraji.tutorial.spring.service.BookService;
import com.boraji.tutorial.spring.service.PersonService;

@RestController
public class Controller {

   @Autowired
   private BookService bookService;
   @Autowired
   private PersonService personService;

   /*---Add new book---*/
   @PostMapping("/book")
   public ResponseEntity<?> saveBook(@RequestBody Book book) {
      long id = bookService.save(book);
      return ResponseEntity.ok().body("New Book has been saved with ID:" + id);
   }

   /*---Get a book by id---*/
   @GetMapping("/book/{id}")
   public ResponseEntity<Book> getBook(@PathVariable("id") long id) {
      Book book = bookService.get(id);
      return ResponseEntity.ok().body(book);
   }

   /*---get all books---*/
   @GetMapping("/book")
   public ResponseEntity<List<Book>> listBook() {
      List<Book> books = bookService.list();
      return ResponseEntity.ok().body(books);
   }

   /*---Update a book by id---*/
   @PutMapping("/book/{id}")
   public ResponseEntity<?> updateBook(@PathVariable("id") long id, @RequestBody Book book) {
      bookService.update(id, book);
      return ResponseEntity.ok().body("Book has been updated successfully.");
   }

   /*---Delete a book by id---*/
   @DeleteMapping("/book/{id}")
   public ResponseEntity<?> deleteBook(@PathVariable("id") long id) {
      bookService.delete(id);
      return ResponseEntity.ok().body("Book has been deleted successfully.");
   }
   
   /*---Add new Person---*/
   @PostMapping("/person")
   public ResponseEntity<?> savePerson(@RequestBody Person person) {
      long id = personService.save(person);
      return ResponseEntity.ok().body("New Person has been saved with ID:" + id);
   }

   /*---Get a book by id---*/
   @GetMapping("/person/{id}")
   public ResponseEntity<Person> getPerson(@PathVariable("id") long id) {
      Person person = personService.get(id);
      return ResponseEntity.ok().body(person);
   }

   /*---get all books---*/
   @GetMapping("/person")
   public ResponseEntity<List<Person>> listPerson() {
      List<Person> persons = personService.list();
      return ResponseEntity.ok().body(persons);
   }

   /*---Update a book by id---*/
   @PutMapping("/person/{id}")
   public ResponseEntity<?> updatePerson(@PathVariable("id") long id, @RequestBody Person person) {
      personService.update(id, person);
      return ResponseEntity.ok().body("Person has been updated successfully.");
   }

   /*---Delete a book by id---*/
   @DeleteMapping("/person/{id}")
   public ResponseEntity<?> deletePerson(@PathVariable("id") long id) {
      personService.delete(id);
      return ResponseEntity.ok().body("Person has been deleted successfully.");
   }
}