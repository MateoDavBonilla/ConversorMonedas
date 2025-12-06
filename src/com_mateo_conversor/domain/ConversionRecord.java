package com_mateo_conversor.domain;

public class ConversionRecord {
    private final String from;
    private final String to;
    private final double amount;
    private final double result;

    public ConversionRecord(String from, String to, double amount, double result) {
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.result = result;
    }

    @Override
    public String toString() {
        return amount + " " + from + " → " + result + " " + to;
    }
}
