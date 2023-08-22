package com.loan.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "customer_id")
	private Integer customerId;
	
	@Column(name= "f_name")
	private String fname;
	@Column(name= "l_name")
	private String lname;
	
	@Column(name= "total_loan_amt")
	private double totalLoanAmt;

	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Loan> loans = new ArrayList<Loan>();

	public Customer() {

	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	
	public List<Loan> getLoans() {
		return loans;
	}

	public void setLoans(List<Loan> loans) {
		this.loans = loans;
	}

	// the method below will add Loan to LoansList
	// also serves the purpose to avoid cyclic references.
	public void addLoan(Loan loan) {
		loan.setCustomer(this); // this will avoid nested cascade
		this.getLoans().add(loan);
	}
	
	public double getTotalLoanAmt() {
		return totalLoanAmt;
	}

	public void setTotalLoanAmt(double totalLoanAmt) {
		this.totalLoanAmt = totalLoanAmt;
	}

}
