package com.teksystem.salestaxes.model.items;

import java.math.BigDecimal;

public abstract class AbstractItem implements Item {
    private final String name;
    private final BigDecimal price;

    AbstractItem(final String name, final BigDecimal price) {
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
