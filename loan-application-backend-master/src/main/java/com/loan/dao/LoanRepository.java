package com.loan.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.loan.entity.Loan;

@Repository

public interface LoanRepository extends JpaRepository<Loan, Integer> {

	List<Loan> findAllByCustomerId(Integer customerId);

	//@Query(value = "select customer_Id, SUM(loan_Amt) from Loan  where  customer_Id=?1 group by customer_Id =?1")
	//Loan findTotalLoansAmountforCustomerId(Integer customerId);

}
