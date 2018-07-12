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
      import org.springframework.web.bind.annotation.CrossOrigin;
@RestController
@Api(value="Book Controller")
public class BookController {

   @Autowired
   private BookService bookService;

   /*---Add new book---*/
   @CrossOrigin
   @PostMapping("/book")
   @ApiOperation(value="Crea un libro", response = ResponseEntity.class, notes = "Retorna el libro añadido")
   public ResponseEntity<Book> saveBook(@ApiParam(value = "Un objeto Book tipo Json", required = true) @RequestBody Book book) {
      Book bookReturn = bookService.save(book);
      return ResponseEntity.ok().body(bookReturn);
   }

   /*---Get a book by id---*/
   @CrossOrigin
   @GetMapping("/book/{id}")
   @ApiOperation(value="Busca un Libro", response = Book.class, notes = "Retorna una libro por ID")
   public ResponseEntity<Book> getBook(@ApiParam(value="El ID del libro a buscar",required = true) @PathVariable("id") long id) {
      Book book = bookService.get(id);
      return ResponseEntity.ok().body(book);
   }

   /*---get all books---*/
   @CrossOrigin
   @GetMapping("/book")
   @ApiOperation(value="Busca todos los libros", response = List.class , notes = "Retorna una lista de objetos Book")
   public ResponseEntity<List<Book>> listBook() {
      List<Book> books = bookService.list();
      return ResponseEntity.ok().body(books);
   }

   /*---Update a book by id---*/
   @CrossOrigin
   @PutMapping("/book/{id}")
   @ApiOperation(value="Actualiza un libro por ID", response = ResponseEntity.class, notes="Restorna el libro actualizado")
   @ApiResponses({@ApiResponse(code = 500, message = "The book does not exist")})
   public ResponseEntity<Book> updateBook(@ApiParam(value="El ID del libro a actualizar",required= true) @PathVariable("id")long id, 
   			@ApiParam(value="Un objeto Book tipo Json",required= true)	@RequestBody Book book) {
	   
      bookService.update(id, book);
      return ResponseEntity.ok().body(book);
   }

   /*---Delete a book by id---*/
   @CrossOrigin
   @DeleteMapping("/book/{id}")
   @ApiOperation(value="Elimina un libro por ID", response = ResponseEntity.class, notes="Retorna una respuesta OK")
   @ApiResponses({@ApiResponse(code = 500, message = "The book does not exist")})
   public ResponseEntity<?> deleteBook(@ApiParam(value="El ID del libro a eliminar",required= true)@PathVariable("id")long id){
      bookService.delete(id);
      return ResponseEntity.ok().body("Book has been deleted successfully.");
   }
   
  
}