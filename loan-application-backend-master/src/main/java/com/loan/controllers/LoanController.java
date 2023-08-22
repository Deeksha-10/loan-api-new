package com.loan.controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loan.entity.Loan;
import com.loan.models.CustomerDTO;
import com.loan.models.LoanDTO;
import com.loan.services.LoanService;

@RestController
@RequestMapping("/loan")
public class LoanController {

	@Autowired
	private ModelMapper mapper;

	@Autowired(required = true)
	private LoanService loanService;

	@PostMapping
	public ResponseEntity<LoanDTO> applyLoan(@RequestBody Loan loan) {

		return new ResponseEntity<LoanDTO>(loanService.applyLoan(loan), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CustomerDTO> getTotalLoansAmountforCustomerId(@PathVariable Integer id) {

		return new ResponseEntity<CustomerDTO>(loanService.getTotalLoansAmountforCustomerId(id), HttpStatus.OK);

	}

	@GetMapping("/customer/{id}")
	public ResponseEntity<List<Loan>> getLoansByCustomerId(@PathVariable Integer id) {
		return new ResponseEntity<List<Loan>>(loanService.getLoansByCustomerId(id), HttpStatus.OK);
	}

}