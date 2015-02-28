package com.teksystem.salestaxes.model.factory;

import com.teksystem.salestaxes.model.Item;
import com.teksystem.salestaxes.model.TaxableItem;

import java.math.BigDecimal;

public class ItemFactory {

    public Item getItem(final Item item, final BigDecimal rate) {

        if (item instanceof TaxableItem) {
            //return new TaxableItem(item.getName(), rate.add(BigDecimal.valueOf(item.getPrice())));
        }

        return item;
    }
}
