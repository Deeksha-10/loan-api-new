package com.loan.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.loan.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	Customer save(Customer customerdetail);

//	@Query(value="select c from Customer c where  c.customerId=?1 group by c.customerId =?1")
	//Customer findTotalLoansAmountforCustomerId(Integer customerId);
	
}
