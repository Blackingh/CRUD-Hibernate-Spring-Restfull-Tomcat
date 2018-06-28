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
import com.boraji.tutorial.spring.service.BookService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value="Book Controller")
public class BookController {

   @Autowired
   private BookService bookService;

   /*---Add new book---*/
   @PostMapping("/book")
   @ApiOperation(value="Crea un libro", response = ResponseEntity.class, notes = "Retorna una respuesta OK")
   public ResponseEntity<?> saveBook(@ApiParam(value = "Un objeto Book tipo Json", required = true) @RequestBody Book book) {
      long id = bookService.save(book);
      return ResponseEntity.ok().body("New Book has been saved with ID:" + id);
   }

   /*---Get a book by id---*/
   @GetMapping("/book/{id}")
   @ApiOperation(value="Busca un Libro", response = Book.class, notes = "Retorna una libro por ID")
   public ResponseEntity<Book> getBook(@ApiParam(value="El ID del libro a buscar",required = true) @PathVariable("id") long id) {
      Book book = bookService.get(id);
      return ResponseEntity.ok().body(book);
   }

   /*---get all books---*/
   @GetMapping("/book")
   @ApiOperation(value="Busca todos los libros", response = List.class , notes = "Retorna una lista de objetos Book")
   public ResponseEntity<List<Book>> listBook() {
      List<Book> books = bookService.list();
      return ResponseEntity.ok().body(books);
   }

   /*---Update a book by id---*/
   @PutMapping("/book/{id}")
   @ApiOperation(value="Actualiza un libro por ID", response = ResponseEntity.class, notes="Restorna una respuesta OK")
   @ApiResponses({@ApiResponse(code = 500, message = "The book does not exist")})
   public ResponseEntity<?> updateBook(@ApiParam(value="El ID del libro a actualizar",required= true) @PathVariable("id")long id, 
   			@ApiParam(value="Un objeto Book tipo Json",required= true)	@RequestBody Book book) {
	   
      bookService.update(id, book);
      return ResponseEntity.ok().body("Book has been updated successfully.");
   }

   /*---Delete a book by id---*/
   @DeleteMapping("/book/{id}")
   @ApiOperation(value="Elimina un libro por ID", response = ResponseEntity.class, notes="Retorna una respuesta OK")
   @ApiResponses({@ApiResponse(code = 500, message = "The book does not exist")})
   public ResponseEntity<?> deleteBook(@ApiParam(value="El ID del libro a eliminar",required= true)@PathVariable("id")long id){
      bookService.delete(id);
      return ResponseEntity.ok().body("Book has been deleted successfully.");
   }
   
  
}