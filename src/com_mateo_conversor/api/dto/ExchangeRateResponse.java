package com_mateo_conversor.api.dto;

import java.util.Map;

public class ExchangeRateResponse {
    private String result;
    private String base_code;

    public String getResult() {
        return result;
    }

    public String getBase_code() {
        return base_code;
    }

    public Map<String, Double> getConversion_rates() {
        return conversion_rates;
    }

    private Map<String, Double> conversion_rates;
}
