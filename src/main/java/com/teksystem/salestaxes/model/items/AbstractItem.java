package com.teksystem.salestaxes.model.items;

public abstract class AbstractItem implements Item {
    private final String name;
    private final Double price;

    public AbstractItem(final String name, final Double price) {
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
