package com.loan.services;

import java.util.List;

import com.loan.entity.Loan;
import com.loan.models.CustomerDTO;
import com.loan.models.LoanDTO;

public interface LoanService {

	public LoanDTO applyLoan(Loan loan);

 public List<Loan> getLoansByCustomerId(Integer customerId);
 
 public CustomerDTO getTotalLoansAmountforCustomerId(Integer customerId);

}
