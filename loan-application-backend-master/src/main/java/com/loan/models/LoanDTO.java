package com.loan.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.loan.entity.Customer;
import com.loan.entity.Loan;

public class LoanDTO {

	private Integer loanReqNo;

	private String fname;
	private String lname;

	@Min(value = 500, message = "minimum loanAmt required is 500")
	@Max(value = 12000, message = "maximum loanAmt cannot exceed 12000")

	@NotNull
	private double loanAmt;

	private Integer customerId;



	public LoanDTO() {

	}

	public Double getLoanAmt() {
		return loanAmt;
	}

	public void setLoanAmt(Double loanAmt) {
		this.loanAmt = loanAmt;
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

}
