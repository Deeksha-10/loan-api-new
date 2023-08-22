package com.loan.test.controller;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.loan.controllers.LoanController;
import com.loan.entity.Loan;
import com.loan.models.CustomerDTO;
import com.loan.models.LoanDTO;
import com.loan.services.impl.LoanServiceImpl;

@RunWith(MockitoJUnitRunner.class)
//@WebMvcTest(value=LoanController.class )
public class LoanControllerTest {

	@Mock
	private LoanServiceImpl loanServiceImpl;

	@InjectMocks
	private LoanController loanController;

	@Test
	public void testApplyLoan() throws Exception {

		Loan mockloanRequest = new Loan();
		mockloanRequest.setCustomerId(1);
		mockloanRequest.setLoanAmt(6000.0);

		LoanDTO mockloanResponse = new LoanDTO();
		mockloanResponse.setCustomerId(1);
		mockloanResponse.setLoanAmt(6000.0);

		LoanDTO mockloanResponse1 = new LoanDTO();
		mockloanResponse.setCustomerId(1);
		mockloanResponse.setLoanAmt(4000.0);

		List<LoanDTO> loans = new ArrayList<>();
		loans.add(mockloanResponse1);
		loans.add(mockloanResponse1);

		Mockito.when(loanServiceImpl.applyLoan(mockloanRequest)).thenReturn(mockloanResponse);
		ResponseEntity<LoanDTO> loan = loanController.applyLoan(mockloanRequest);
		assertNotNull(loan);

	}

	@Test
	public void testGetLoansByCustomerId() throws Exception {

		Loan mockloanRequest = new Loan();
		mockloanRequest.setCustomerId(1);
		mockloanRequest.setLoanAmt(6000.0);

		LoanDTO mockloanResponse = new LoanDTO();
		mockloanResponse.setCustomerId(1);
		mockloanResponse.setLoanAmt(6000.0);

		LoanDTO mockloanResponsedto = new LoanDTO();
		mockloanResponse.setCustomerId(1);
		mockloanResponse.setLoanAmt(6000.0);

		Loan mockloanResponse1 = new Loan();
		mockloanResponse.setCustomerId(1);
		mockloanResponse.setLoanAmt(4000.0);

		List<LoanDTO> loandto = new ArrayList<>();
		loandto.add(mockloanResponsedto);

		List<Loan> loans = new ArrayList<>();
		loans.add(mockloanResponse1);

		Mockito.when(loanServiceImpl.getLoansByCustomerId(1)).thenReturn(loans);
		ResponseEntity<List<Loan>> loanRes = loanController.getLoansByCustomerId(1);

		assertNotNull(loanRes);

	}

	@Test
	public void getTotalLoansAmountforCustomerIdTest() throws Exception {

		Loan mockloanRequest = new Loan();
		mockloanRequest.setCustomerId(1);
		mockloanRequest.setLoanAmt(6000.0);

		LoanDTO mockloanResponse = new LoanDTO();
		mockloanResponse.setCustomerId(1);
		mockloanResponse.setLoanAmt(6000.0);

		LoanDTO mockloanResponsedto = new LoanDTO();
		mockloanResponse.setCustomerId(1);
		mockloanResponse.setLoanAmt(6000.0);

		Loan mockloanResponse1 = new Loan();
		mockloanResponse.setCustomerId(1);
		mockloanResponse.setLoanAmt(4000.0);

		List<LoanDTO> loandto = new ArrayList<>();
		loandto.add(mockloanResponsedto);

		List<Loan> loans = new ArrayList<>();
		loans.add(mockloanResponse1);

		CustomerDTO mockCustomerRes = new CustomerDTO();
		mockCustomerRes.setCustomerId(1);
		mockCustomerRes.setFname("David");

		Mockito.when(loanServiceImpl.getTotalLoansAmountforCustomerId(1)).thenReturn(mockCustomerRes);
		ResponseEntity<CustomerDTO> customerRes = loanController.getTotalLoansAmountforCustomerId(1);

		assertNotNull(customerRes);

	}

}
