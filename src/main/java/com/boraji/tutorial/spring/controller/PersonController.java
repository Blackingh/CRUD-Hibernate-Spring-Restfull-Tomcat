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

import com.boraji.tutorial.spring.model.Person;
import com.boraji.tutorial.spring.service.PersonService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value="Person Controller")
public class PersonController {

   @Autowired
   private PersonService personService;
   
   /*---Add new Person---*/
   @PostMapping("/person")
   @ApiOperation(value="Crea una persona", response = ResponseEntity.class, notes="Retorna una respuesta OK")
   public ResponseEntity<?> savePerson(@ApiParam(value="Un objeto Person tipo Json",required= true) @RequestBody Person person) {
      long id = personService.save(person);
      return ResponseEntity.ok().body("New Person has been saved with ID:" + id);
   }

   /*---Get a Person by id---*/
   @GetMapping("/person/{id}")
   @ApiOperation(value="Busca una persona",response = Person.class, notes="Retorna una persona por ID")
   public ResponseEntity<Person> getPerson(@ApiParam(value="El ID de la persona a buscar",required= true) @PathVariable("id") long id) {
      Person person = personService.get(id);
      return ResponseEntity.ok().body(person);
   }

   /*---get all Person---*/
   @GetMapping("/person")
   @ApiOperation(value="Busca todas personas", response = List.class, notes ="Retorna una lista de objetos Person")
   public ResponseEntity<List<Person>> listPerson() {
      List<Person> persons = personService.list();
      return ResponseEntity.ok().body(persons);
   }

   /*---Update a Person by id---*/
   @PutMapping("/person/{id}")
   @ApiOperation(value ="Actualiza una persona", response = ResponseEntity.class, notes = "Restorna una respuesta OK")
   @ApiResponses({@ApiResponse(code = 500, message = "The book does not exist")})
   public ResponseEntity<?> updatePerson(@ApiParam(value = "El ID de la persona a actualizar", required = true) @PathVariable("id") long id, 
   			@ApiParam(value = "Un objeto Person tipo Json", required = true) @RequestBody Person person) {
	   
      personService.update(id, person);
      return ResponseEntity.ok().body("Person has been updated successfully.");
   }

   /*---Delete a Person by id---*/
   @DeleteMapping("/person/{id}")
   @ApiOperation(value="Elimina una persona", response = ResponseEntity.class, notes = "Retorna una respuesta OK")
   @ApiResponses({@ApiResponse(code = 500, message = "The book does not exist")})
   public ResponseEntity<?> deletePerson(@ApiParam(value = "El ID de la persona a eliminar", required= true) @PathVariable("id") long id) {
      personService.delete(id);
      return ResponseEntity.ok().body("Person has been deleted successfully.");
   }
}