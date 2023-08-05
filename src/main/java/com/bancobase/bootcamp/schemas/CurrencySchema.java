package com.bancobase.bootcamp.schemas;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "currencies")
public class CurrencySchema {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "symbol", nullable = false, unique = true)
    private String symbol;

    @Column(name = "value", nullable = false)
    private Double value;

    public CurrencySchema(String name, String symbol, Double value) {
        this.name = name;
        this.symbol = symbol;
        this.value = value;
    }
    
    public CurrencySchema() {}
    
}