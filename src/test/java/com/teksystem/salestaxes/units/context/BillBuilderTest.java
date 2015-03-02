package com.teksystem.salestaxes.units.context;

import com.teksystem.salestaxes.context.BillBuilder;
import com.teksystem.salestaxes.context.TaxApplier;
import com.teksystem.salestaxes.context.TotalsCalculator;
import com.teksystem.salestaxes.model.items.NonTaxableItem;
import com.teksystem.salestaxes.model.taxes.visitor.TaxVisitorImpl;
import com.teksystem.salestaxes.utils.NegativeDecimalException;
import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BillBuilderTest {

    @Test
    public void itShouldDisplayZeroAsTaxTotalForNonItem() throws NegativeDecimalException {
        final TaxApplier taxApplier = new TaxApplier(new TaxVisitorImpl(10.0, 5.0));

        final TotalsCalculator totalsCalculator = Mockito.mock(TotalsCalculator.class);
        Mockito.when(totalsCalculator.calculateTotalOfSalesTaxes()).thenReturn(new BigDecimal(0.0));
        Mockito.when(totalsCalculator.calculateTotalOfTaxedItems()).thenReturn(new BigDecimal(0.0));

        final BillBuilder billBuilder = new BillBuilder(totalsCalculator, taxApplier);

        assertThat(billBuilder.displayBill(), is("Sales Taxes: 0.00\nTotal: 0.00"));
    }

    @Test
    public void itShouldDisplayZeroAsTaxTotalForNonTaxableItem() throws NegativeDecimalException {
        final TaxApplier taxApplier = new TaxApplier(new TaxVisitorImpl(10.0, 5.0));
        taxApplier.applyTaxOn(new NonTaxableItem("chocolate bar", new BigDecimal(0.85)));

        final TotalsCalculator totalsCalculator = Mockito.mock(TotalsCalculator.class);
        Mockito.when(totalsCalculator.calculateTotalOfSalesTaxes()).thenReturn(new BigDecimal(0.0));
        Mockito.when(totalsCalculator.calculateTotalOfTaxedItems()).thenReturn(new BigDecimal(0.85));

        final BillBuilder billBuilder = new BillBuilder(totalsCalculator, taxApplier);

        assertThat(billBuilder.displayBill(), is("1 chocolate bar: 0.85\nSales Taxes: 0.00\nTotal: 0.85"));
    }

    @Test
    public void itShouldDisplayTaxTotalForTaxableItem() throws NegativeDecimalException {
        final TaxApplier taxApplier = new TaxApplier(new TaxVisitorImpl(10.0, 5.0));
        taxApplier.applyTaxOn(new NonTaxableItem("chocolate bar", new BigDecimal(0.85)));

        final TotalsCalculator totalsCalculator = Mockito.mock(TotalsCalculator.class);
        Mockito.when(totalsCalculator.calculateTotalOfSalesTaxes()).thenReturn(new BigDecimal(0.0));
        Mockito.when(totalsCalculator.calculateTotalOfTaxedItems()).thenReturn(new BigDecimal(0.85));

        final BillBuilder billBuilder = new BillBuilder(totalsCalculator, taxApplier);

        assertThat(billBuilder.displayBill(), is("1 chocolate bar: 0.85\nSales Taxes: 0.00\nTotal: 0.85"));
    }
}