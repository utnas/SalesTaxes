package com.teksystem.salestaxes.model.items;

import com.teksystem.salestaxes.utils.NegativeDecimalException;

public abstract class AbstractItem implements Item {
    private final String name;
    private final Double price;

    AbstractItem(final String name, final Double price) throws NegativeDecimalException {
        if (price < 0) {
            throw new NegativeDecimalException("Values " + price + " for price provided is negative.");
        }
        this.price = price;
        this.name = name;
    }

    @Override
    public Double getPrice() {
        return price;
    }

    @Override
    public String getName() {
        return name;
    }
}
