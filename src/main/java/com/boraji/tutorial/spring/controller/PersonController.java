package com.boraji.tutorial.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.boraji.tutorial.spring.model.Person;
import com.boraji.tutorial.spring.service.PersonService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@Api(value="Person Controller")
public class PersonController {

   @Autowired
   private PersonService personService;
   
   /*---Add new Person---*/
   @CrossOrigin
   @PostMapping("/person")
   @ApiOperation(value="Crea una persona", response = ResponseEntity.class, notes="Retorna la person añadida")
   public ResponseEntity<?> savePerson(@ApiParam(value="Un objeto Person tipo Json",required= true) @RequestBody Person person) {
      Person personRetur = personService.save(person);
      return ResponseEntity.ok().body(personRetur);
   }

   /*---Get a Person by id---*/
   @CrossOrigin
   @GetMapping("/person/{id}")
   @ApiOperation(value="Busca una persona",response = Person.class, notes="Retorna una persona por ID")
   public ResponseEntity<Person> getPerson(@ApiParam(value="El ID de la persona a buscar",required= true) @PathVariable("id") long id) {
      Person person = personService.get(id);
      return ResponseEntity.ok().body(person);
   }

   /*---get all Person---*/
   @CrossOrigin
   @GetMapping("/person")
   @ApiOperation(value="Busca todas personas", response = List.class, notes ="Retorna una lista de objetos Person")
   public ResponseEntity<List<Person>> listPerson() {
      List<Person> persons = personService.list();
      return ResponseEntity.ok().body(persons);
   }

   /*---Update a Person by id---*/
   @CrossOrigin
   @PutMapping("/person/{id}")
   @ApiOperation(value ="Actualiza una persona", response = ResponseEntity.class, notes = "Restorna la persona actualizada")
   @ApiResponses({@ApiResponse(code = 500, message = "The book does not exist")})
   public ResponseEntity<Person> updatePerson(@ApiParam(value = "El ID de la persona a actualizar", required = true) @PathVariable("id") long id, 
   			@ApiParam(value = "Un objeto Person tipo Json", required = true) @RequestBody Person person) {
	   
      personService.update(id, person);
      return ResponseEntity.ok().body(person);
   }

   /*---Delete a Person by id---*/
   @CrossOrigin
   @DeleteMapping("/person/{id}")
   @ApiOperation(value="Elimina una persona", response = ResponseEntity.class, notes = "Retorna una respuesta OK")
   @ApiResponses({@ApiResponse(code = 500, message = "The book does not exist")})
   public ResponseEntity<?> deletePerson(@ApiParam(value = "El ID de la persona a eliminar", required= true) @PathVariable("id") long id) {
      personService.delete(id);
      return ResponseEntity.ok().body("Person has been deleted successfully.");
   }
}