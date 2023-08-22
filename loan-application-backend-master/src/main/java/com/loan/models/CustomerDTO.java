package com.loan.models;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.loan.entity.Customer;

public class CustomerDTO {

	@NotNull
	private Integer customerId;
	private String fname;
	private String lname;
	private double totalLoanAmt;

	public double getTotalLoanAmt() {
		return totalLoanAmt;
	}

	public void setTotalLoanAmt(double totalLoanAmt) {
		this.totalLoanAmt = totalLoanAmt;
	}

	//private List<LoanDTO> loans = new ArrayList<LoanDTO>();

	public CustomerDTO() {

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

/*	public List<LoanDTO> getLoans() {
		return loans;
	}

	public void setLoans(List<LoanDTO> loans) {
		this.loans = loans;
	}

	// the method below will add Loan to LoansList
	// also serves the purpose to avoid cyclic references.
	public void addLoan(LoanDTO loan) {
		loan.setCustomer(this); // this will avoid nested cascade
		this.getLoans().add(loan);
	} */

}
