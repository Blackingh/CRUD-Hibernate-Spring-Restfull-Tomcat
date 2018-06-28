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

@RestController
@Api(value="Person Controller")
public class PersonController {

   @Autowired
   private PersonService personService;
   
   /*---Add new Person---*/
   @ApiOperation(value="Guarda una persona")
   @PostMapping("/person")
   public ResponseEntity<?> savePerson(@RequestBody Person person) {
      long id = personService.save(person);
      return ResponseEntity.ok().body("New Person has been saved with ID:" + id);
   }

   /*---Get a book by id---*/
   @ApiOperation(value="Obtiene una persona por ID")
   @GetMapping("/person/{id}")
   public ResponseEntity<Person> getPerson(@PathVariable("id") long id) {
      Person person = personService.get(id);
      return ResponseEntity.ok().body(person);
   }

   /*---get all books---*/
   @ApiOperation(value="Obtiene una lista de personas")
   @GetMapping("/person")
   public ResponseEntity<List<Person>> listPerson() {
      List<Person> persons = personService.list();
      return ResponseEntity.ok().body(persons);
   }

   /*---Update a book by id---*/
   @ApiOperation(value="Actualiza una persona por ID")
   @PutMapping("/person/{id}")
   public ResponseEntity<?> updatePerson(@PathVariable("id") long id, @RequestBody Person person) {
      personService.update(id, person);
      return ResponseEntity.ok().body("Person has been updated successfully.");
   }

   /*---Delete a book by id---*/
   @ApiOperation(value="Elimina una persona por id")
   @DeleteMapping("/person/{id}")
   public ResponseEntity<?> deletePerson(@PathVariable("id") long id) {
      personService.delete(id);
      return ResponseEntity.ok().body("Person has been deleted successfully.");
   }
}