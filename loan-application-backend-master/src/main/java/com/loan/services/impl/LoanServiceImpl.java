package com.loan.services.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loan.dao.CustomerRepository;
import com.loan.dao.LoanRepository;
import com.loan.entity.Customer;
import com.loan.entity.Loan;
import com.loan.exceptions.CustomerNotFoundException;
import com.loan.exceptions.LoanNotFoundException;
import com.loan.models.CustomerDTO;
import com.loan.models.LoanDTO;
import com.loan.services.LoanService;

@Service
public class LoanServiceImpl implements LoanService {

	@Autowired
	private LoanRepository loanDao;

	@Autowired
	private CustomerRepository customerDao;

	private Logger logger = Logger.getLogger(getClass());

	public LoanDTO applyLoan(Loan loan) {
		Integer customerId = loan.getCustomerId();

		LoanDTO loanDTO = new LoanDTO();
		loanDTO.setCustomerId(loan.getCustomerId());
		loanDTO.setLoanAmt(loan.getLoanAmt());
		loanDTO.setFname(loan.getFname());
		loanDTO.setLname(loan.getLname());

		if (loan.getCustomerId() != null) {
			Customer customer = customerDao.findById(customerId)
					.orElseThrow(() -> new CustomerNotFoundException("Cusotmer Not Found: " + customerId));
			CustomerDTO customerdto = toConvertCustomerEntity(customer);
			List<Loan> loans = loanDao.findAllByCustomerId(customerId);

			if (!loans.isEmpty()) {
				double loanAmt = loans.stream().mapToDouble(lo -> lo.getLoanAmt()).sum();
				double totalLoanAmt = loan.getLoanAmt() + loanAmt;
				if (totalLoanAmt <= 12000) {
					loanDao.save(loan);
					logger.info("loan saved successfully");
				} else {
					throw new LoanNotFoundException("loan maximum limit exceeded for customer:" + customerId);
				}
			}

		} else if (loan.getLoanAmt() > 12000) {
			throw new LoanNotFoundException("Please apply the loan less than 12000:");
		} else {

			LoanDTO loandto = createNewCustomer(loan);

			return loandto;

		}

		return loanDTO;

	}

	@Override
	public List<Loan> getLoansByCustomerId(Integer customerId) {

		Customer customer = customerDao.findById(customerId)
				.orElseThrow(() -> new CustomerNotFoundException("Customer Not Found: " + customerId));

		List<Loan> loans = loanDao.findAllByCustomerId(customerId);

		return loans;
	}

	public LoanDTO createNewCustomer(Loan loan) {

		Customer customerdetail = new Customer();
		customerdetail.setFname(loan.getFname());
		customerdetail.setLname(loan.getLname());
		customerdetail.setTotalLoanAmt(loan.getLoanAmt());

		Loan loanentity = new Loan();

		Customer customersaved = customerDao.save(customerdetail);

		LoanDTO loandto = new LoanDTO();
		loandto.setCustomerId(customersaved.getCustomerId());
		loandto.setLoanAmt(loan.getLoanAmt());
		loandto.setFname(loan.getFname());
		loandto.setLname(loan.getLname());

		if (customersaved != null) {
			loanentity.setCustomerId(customersaved.getCustomerId());
			loanentity.setLoanAmt(loan.getLoanAmt());
			loanentity.setFname(loan.getFname());
			loanentity.setLname(loan.getLname());

			Loan loanEntity = loanDao.save(loanentity);

		}

		return loandto;

	}

	private CustomerDTO toConvertCustomerEntity(Customer customerreq) {
		CustomerDTO customer = new CustomerDTO();
		customer.setCustomerId(customerreq.getCustomerId());
		customer.setFname(customerreq.getFname());
		customer.setLname(customerreq.getLname());
		customer.setTotalLoanAmt(customerreq.getTotalLoanAmt());
		return customer;
	}

	private LoanDTO toConvertLoanEntity(Loan loan) {
		LoanDTO loandto = new LoanDTO();
		loan.setCustomerId(loan.getCustomerId());
		loan.setLoanAmt(loan.getLoanAmt());

		return loandto;
	}

	private List<LoanDTO> convertAndMapEntityList(List<Loan> loans) {
		return loans.stream().map(this::toConvertLoanEntity).collect(Collectors.toList());
	}

	private Loan toConvertLoanDTO(LoanDTO loandto) {
		Loan loan = new Loan();
		loan.setCustomerId(loandto.getCustomerId());
		loan.setLoanAmt(loandto.getLoanAmt());
		loan.setFname(loandto.getFname());
		loan.setLname(loandto.getLname());

		return loan;
	}

	@Override
	public CustomerDTO getTotalLoansAmountforCustomerId(Integer customerId) {

		CustomerDTO customerdto1 = new CustomerDTO();
		Customer customer = customerDao.findById(customerId)
				.orElseThrow(() -> new CustomerNotFoundException("Cusotmer Not Found: " + customerId));
		List<Loan> loans = loanDao.findAllByCustomerId(customerId);
		if (!loans.isEmpty()) {
			double loanAmt = loans.stream().mapToDouble(lo -> lo.getLoanAmt()).sum();
			customer.setTotalLoanAmt(loanAmt);
			CustomerDTO customerdto = toConvertCustomerEntity(customer);
			return customerdto;
		}
		return customerdto1;
	}
}
