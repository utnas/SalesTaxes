package com.teksystem.salestaxes.model.items;

import com.teksystem.salestaxes.model.tax.TaxVisitor;
import com.teksystem.salestaxes.utils.CustomDecimalFormatter;
import com.teksystem.salestaxes.utils.NegativeDecimalException;

import java.math.BigDecimal;

import static java.lang.String.format;

public class TaxableItemBuilder {
    private final String name;
    private final BigDecimal price;

    public TaxableItemBuilder(final String name, final BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public final Item make() throws NegativeDecimalException {
        if (price.doubleValue() < 0) {
            throw new NegativeDecimalException(format("Values %s for price provided is negative.", CustomDecimalFormatter.format(price)));
        }
        return new Item() {
            @Override
            public BigDecimal getPrice() {
                return price;
            }

            @Override
            public String getName() {
                return name;
            }

            @Override
            public void accept(TaxVisitor taxVisitor) {
            }
        };
    }
}
