package com.bancobase.bootcamp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bancobase.bootcamp.dto.AccountDTO;
import com.bancobase.bootcamp.dto.CustomerDTO;
import com.bancobase.bootcamp.dto.CustomerInfoDTO;
import com.bancobase.bootcamp.dto.request.PreCustomerInfo;
import com.bancobase.bootcamp.schemas.AccountSchema;
import com.bancobase.bootcamp.schemas.CustomerSchema;
import com.bancobase.bootcamp.services.AccountService;
import com.bancobase.bootcamp.services.CustomerService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;

@RestController
@RequestMapping("/accounts")
@Tag(name = "Accounts controller")
@CrossOrigin(origins = {"*"}, methods = {RequestMethod.GET, RequestMethod.POST})
public class AccountController {
	private final AccountService accountService;
	
	@Autowired
	private CustomerService customerService;
	
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
	
	@GetMapping("/{id}")
    @Operation(summary = "Obtiene la cuenta del cliente por su id")
    public ResponseEntity<List<AccountDTO>> getCustomerAccountId(@PathVariable(value = "id") Long id) {
    	List<AccountDTO> accounts = this.accountService.getAllAccountsByCustomerId(id);

        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }
	
	@PostMapping("/{id}")
    @Operation(summary = "Create a new customer account")
    public ResponseEntity<AccountDTO> createCustomer(@PathVariable(value = "id") Long id) {
		CustomerSchema customer = customerService.getCustomerByCustomerId(id).orElseGet(null);
		AccountDTO savedAccount = this.accountService.createAccountByCustomerId(customer);
		 

        return new ResponseEntity<>(savedAccount, HttpStatus.CREATED);
    }
}
