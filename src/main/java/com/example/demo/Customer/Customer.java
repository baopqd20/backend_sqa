package com.example.demo.Customer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "customer")
@Data
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String gender;
	private String dob;
	private String nationality;
	private String cardId;
	private String passport;
	private String taxcode;
	private String job;
	private String position;
	private String perAddress;
	private String curAddress;
	private String phone;
	private String email;
}
