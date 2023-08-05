package com.bancobase.bootcamp.dto;

import java.util.Map;

import com.bancobase.bootcamp.dto.response.Symbol;
import com.bancobase.bootcamp.schemas.CurrencySchema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
public class CurrencyDTO {
    private String name;
    private String symbol;
    private Double value;

    public static CurrencyDTO getFromSchema(CurrencySchema currencySchema) {
        return CurrencyDTO.builder()
                .name(currencySchema.getName())
                .symbol(currencySchema.getSymbol())
                .value(currencySchema.getValue())
                .build();
    }
    

}