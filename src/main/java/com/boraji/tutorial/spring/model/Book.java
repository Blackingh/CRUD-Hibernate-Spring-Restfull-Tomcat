package com.boraji.tutorial.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity(name = "Book")
@ApiModel("Modelo Book")
public class Book {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @ApiModelProperty(value = "La ID del libro", notes ="No es obligatoria por que se genera automaticamente", required = false)
   private Long id;
   
   @Column(name="title")
   @ApiModelProperty(value = "El titulo del libro", notes ="Es obligatoria por que se necesita identificar el libro", required = true)
   private String title;
   
   @Column(name="author")
   @ApiModelProperty(value = "El nombre del autor", notes ="Es obligatoria por que se necesita identificar el autor", required = true)
   private String author;

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public String getAuthor() {
      return author;
   }

   public void setAuthor(String author) {
      this.author = author;
   }

}
