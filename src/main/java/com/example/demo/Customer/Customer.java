package com.example.demo.Customer;

import java.util.Date;
import java.util.List;

import com.example.demo.Loan.Loan;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customer")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private Integer gender;
	private Date dob;
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
	@JsonIgnore
	@OneToMany(mappedBy = "customer")
	private List<Loan> loans;
}
