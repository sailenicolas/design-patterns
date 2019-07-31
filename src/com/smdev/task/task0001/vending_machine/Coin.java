package com.smdev.task.task0001.vending_machine;

import lombok.Getter;

import java.math.BigDecimal;

public enum Coin {
    /**
     * 10 cents
     */
    CENTS_10(BigDecimal.valueOf(0.1), 1.35, 2.268, "10 cents"),
    /**
     * 25 cents
     */
    CENTS_25(BigDecimal.valueOf(0.25), 1.75, 5.67, "25 cents"),
    /**
     * 50 cents
     */
    CENTS_50(BigDecimal.valueOf(0.5), 2.15, 11.34, "50 cents"),
    /**
     * 1 dollar
     */
    DOLLAR_1(BigDecimal.valueOf(1.0), 2.58, 22.68, "1 dollar"),
    /**
     * NULL OBJECT
     */
    UNKNOWN(BigDecimal.valueOf(0), 0, 0, "Not Recognized")
    ;
    /**
     * The value
     */
    @Getter
    private BigDecimal nominal;

    /**
     * In millimeters
     */
    @Getter
    private double diameter;

    /**
     * In millimeters
     */
    @Getter
    private String label;

    /**
     * In grams
     */
    @Getter
    private double weight;

    Coin(BigDecimal nominal, double diameter, double weight, String label) {
        this.nominal = nominal;
        this.diameter = diameter;
        this.weight = weight;
        this.label = label;
    }

    /**
     * Acts as an abstract factory
     *
     * @param diameter
     * @param weight
     * @return
     */
    public static Coin get(double diameter, double weight) {
        Coin[] coins = Coin.values();
        for(Coin coin: coins){
            if(coin.getDiameter() == diameter && coin.getWeight() == weight){
                return coin;
            }
        }
        return UNKNOWN;
    }
}
