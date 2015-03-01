package com.teksystem.salestaxes.units.context;

import com.teksystem.salestaxes.context.TaxApplier;
import com.teksystem.salestaxes.model.items.NonTaxableImportedItem;
import com.teksystem.salestaxes.model.items.NonTaxableItem;
import com.teksystem.salestaxes.model.items.TaxableImportedItem;
import com.teksystem.salestaxes.model.items.TaxableItem;
import com.teksystem.salestaxes.model.taxes.visitor.TaxVisitorImpl;
import org.junit.Test;

import static com.teksystem.salestaxes.model.taxes.helper.TaxApplierHelper.addItemsTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TaxApplierTest {

    @Test
    public void itShouldAddItemsToTaxApplier() {
        final TaxApplier taxApplier = new TaxApplier(new TaxVisitorImpl(10.0, 5.0));
        addItemsTo(taxApplier,
                new TaxableImportedItem("music box", 20.30),
                new NonTaxableImportedItem("book", 12.49),
                new NonTaxableItem("book", 12.49),
                new TaxableItem("music box", 20.30)
        );
        assertThat(taxApplier.getTaxedItems().size(), is(4));
    }

    @Test
    public void itemListShouldBeEmptyByDefault() {
        final TaxApplier taxApplier = new TaxApplier(new TaxVisitorImpl(2.0, 23.0));
        assertThat(taxApplier.getTaxedItems().size(), is(0));
    }

    @Test
    public void itShouldCalculateTotalOfSalesTaxes() {
        final TaxApplier taxApplier = new TaxApplier(new TaxVisitorImpl(10.0, 5.0));
        addItemsTo(taxApplier,
                new NonTaxableImportedItem("imported box chocolates", 10.00),
                new TaxableImportedItem("imported bottle of perfume", 47.50)
        );
        assertThat(taxApplier.calculateTotalOfSalesTaxes(), is(7.63));
    }

    @Test
    public void itShouldReturnZeroForSalesTaxesOfNoItem() {
        final TaxApplier taxApplier = new TaxApplier(new TaxVisitorImpl(10.0, 5.0));
        assertThat(taxApplier.calculateTotalOfSalesTaxes(), is(0.0));
    }

    @Test
    public void itShouldCalculateTotalOfItemsIncludingTaxes() {
        final TaxApplier taxApplier = new TaxApplier(new TaxVisitorImpl(10.0, 5.0));
        addItemsTo(taxApplier,
                new NonTaxableImportedItem("imported box chocolates", 10.00),
                new TaxableImportedItem("imported bottle of perfume", 47.50)
        );
        assertThat(taxApplier.calculateTotalOfTaxedItems(), is(65.13));
    }

    @Test
    public void itShouldReturnZeroAsTotalOfNoItem() {
        final TaxApplier taxApplier = new TaxApplier(new TaxVisitorImpl(10.0, 5.0));
        assertThat(taxApplier.calculateTotalOfTaxedItems(), is(0.0));
    }
}