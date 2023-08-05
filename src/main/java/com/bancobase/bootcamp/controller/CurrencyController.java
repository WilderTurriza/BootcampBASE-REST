package com.bancobase.bootcamp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.bancobase.bootcamp.dto.CurrencyDTO;
import com.bancobase.bootcamp.services.CurrencyService;

@RestController
public class CurrencyController {
	@Autowired
    private CurrencyService currencyService;

    @GetMapping("/currency")
    public ResponseEntity<List<CurrencyDTO>> getAllCurrencies() {
        List<CurrencyDTO> currencies = this.currencyService.getCurrencies();
        return new ResponseEntity<>(currencies, HttpStatus.OK);
    }

}