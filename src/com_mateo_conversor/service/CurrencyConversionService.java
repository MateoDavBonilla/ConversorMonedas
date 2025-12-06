package com_mateo_conversor.service;

import com_mateo_conversor.api.dto.ExchangeRateResponse;

public class CurrencyConversionService {
    private final CurrencyFilterService filterService;

    public CurrencyConversionService(CurrencyFilterService filterService) {
        this.filterService = filterService;
    }

    public double convert(String fromCurrency, String toCurrency, double amount, ExchangeRateResponse response) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        if (response == null || response.getConversion_rates() == null) {
            throw new IllegalArgumentException("Invalid exchange rate data");
        }
        String base = response.getBase_code();
        var rates = response.getConversion_rates();

        //Normalize to upper-case
        String from = fromCurrency.toUpperCase();
        String to = toCurrency.toUpperCase();

        //Validate currencies
        filterService.validateCurrency(from, response);
        filterService.validateCurrency(to, response);

        double baseToFrom = rates.get(from);
        double baseToTo = rates.get(to);

        //From is base currency
        if (from.equals(base)) {
            return amount * baseToTo;
        }
        //to is base currency
        if (to.equals(base)){
            return amount / baseToFrom;
        }
        //neither is base currency
        double amountInBase = amount / baseToFrom;
        return amountInBase * baseToTo;
    }
}
