package com.teksystem.salestaxes.units.receipt;

import com.teksystem.salestaxes.receipt.ReceiptBuilder;
import com.teksystem.salestaxes.receipt.calculator.tax.TaxITaxApplier;
import com.teksystem.salestaxes.receipt.calculator.total.TotalsITotalsCalculator;
import com.teksystem.salestaxes.model.items.NonTaxableItem;
import com.teksystem.salestaxes.model.tax.TaxVisitorImpl;
import com.teksystem.salestaxes.utils.NegativeDecimalException;
import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ReceiptBuilderTest {

    @Test
    public void itShouldDisplayZeroAsTaxTotalForNonItem() throws NegativeDecimalException {
        final TaxITaxApplier taxApplier = new TaxITaxApplier(new TaxVisitorImpl(10.0, 5.0));

        final TotalsITotalsCalculator totalsCalculator = Mockito.mock(TotalsITotalsCalculator.class);
        Mockito.when(totalsCalculator.calculateTotalOfSalesTaxes()).thenReturn(new BigDecimal(0.0));
        Mockito.when(totalsCalculator.calculateTotalOfTaxedItems()).thenReturn(new BigDecimal(0.0));

        final ReceiptBuilder receiptBuilder = new ReceiptBuilder(totalsCalculator, taxApplier);

        assertThat(receiptBuilder.displayBill(), is("Sales Taxes: 0.00\nTotal: 0.00"));
    }

    @Test
    public void itShouldDisplayZeroAsTaxTotalForNonTaxableItem() throws NegativeDecimalException {
        final TaxITaxApplier taxApplier = new TaxITaxApplier(new TaxVisitorImpl(10.0, 5.0));
        taxApplier.applyTaxOn(new NonTaxableItem("chocolate bar", new BigDecimal(0.85)));

        final TotalsITotalsCalculator totalsCalculator = Mockito.mock(TotalsITotalsCalculator.class);
        Mockito.when(totalsCalculator.calculateTotalOfSalesTaxes()).thenReturn(new BigDecimal(0.0));
        Mockito.when(totalsCalculator.calculateTotalOfTaxedItems()).thenReturn(new BigDecimal(0.85));

        final ReceiptBuilder receiptBuilder = new ReceiptBuilder(totalsCalculator, taxApplier);

        assertThat(receiptBuilder.displayBill(), is("1 chocolate bar: 0.85\nSales Taxes: 0.00\nTotal: 0.85"));
    }

    @Test
    public void itShouldDisplayTaxTotalForTaxableItem() throws NegativeDecimalException {
        final TaxITaxApplier taxApplier = new TaxITaxApplier(new TaxVisitorImpl(10.0, 5.0));
        taxApplier.applyTaxOn(new NonTaxableItem("chocolate bar", new BigDecimal(0.85)));

        final TotalsITotalsCalculator totalsCalculator = Mockito.mock(TotalsITotalsCalculator.class);
        Mockito.when(totalsCalculator.calculateTotalOfSalesTaxes()).thenReturn(new BigDecimal(0.0));
        Mockito.when(totalsCalculator.calculateTotalOfTaxedItems()).thenReturn(new BigDecimal(0.85));

        final ReceiptBuilder receiptBuilder = new ReceiptBuilder(totalsCalculator, taxApplier);

        assertThat(receiptBuilder.displayBill(), is("1 chocolate bar: 0.85\nSales Taxes: 0.00\nTotal: 0.85"));
    }
}