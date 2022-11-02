package com.example.app.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="user_table")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private String name;
	private String surname;
	
	@NotNull
	private String dob;
	
	@NotNull
	private String joiningDate;
	
	private int pincode;
	
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
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("dd-mm-yyyy");
		Date formattedDate = format.parse(dob);
		java.sql.Date dateDB = new java.sql.Date(formattedDate.getTime());
		this.dob = dateDB.toString();
	}
	public String getJoiningDate() {
		return joiningDate;
	}
	public void setJoiningDate(String joiningDate) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("dd-mm-yyyy");
		Date formattedDate = format.parse(joiningDate);
		java.sql.Date dateDB = new java.sql.Date(formattedDate.getTime());
		this.joiningDate = dateDB.toString();
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	
	@Override
	public String toString() {
		return String.format("User [id=%s, name=%s, surname=%s, dob=%s, joiningDate=%s, pincode=%s]", id, name, surname,
				dob, joiningDate, pincode);
	}
	
}
