package com.example.websocketproject.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


// test candidate entity 

@Entity
public class Candidate {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	String name, email,number,senderId;
	
	

	public Candidate() {
	}



	public Candidate(Long id, String name, String email, String number, String senderId) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.number = number;
		this.senderId = senderId;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getNumber() {
		return number;
	}



	public void setNumber(String number) {
		this.number = number;
	}



	public String getSenderId() {
		return senderId;
	}



	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}
	
	

}
