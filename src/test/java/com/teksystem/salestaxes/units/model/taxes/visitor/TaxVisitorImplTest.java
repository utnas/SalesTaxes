package com.teksystem.salestaxes.units.model.taxes.visitor;

import com.teksystem.salestaxes.model.items.*;
import com.teksystem.salestaxes.model.taxes.visitor.TaxVisitorImpl;
import com.teksystem.salestaxes.utils.Pair;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.concurrent.atomic.AtomicReference;

import static com.teksystem.salestaxes.utils.CustomFormatter.format;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;

public class TaxVisitorImplTest {

    @Test
    public void itShouldReturnComputedTaxItemAfterComputingForTaxTaxableItem() {
        final TaxableItem taxableItem = mockTaxableItem("music CD", 14.99);
        final Pair<Item, Double> itemTax = new TaxVisitorImpl(10.0, 5.0).visit(taxableItem);

        assertThat(itemTax.first().getName(), is(taxableItem.getName()));
        assertNotEquals(itemTax.first().getPrice(), is(taxableItem.getPrice()));
        assertThat(format(itemTax.first().getPrice()), is(16.49));
    }


    @Test
    public void itShouldComputeTaxForTaxableItem() {
        final TaxableItem taxableItem = mockTaxableItem("music CD", 14.99);
        final TaxVisitorImpl taxVisitor = new TaxVisitorImpl(10.0, 5.0);

        assertThat(taxVisitor.visit(taxableItem).second(), is(1.50));
    }

    @Test
    public void itShouldComputeTaxForNonTaxableItem() {
        final TaxVisitorImpl taxVisitor = new TaxVisitorImpl(10.0, 5.0);

        final NonTaxableItem nonTaxableItem = Mockito.mock(NonTaxableItem.class);
        Mockito.when(nonTaxableItem.getName()).thenReturn("book");
        Mockito.when(nonTaxableItem.getPrice()).thenReturn(12.49);

        assertThat(taxVisitor.visit(nonTaxableItem).second(), is(0.0));
    }

    @Test
    public void itShouldComputeTaxForTaxableImportedItem() {
        final TaxVisitorImpl taxVisitor = new TaxVisitorImpl(10.0, 5.0);
        final TaxableImportedItem taxableImportedItem = mockTaxableImportedItem("bottle of perfume", 10.0);

        assertThat(taxVisitor.visit(taxableImportedItem).second(), is(1.50));
    }


    @Test
    public void itShouldComputeTaxForTaxableImportedItemWithNonZeroDecimal() {
        final TaxVisitorImpl taxVisitor = new TaxVisitorImpl(10.0, 5.0);
        TaxableImportedItem taxableImportedItem = mockTaxableImportedItem("bottle of perfume", 47.50);

        assertThat(taxVisitor.visit(taxableImportedItem).second(), is(7.13));
    }

    @Test
    public void itShouldComputeTaxForTaxableImportedItemButNotAsExpectedInSpecification() {
        final TaxVisitorImpl taxVisitor = new TaxVisitorImpl(10.0, 5.0);
        final TaxableImportedItem taxableImportedItem = mockTaxableImportedItem("bottle of perfume", 47.50);

        assertNotEquals(taxVisitor.visit(taxableImportedItem).second(), is(7.13));
    }


    @Test
    public void itShouldComputeTaxForNonTaxableImportedItem() {
        final TaxVisitorImpl taxVisitor = new TaxVisitorImpl(10.0, 5.0);
        final NonTaxableImportedItem item = mockNonTaxableImportedItem("box of imported chocolates", 11.25);

        assertThat(taxVisitor.visit(item).second(), is(0.56));
    }

    @Test
    public void itShouldComputeTaxForNonTaxableImportedItemNotEqualsToExpectedValueFromSpecification() {
        final TaxVisitorImpl taxVisitor = new TaxVisitorImpl(10.0, 5.0);
        final NonTaxableImportedItem item = mockNonTaxableImportedItem("box of imported chocolates", 11.25);

        assertNotEquals(taxVisitor.visit(item).second(), is(0.60));
    }

    private static NonTaxableImportedItem mockNonTaxableImportedItem(String itemName, double itemPrice) {
        final NonTaxableImportedItem nonTaxableImportedItem = Mockito.mock(NonTaxableImportedItem.class);
        Mockito.when(nonTaxableImportedItem.getName()).thenReturn(itemName);
        Mockito.when(nonTaxableImportedItem.getPrice()).thenReturn(itemPrice);

        return nonTaxableImportedItem;
    }

    private static TaxableItem mockTaxableItem(String itemName, final Double itemPrice) {
        final AtomicReference<TaxableItem> taxableItem = new AtomicReference<TaxableItem>(Mockito.mock(TaxableItem.class));
        Mockito.when(taxableItem.get().getName()).thenReturn(itemName);
        Mockito.when(taxableItem.get().getPrice()).thenReturn(itemPrice);

        return taxableItem.get();
    }

    private static TaxableImportedItem mockTaxableImportedItem(final String itemName, double itemPrice) {
        final AtomicReference<TaxableImportedItem> taxableImportedItem = new AtomicReference<TaxableImportedItem>(Mockito.mock(TaxableImportedItem.class));
        Mockito.when(taxableImportedItem.get().getName()).thenReturn(itemName);
        Mockito.when(taxableImportedItem.get().getPrice()).thenReturn(itemPrice);
        return taxableImportedItem.get();
    }
}
