package com.teksystem.salestaxes.units.receipt.calculator.total;

import com.teksystem.salestaxes.model.items.NonTaxableImportedItem;
import com.teksystem.salestaxes.model.items.TaxableImportedItem;
import com.teksystem.salestaxes.model.tax.TaxVisitorImpl;
import com.teksystem.salestaxes.receipt.calculator.tax.TaxApplierImpl;
import com.teksystem.salestaxes.receipt.calculator.total.TotalsCalculatorImpl;
import com.teksystem.salestaxes.utils.NegativeDecimalException;
import org.junit.Test;

import java.math.BigDecimal;

import static com.teksystem.salestaxes.receipt.calculator.tax.TaxApplierHelper.addItemsTo;
import static com.teksystem.salestaxes.utils.CustomDecimalFormatter.format;
import static java.util.Collections.EMPTY_LIST;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TotalsCalculatorImplTest {

    @Test
    public void itShouldCalculateTotalOfSalesTaxes() throws NegativeDecimalException {
        final TaxApplierImpl taxApplier = new TaxApplierImpl(new TaxVisitorImpl(10.0, 5.0));

        addItemsTo(taxApplier,
                new NonTaxableImportedItem("imported box chocolates", new BigDecimal(10.00)),
                new TaxableImportedItem("imported bottle of perfume", new BigDecimal(47.50))
        );

        final TotalsCalculatorImpl totalsCalculator = new TotalsCalculatorImpl(taxApplier.getTaxedItems());
        assertThat(totalsCalculator.calculateTotalOfSalesTaxes().doubleValue(), is(7.63));
    }

    @Test
    public void itShouldReturnZeroForSalesTaxesOfNoItem() {
        assertThat(new TotalsCalculatorImpl(EMPTY_LIST).calculateTotalOfSalesTaxes().doubleValue(), is(0.0));
    }

    @Test
    public void itShouldCalculateTotalOfItemsIncludingTaxes() throws NegativeDecimalException {
        final TaxApplierImpl taxApplier = new TaxApplierImpl(new TaxVisitorImpl(10.0, 5.0));
        addItemsTo(taxApplier,
                new NonTaxableImportedItem("imported box chocolates", new BigDecimal(10.00)),
                new TaxableImportedItem("imported bottle of perfume", new BigDecimal(47.50))
        );
        final TotalsCalculatorImpl totalsCalculator = new TotalsCalculatorImpl(taxApplier.getTaxedItems());
        assertThat(format(totalsCalculator.calculateTotalOfTaxedItems()), is("65.13"));
    }

    @Test
    public void itShouldReturnZeroAsTotalOfNoItem() {
        assertThat(new TotalsCalculatorImpl(EMPTY_LIST).calculateTotalOfTaxedItems().doubleValue(), is(0.0));
    }
}