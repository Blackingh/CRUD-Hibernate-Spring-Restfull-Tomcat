package com.boraji.tutorial.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity(name="Person")
@ApiModel("Modelo Persona")
public class Person {
	
	@Id
	@Column(name="id")
	@ApiModelProperty(value = "EL ID de la persona",notes="Es obligatoria por que la persona necesita un ID", required = true)
	private int identification;
	
	@Column(name="first_name")
	@ApiModelProperty(value = "EL nombre de la persona",notes="Es obligatoria por que la persona necesita un nombre", required = true)
	private String name;
	
	@Column(name="last_name")
	@ApiModelProperty(value = "El apellido de la persona",notes="Es obligatoria prque la persona necesita un apellido", required = true)
	private String lastName;
	
	@Column(name="age")
	@ApiModelProperty(value = "La edad de la persona",notes="Es obligatoria por que la persona necesita una edad", required = true)
	private int age;

	public int getIdentification() {
		return identification;
	}

	public void setIdentification(int identification) {
		this.identification = identification;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
}
