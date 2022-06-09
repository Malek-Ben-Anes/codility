package com.codility.soldExchange;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SoldProductsAggregator {

    private final EURExchangeService exchangeService;

    SoldProductsAggregator(EURExchangeService EURExchangeService) {
        this.exchangeService = EURExchangeService;
    }

    SoldProductsAggregate aggregate(Stream<SoldProduct> products) {
        if (products == null) {
            return new SoldProductsAggregate(new ArrayList<>(), BigDecimal.ZERO);
        }

        final List<SimpleSoldProduct> simpleProducts = products
                .map(prod -> new SimpleSoldProduct(prod.getName(), convertToEuro(prod.getCurrency(), prod.getPrice())))
                .filter(prod -> prod.getPrice() != null)
                .collect(Collectors.toList());

        BigDecimal total = simpleProducts.stream()
                .map(simpleProd -> simpleProd.getPrice())
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new SoldProductsAggregate(simpleProducts, total);
    }

    private BigDecimal convertToEuro(String currency, BigDecimal price) {
        Optional<BigDecimal> rateOpt = this.exchangeService.rate(currency);
        if (rateOpt == null || !rateOpt.isPresent() || rateOpt.get() == null || rateOpt.get().compareTo(BigDecimal.ZERO) < 0) {
            return null;
        }
        return price.multiply(rateOpt.get());
    }

}
