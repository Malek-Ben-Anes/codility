package com.codility.soldExchange;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class SoldProduct {
    String name;
    BigDecimal price;
    String currency;
}
