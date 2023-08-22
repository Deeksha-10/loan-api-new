package com.loan.test.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.loan.dao.CustomerRepository;
import com.loan.dao.LoanRepository;
import com.loan.entity.Customer;
import com.loan.entity.Loan;
import com.loan.exceptions.LoanNotFoundException;
import com.loan.models.CustomerDTO;
import com.loan.models.LoanDTO;
import com.loan.services.impl.LoanServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class LoanServiceImplTest {

	@InjectMocks
	private LoanServiceImpl loanServiceImpl;

	@Mock
	private LoanRepository loanRepo;

	@Mock
	private CustomerRepository customerRepo;

	@Test
	public void applyLoanTest() {

		Loan mockloanRequest = new Loan();
		mockloanRequest.setCustomerId(1);
		mockloanRequest.setLoanAmt(6000.0);

		Loan mockloanResponse = new Loan();
		mockloanResponse.setCustomerId(1);
		mockloanResponse.setLoanAmt(6000.0);
		mockloanResponse.setLoanReqNo(2);

		Loan mockloanResponse1 = new Loan();
		mockloanResponse.setCustomerId(1);
		mockloanResponse.setLoanAmt(4000.0);
		mockloanResponse.setLoanReqNo(2);

		List<Loan> loans = new ArrayList<>();
		loans.add(mockloanResponse);
		loans.add(mockloanResponse1);

		mockloanRequest.setCustomerId(1);

		Optional<Customer> mockCustomerResponse = Optional.of(new Customer());

		Mockito.when(customerRepo.findById(1)).thenReturn(mockCustomerResponse);
		Mockito.when(loanRepo.findAllByCustomerId(1)).thenReturn(loans);
		Mockito.when(loanRepo.save(mockloanRequest)).thenReturn(mockloanResponse1);
		LoanDTO loan = loanServiceImpl.applyLoan(mockloanRequest);

		assertNotNull(loan);

	}

	@Test
	public void getLoansByCustomerIdTest() {

		Loan mockloanRequest = new Loan();
		mockloanRequest.setCustomerId(1);
		mockloanRequest.setLoanAmt(6000.0);

		Loan mockloanResponse = new Loan();
		mockloanResponse.setCustomerId(1);
		mockloanResponse.setLoanAmt(6000.0);
		mockloanResponse.setLoanReqNo(2);

		Loan mockloanResponse1 = new Loan();
		mockloanResponse.setCustomerId(1);
		mockloanResponse.setLoanAmt(4000.0);
		mockloanResponse.setLoanReqNo(2);

		List<Loan> loans = new ArrayList<>();
		loans.add(mockloanResponse);
		loans.add(mockloanResponse1);

		Optional<Customer> mockCustomerRes = Optional.of(new Customer());

		Mockito.when(customerRepo.findById(1)).thenReturn(mockCustomerRes);
		Mockito.when(loanRepo.findAllByCustomerId(1)).thenReturn(loans);
		List<Loan> loansbyId = loanServiceImpl.getLoansByCustomerId(1);
		assertNotNull(loansbyId);

	}

	@Test
	public void getTotalLoansAmountforCustomerIdTest() {

		Loan mockloanRequest = new Loan();
		mockloanRequest.setCustomerId(1);
		mockloanRequest.setLoanAmt(6000.0);

		Loan mockloanResponse = new Loan();
		mockloanResponse.setCustomerId(1);
		mockloanResponse.setLoanAmt(6000.0);
		mockloanResponse.setLoanReqNo(2);

		Loan mockloanResponse1 = new Loan();
		mockloanResponse.setCustomerId(1);
		mockloanResponse.setLoanAmt(4000.0);
		mockloanResponse.setLoanReqNo(2);

		List<Loan> loans = new ArrayList<>();
		loans.add(mockloanResponse);
		loans.add(mockloanResponse1);

		CustomerDTO mockCustomerdtoRequest = new CustomerDTO();
		mockCustomerdtoRequest.setCustomerId(1);
		mockCustomerdtoRequest.setFname("David");

		Optional<Customer> mockCustomerRes = Optional.of(new Customer());

		Mockito.when(customerRepo.findById(1)).thenReturn(mockCustomerRes);
		Mockito.when(loanRepo.findAllByCustomerId(1)).thenReturn(loans);

		CustomerDTO customerdto = loanServiceImpl.getTotalLoansAmountforCustomerId(1);
		assertNotNull(customerdto);

	}

	@Test
	public void createNewCustomerTest() {

		Loan mockloanRequest = new Loan();
		mockloanRequest.setCustomerId(null);
		mockloanRequest.setFname("David");
		mockloanRequest.setLname("Wagner");
		mockloanRequest.setLoanAmt(6000.0);

		Loan mockloanResponse = new Loan();
		mockloanResponse.setCustomerId(1);
		mockloanResponse.setLoanAmt(6000.0);
		mockloanResponse.setLoanReqNo(2);

		Loan mockloanResponse1 = new Loan();
		mockloanResponse1.setCustomerId(1);
		mockloanResponse1.setLoanAmt(4000.0);
		mockloanResponse1.setFname("David");
		mockloanResponse1.setLname("Wagner");
		mockloanResponse.setLoanReqNo(2);

		List<Loan> loans = new ArrayList<>();
		loans.add(mockloanResponse);
		loans.add(mockloanResponse1);

		Customer mockCustomerResponse = new Customer();
		mockCustomerResponse.setTotalLoanAmt(12000);
		mockCustomerResponse.setCustomerId(1);
		mockCustomerResponse.setFname("David");
		mockCustomerResponse.setLname("Wagner");

		Customer mockCustomerRequest = new Customer();
		mockCustomerRequest.setTotalLoanAmt(12000);
		mockCustomerRequest.setFname("David");
		mockCustomerRequest.setLname("Wagner");
		mockCustomerRequest.setCustomerId(null);

		Mockito.when(customerRepo.save(Mockito.any())).thenReturn(mockCustomerResponse);
		Mockito.when(loanRepo.save(Mockito.any())).thenReturn(mockloanResponse1);
		LoanDTO loan = loanServiceImpl.createNewCustomer(mockloanRequest);
		assertNotNull(loan);

	}

	@Test(expected = LoanNotFoundException.class)
	public void testLoanNotFoundException() throws LoanNotFoundException {

		Loan mockloanRequest = new Loan();
		mockloanRequest.setCustomerId(1);
		mockloanRequest.setLoanAmt(15000.0);

		Loan mockloanResponse = new Loan();
		mockloanResponse.setCustomerId(1);
		mockloanResponse.setLoanAmt(6000.0);
		mockloanResponse.setLoanReqNo(2);

		Loan mockloanResponse1 = new Loan();
		mockloanResponse.setCustomerId(1);
		mockloanResponse.setLoanAmt(4000.0);
		mockloanResponse.setLoanReqNo(2);

		List<Loan> loans = new ArrayList<>();
		loans.add(mockloanResponse);
		loans.add(mockloanResponse1);

		Optional<Customer> mockCustomerResponse = Optional.of(new Customer());

		Mockito.when(customerRepo.findById(1)).thenReturn(mockCustomerResponse);
		Mockito.when(loanRepo.findAllByCustomerId(1)).thenReturn(loans);
		Mockito.when(loanRepo.save(mockloanRequest)).thenThrow(LoanNotFoundException.class);
		LoanDTO loan = loanServiceImpl.applyLoan(mockloanRequest);
		assertThrows(LoanNotFoundException.class, Mockito.any());

	}

	@Test(expected = LoanNotFoundException.class)
	public void testLoanTotalAmountLimit() throws LoanNotFoundException {

		Loan mockloanRequest = new Loan();
		mockloanRequest.setCustomerId(null);
		mockloanRequest.setFname("David");
		mockloanRequest.setLname("Wagner");
		mockloanRequest.setLoanAmt(13000.0);

		Loan mockloanResponse = new Loan();
		mockloanResponse.setCustomerId(1);
		mockloanResponse.setLoanAmt(6000.0);
		mockloanResponse.setLoanReqNo(2);

		Loan mockloanResponse1 = new Loan();
		mockloanResponse.setCustomerId(1);
		mockloanResponse.setLoanAmt(4000.0);
		mockloanResponse.setLoanReqNo(2);

		List<Loan> loans = new ArrayList<>();
		loans.add(mockloanResponse);
		loans.add(mockloanResponse1);

		Optional<Customer> mockCustomerResponse = Optional.of(new Customer());

		LoanDTO loan = loanServiceImpl.applyLoan(mockloanRequest);
		assertThrows(LoanNotFoundException.class, Mockito.any());

	}

	@Test
	public void createNewCustomerTestForApplyLoan() {

		Loan mockloanRequest = new Loan();
		mockloanRequest.setCustomerId(null);
		mockloanRequest.setFname("David");
		mockloanRequest.setLname("Wagner");
		mockloanRequest.setLoanAmt(6000.0);

		Loan mockloanResponse = new Loan();
		mockloanResponse.setCustomerId(1);
		mockloanResponse.setLoanAmt(6000.0);
		mockloanResponse.setLoanReqNo(2);

		Loan mockloanResponse1 = new Loan();
		mockloanResponse1.setCustomerId(1);
		mockloanResponse1.setLoanAmt(4000.0);
		mockloanResponse1.setFname("David");
		mockloanResponse1.setLname("Wagner");
		mockloanResponse.setLoanReqNo(2);

		List<Loan> loans = new ArrayList<>();
		loans.add(mockloanResponse);
		loans.add(mockloanResponse1);

		Customer mockCustomerResponse = new Customer();
		mockCustomerResponse.setTotalLoanAmt(12000);
		mockCustomerResponse.setCustomerId(1);
		mockCustomerResponse.setFname("David");
		mockCustomerResponse.setLname("Wagner");

		Customer mockCustomerRequest = new Customer();
		mockCustomerRequest.setTotalLoanAmt(12000);
		mockCustomerRequest.setFname("David");
		mockCustomerRequest.setLname("Wagner");
		mockCustomerRequest.setCustomerId(null);

		Mockito.when(customerRepo.save(Mockito.any())).thenReturn(mockCustomerResponse);
		Mockito.when(loanRepo.save(Mockito.any())).thenReturn(mockloanResponse1);
		LoanDTO loan = loanServiceImpl.applyLoan(mockloanRequest);
		assertNotNull(loan);

	}

	@Test
	public void getTotalLoansAmountNullforCustomerIdTest() {

		Loan mockloanRequest = new Loan();
		mockloanRequest.setCustomerId(1);
		mockloanRequest.setLoanAmt(6000.0);

		Loan mockloanResponse = new Loan();

		Loan mockloanResponse1 = new Loan();

		List<Loan> loans = new ArrayList<>();

		CustomerDTO mockCustomerdtoRequest = new CustomerDTO();

		Optional<Customer> mockCustomerRes = Optional.of(new Customer());

		Mockito.when(customerRepo.findById(1)).thenReturn(mockCustomerRes);
		Mockito.when(loanRepo.findAllByCustomerId(1)).thenReturn(loans);

		CustomerDTO customerdto = loanServiceImpl.getTotalLoansAmountforCustomerId(1);
		assertNotNull(customerdto);

	}

}
