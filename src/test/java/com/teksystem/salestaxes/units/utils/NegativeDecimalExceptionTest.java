package com.teksystem.salestaxes.units.utils;

import com.teksystem.salestaxes.model.items.TaxableItem;
import com.teksystem.salestaxes.utils.NegativeDecimalException;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class NegativeDecimalExceptionTest {

    @Test
    public void itShouldRaiseNegativeDecimalException() {

        try {
            new TaxableItem("Negative price fro Item", -22.3);
        } catch (NegativeDecimalException e) {
            assertThat(e.getMessage(), is("Values -22.3 for price provided is negative."));
        }
    }
}