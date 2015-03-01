package com.teksystem.salestaxes.units.context;

import com.teksystem.salestaxes.context.TaxApplier;
import com.teksystem.salestaxes.context.TotalsCalculator;
import com.teksystem.salestaxes.model.items.NonTaxableImportedItem;
import com.teksystem.salestaxes.model.items.TaxableImportedItem;
import com.teksystem.salestaxes.model.taxes.visitor.TaxVisitorImpl;
import org.junit.Test;

import static com.teksystem.salestaxes.model.taxes.helper.TaxApplierHelper.addItemsTo;
import static java.util.Collections.EMPTY_LIST;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TotalsCalculatorTest {

    @Test
    public void itShouldCalculateTotalOfSalesTaxes() {
        final TaxApplier taxApplier = new TaxApplier(new TaxVisitorImpl(10.0, 5.0));

        addItemsTo(taxApplier,
                new NonTaxableImportedItem("imported box chocolates", 10.00),
                new TaxableImportedItem("imported bottle of perfume", 47.50)
        );

        final TotalsCalculator totalsCalculator = new TotalsCalculator(taxApplier.getTaxedItems());
        assertThat(totalsCalculator.calculateTotalOfSalesTaxes(), is(7.63));
    }

    @Test
    public void itShouldReturnZeroForSalesTaxesOfNoItem() {
        assertThat(new TotalsCalculator(EMPTY_LIST).calculateTotalOfSalesTaxes(), is(0.0));
    }

    @Test
    public void itShouldCalculateTotalOfItemsIncludingTaxes() {
        final TaxApplier taxApplier = new TaxApplier(new TaxVisitorImpl(10.0, 5.0));
        addItemsTo(taxApplier,
                new NonTaxableImportedItem("imported box chocolates", 10.00),
                new TaxableImportedItem("imported bottle of perfume", 47.50)
        );
        final TotalsCalculator totalsCalculator = new TotalsCalculator(taxApplier.getTaxedItems());
        assertThat(totalsCalculator.calculateTotalOfTaxedItems(), is(65.13));
    }

    @Test
    public void itShouldReturnZeroAsTotalOfNoItem() {
        assertThat(new TotalsCalculator(EMPTY_LIST).calculateTotalOfTaxedItems(), is(0.0));
    }
}