package com.boraji.tutorial.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.boraji.tutorial.spring.model.Book;
import com.boraji.tutorial.spring.model.Rental;
import com.boraji.tutorial.spring.service.RentalService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@RestController
@Api(value="Rental Controller")
public class RentalController {

   @Autowired
   private RentalService rentalService;

   /*---Add new book---*/
   @CrossOrigin
   @PostMapping("/rental")
   @ApiOperation(value="Crea un alquiler", response = ResponseEntity.class, notes = "Retorna el alquiler añadido")
   public ResponseEntity<Rental> saveRental(@ApiParam(value = "Un objeto alquiler tipo Json", required = true) @RequestBody Rental rental) {
      Rental rentalReturn = rentalService.save(rental);
      return ResponseEntity.ok().body(rentalReturn);
   }

   /*---Get a book by id---*/
   @CrossOrigin
   @GetMapping("/rental/{id}")
   @ApiOperation(value="Busca un alquiler", response = Book.class, notes = "Retorna un alquiler por ID")
   public ResponseEntity<Rental> getRental(@ApiParam(value="El ID del alquiler a buscar",required = true) @PathVariable("id") int id) {
      Rental rental = rentalService.get(id);
      return ResponseEntity.ok().body(rental);
   }
   
   @CrossOrigin
   @GetMapping("/rental/person/{id}")
   public ResponseEntity<List<Rental>> listPersonId(@PathVariable("id")long id){
	   List<Rental> rentals = rentalService.getPersonId(id);
	   return ResponseEntity.ok().body(rentals);
   }

   /*---get all books---*/
   @CrossOrigin
   @GetMapping("/rental")
   @ApiOperation(value="Busca todos los alquileres", response = List.class , notes = "Retorna una lista de objetos Rental")
   public ResponseEntity<List<Rental>> listRental() {
      List<Rental> rentals = rentalService.list();
      return ResponseEntity.ok().body(rentals);
   }

   /*---Update a book by id---*/
   @CrossOrigin
   @PutMapping("/rental/{id}")
   @ApiOperation(value="Actualiza un alquiler por ID", response = ResponseEntity.class, notes="Restorna el alquiler actualizado")
   @ApiResponses({@ApiResponse(code = 500, message = "The rental does not exist")})
   public ResponseEntity<Rental> updateRental(@ApiParam(value="El ID del alquiler a actualizar",required= true) @PathVariable("id") int id, 
   			@ApiParam(value="Un objeto Rental tipo Json",required= true)	@RequestBody Rental rental) {
	   
	   rentalService.update(id, rental);
      return ResponseEntity.ok().body(rental);
   }

   /*---Delete a book by id---*/
   @CrossOrigin
   @DeleteMapping("/rental/{id}")
   @ApiOperation(value="Elimina un alquiler por ID", response = ResponseEntity.class, notes="Retorna una respuesta OK")
   @ApiResponses({@ApiResponse(code = 500, message = "The rental does not exist")})
   public ResponseEntity<?> deleteRental(@ApiParam(value="El ID del alquiler a eliminar",required= true)@PathVariable("id") int id){
	   rentalService.delete(id);
      return ResponseEntity.ok().body("Rental has been deleted successfully.");
   }
   
   @CrossOrigin
   @DeleteMapping("/rental/person/delete/{id}")
   public ResponseEntity<?> deleteBooksRental(@PathVariable("id")int id ,@RequestBody List<Book> bookList){
	   rentalService.deleteBooksPerson(id, bookList);
      return ResponseEntity.ok().body("Rental has been deleted successfully.");
   }
}