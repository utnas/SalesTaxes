package com.teksystem.salestaxes.receipt.calculator.tax;

import com.teksystem.salestaxes.model.items.Item;
import com.teksystem.salestaxes.utils.NegativeDecimalException;

public interface ITaxApplier {

    public void applyTaxOn(final Item item) throws NegativeDecimalException;

    public String formatTaxedItems();

    public void clearItemsList();
}
