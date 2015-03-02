package com.teksystem.salestaxes.units.utils;

import com.teksystem.salestaxes.model.items.TaxableItem;
import com.teksystem.salestaxes.model.taxes.visitor.TaxVisitorImpl;
import com.teksystem.salestaxes.utils.NegativeDecimalException;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class NegativeDecimalExceptionTest {

    @Test
    public void itShouldRaiseNegativeDecimalExceptionForItems() {

        try {
            new TaxableItem("Negative price fro Item", new BigDecimal(-22.3));
        } catch (NegativeDecimalException e) {
            assertThat(e.getMessage(), is("Values -22.30 for price provided is negative."));
        }
    }

    @Test
    public void itShouldRaiseNegativeDecimalExceptionForTaxVisitor() {

        try {
            new TaxVisitorImpl(0.0, -22.3);
        } catch (NegativeDecimalException e) {
            assertThat(e.getMessage(), is("Some values provided 0.0 or -22.3 as rate are negatives."));
        }
    }


}