package com_mateo_conversor.service;

import com_mateo_conversor.api.dto.ExchangeRateResponse;

import java.util.Set;

public class CurrencyFilterService {
    /**
     * Checks if a given currency code exists in the conversion rates
     * returned by the API.
     */
    public boolean isSupportedCurrency(String currencyCode, ExchangeRateResponse response) {
        if (currencyCode == null || response == null || response.getConversion_rates() == null) {
            return false;
        }
        String normalizedCode = currencyCode.toUpperCase();

        return response.getConversion_rates().containsKey(normalizedCode);
    }

    /**
     * Returns all available currency codes from the conversion rates map.
     */

    public Set<String> getAvailableCurrencies(ExchangeRateResponse response) {
        if (response == null || response.getConversion_rates() == null) {
            return Set.of(); //empty set;
        }
        return response.getConversion_rates().keySet();
    }

    /**
     * Validates that a currency code exists in the conversion rates map.
     * Throws an exception if not found.
     */
    public void validateCurrency(String currencyCode, ExchangeRateResponse response) {
        if (!isSupportedCurrency(currencyCode, response)) {
            throw new IllegalArgumentException("Unsupported: " + currencyCode);
        }
    }
}
