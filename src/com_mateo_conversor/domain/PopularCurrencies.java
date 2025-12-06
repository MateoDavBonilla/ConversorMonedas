package com_mateo_conversor.domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class PopularCurrencies {
    private static final Map<String, String> POPULAR = new LinkedHashMap<>();

    static {
        POPULAR.put("USD", "United States Dollar");
        POPULAR.put("EUR", "Euro (European Union)");
        POPULAR.put("MXN", "Mexican Peso (Mexico)");
        POPULAR.put("JPY", "Japanese Yen (Japan)");
        POPULAR.put("GBP", "British Pound (United Kingdom)");
        POPULAR.put("BRL", "Brazilian Real (Brazil)");
        POPULAR.put("ARS", "Argentine Peso (Argentina)");
        POPULAR.put("COP", "Colombian Peso (Colombia)");
        POPULAR.put("CAD", "Canadian Dollar (Canada)");
        POPULAR.put("AUD", "Australian Dollar (Australia)");
    }

    public static Map<String, String> getPopularCurrencies() {
        return POPULAR;
    }
}
