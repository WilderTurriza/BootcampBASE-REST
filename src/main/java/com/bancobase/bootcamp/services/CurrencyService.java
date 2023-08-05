package com.bancobase.bootcamp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bancobase.bootcamp.dto.CurrencyDTO;
import com.bancobase.bootcamp.dto.response.ExchangeRateResponse;
import com.bancobase.bootcamp.dto.response.Symbol;
import com.bancobase.bootcamp.dto.response.SymbolsNameResponse;
import com.bancobase.bootcamp.http.APIExchangeRateClient;
import com.bancobase.bootcamp.repositories.CurrencyRepository;
import com.bancobase.bootcamp.schemas.CurrencySchema;

@Service
public class CurrencyService {
    @Autowired
    private APIExchangeRateClient api;
    @Autowired
    private CurrencyRepository currencyRepository;

    public List<CurrencyDTO> getCurrencies() {
    	List<CurrencyDTO> currencies = new ArrayList<>();
        ExchangeRateResponse exchangeRateResponse = api.getExchangeRate();
        SymbolsNameResponse symbolsNameResponse = api.getSymbolsName();

        Map<String, Double> exchangeRates = exchangeRateResponse.getRates();
        Map<String, Symbol> symbols = symbolsNameResponse.getSymbols();

        for (Map.Entry<String, Double> entry : exchangeRates.entrySet()) {
            String currencyCode = entry.getKey();
            Double value = entry.getValue();
            Symbol symbol = symbols.get(currencyCode);
            if (symbol != null) {
                String currencyName = symbol.getDescription();
                String currencySymbol = symbol.getCode();

                CurrencySchema existingCurrency = currencyRepository.findBySymbol(currencySymbol);

                if (existingCurrency != null) {
                    //Ya existe una moneda con este símbolo
                    existingCurrency.setValue(value);
                    currencyRepository.save(existingCurrency);
                } else {
                    //No existe una moneda con este símbolo
                    CurrencySchema newCurrency = new CurrencySchema();
                    newCurrency.setName(currencyName);
                    newCurrency.setSymbol(currencySymbol);
                    newCurrency.setValue(value);
                    currencyRepository.save(newCurrency);
                }

                CurrencyDTO currencyDTO = CurrencyDTO.builder()
                        .name(currencyName)
                        .symbol(currencySymbol)
                        .value(value)
                        .build();

                currencies.add(currencyDTO);
            }
        }
        return currencies;
    }
}