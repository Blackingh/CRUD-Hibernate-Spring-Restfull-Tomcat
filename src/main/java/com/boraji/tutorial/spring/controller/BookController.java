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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.boraji.tutorial.spring.model.Book;
import com.boraji.tutorial.spring.service.BookService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value="Book Controller")
public class BookController {

   @Autowired
   private BookService bookService;

   /*---Add new book---*/
   @ApiOperation(value="Guarda un libro")
   @PostMapping("/book")
   public ResponseEntity<?> saveBook(@RequestBody Book book) {
      long id = bookService.save(book);
      return ResponseEntity.ok().body("New Book has been saved with ID:" + id);
   }

   /*---Get a book by id---*/
   @ApiOperation(value="Obtiene un Libro por ID")
   @GetMapping("/book/{id}")
   public ResponseEntity<Book> getBook(@PathVariable("id") long id) {
      Book book = bookService.get(id);
      return ResponseEntity.ok().body(book);
   }

   /*---get all books---*/
   @ApiOperation(value="Obtiene una lista de libros")
   @GetMapping("/book")
   public ResponseEntity<List<Book>> listBook() {
      List<Book> books = bookService.list();
      return ResponseEntity.ok().body(books);
   }

   /*---Update a book by id---*/
   @ApiOperation(value="Actualiza un libro por ID")
   @PutMapping("/book/{id}")
   public ResponseEntity<?> updateBook(@PathVariable("id") long id, @RequestBody Book book) {
      bookService.update(id, book);
      return ResponseEntity.ok().body("Book has been updated successfully.");
   }

   /*---Delete a book by id---*/
   @ApiOperation(value="Elimina un libro por ID")
   @DeleteMapping("/book/{id}")
   public ResponseEntity<?> deleteBook(@PathVariable("id") long id) {
      bookService.delete(id);
      return ResponseEntity.ok().body("Book has been deleted successfully.");
   }
   
  
}