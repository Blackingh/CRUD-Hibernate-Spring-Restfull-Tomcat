package com.boraji.tutorial.spring.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity(name="Rental")
public class Rental {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_rental")
	private int idRental;
	
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private Person person;
	
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.EAGER)
	private List<Book> book;
	
	
	@Column(name="detail")
	private String detail;
	
	@Column(name="date_return")
	private Date dateReturn;
	
	public Date getDateReturn() {
		return dateReturn;
	}

	public void setDateReturn(Date dateReturn) {
		this.dateReturn = dateReturn;
	}

	public int getIdRental() {
		return idRental;
	}

	public void setIdRental(int idRental) {
		this.idRental = idRental;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public List<Book> getBooks() {
		return book;
	}

	public void setBooks(List<Book> book) {
		this.book = book;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	

}
