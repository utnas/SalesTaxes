package com.teksystem.salestaxes.units.context;

import com.teksystem.salestaxes.context.TaxApplier;
import com.teksystem.salestaxes.context.TotalsCalculator;
import com.teksystem.salestaxes.model.items.NonTaxableImportedItem;
import com.teksystem.salestaxes.model.items.TaxableImportedItem;
import com.teksystem.salestaxes.model.taxes.visitor.TaxVisitorImpl;
import com.teksystem.salestaxes.utils.NegativeDecimalException;
import org.junit.Test;

import java.math.BigDecimal;

import static com.teksystem.salestaxes.model.taxes.helper.TaxApplierHelper.addItemsTo;
import static com.teksystem.salestaxes.utils.CustomDecimalFormatter.format;
import static java.util.Collections.EMPTY_LIST;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TotalsCalculatorTest {

    @Test
    public void itShouldCalculateTotalOfSalesTaxes() throws NegativeDecimalException {
        final TaxApplier taxApplier = new TaxApplier(new TaxVisitorImpl(10.0, 5.0));

        addItemsTo(taxApplier,
                new NonTaxableImportedItem("imported box chocolates", new BigDecimal(10.00)),
                new TaxableImportedItem("imported bottle of perfume", new BigDecimal(47.50))
        );

        final TotalsCalculator totalsCalculator = new TotalsCalculator(taxApplier.getTaxedItems());
        assertThat(totalsCalculator.calculateTotalOfSalesTaxes().doubleValue(), is(7.63));
    }

    @Test
    public void itShouldReturnZeroForSalesTaxesOfNoItem() {
        assertThat(new TotalsCalculator(EMPTY_LIST).calculateTotalOfSalesTaxes().doubleValue(), is(0.0));
    }

    @Test
    public void itShouldCalculateTotalOfItemsIncludingTaxes() throws NegativeDecimalException {
        final TaxApplier taxApplier = new TaxApplier(new TaxVisitorImpl(10.0, 5.0));
        addItemsTo(taxApplier,
                new NonTaxableImportedItem("imported box chocolates", new BigDecimal(10.00)),
                new TaxableImportedItem("imported bottle of perfume", new BigDecimal(47.50))
        );
        final TotalsCalculator totalsCalculator = new TotalsCalculator(taxApplier.getTaxedItems());
        assertThat(format(totalsCalculator.calculateTotalOfTaxedItems()), is("65.13"));
    }

    @Test
    public void itShouldReturnZeroAsTotalOfNoItem() {
        assertThat(new TotalsCalculator(EMPTY_LIST).calculateTotalOfTaxedItems().doubleValue(), is(0.0));
    }
}