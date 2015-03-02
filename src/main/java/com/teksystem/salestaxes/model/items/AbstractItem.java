package com.teksystem.salestaxes.model.items;

import com.teksystem.salestaxes.utils.NegativeDecimalException;

import java.math.BigDecimal;

import static com.teksystem.salestaxes.utils.CustomDecimalFormatter.format;

public abstract class AbstractItem implements Item {
    private final String name;
    private final BigDecimal price;

    AbstractItem(final String name, final BigDecimal price) throws NegativeDecimalException {
        if (price.doubleValue() < 0) {
            throw new NegativeDecimalException("Values " + format(price) + " for price provided is negative.");
        }
        this.price = price;
        this.name = name;
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public String getName() {
        return name;
    }
}
