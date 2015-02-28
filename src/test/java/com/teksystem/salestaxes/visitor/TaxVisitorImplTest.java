package com.teksystem.salestaxes.visitor;

import com.sun.tools.javac.util.Pair;
import com.teksystem.salestaxes.model.*;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;

public class TaxVisitorImplTest {

    @Test
    public void itShouldReturnSameItemAfterComputingForTaxTaxableItem() {
        final TaxableItem taxableItem = mockTaxableItem("music CD", 14.99);
        final Pair<Item, Double> itemTax = new TaxVisitorImpl(10.0, 5.0).visit(taxableItem);

        assertThat(itemTax.fst.getName(), is(taxableItem.getName()));
        assertThat(itemTax.fst.getPrice(), is(taxableItem.getPrice()));
    }


    @Test
    public void itShouldComputeTaxForTaxableItem() {
        final TaxableItem taxableItem = mockTaxableItem("music CD", 14.99);
        final TaxVisitorImpl taxVisitor = new TaxVisitorImpl(10.0, 5.0);

        assertThat(taxVisitor.visit(taxableItem).snd, is(1.50));
    }

    @Test
    public void itShouldComputeTaxForNoneTaxableItem() {
        final TaxVisitorImpl taxVisitor = new TaxVisitorImpl(10.0, 5.0);

        final NoneTaxableItem noneTaxableItem = Mockito.mock(NoneTaxableItem.class);
        Mockito.when(noneTaxableItem.getName()).thenReturn("book");
        Mockito.when(noneTaxableItem.getPrice()).thenReturn(12.49);

        assertThat(taxVisitor.visit(noneTaxableItem).snd, is(0.0));
    }

    @Test
    public void itShouldComputeTaxForTaxableImportedItem() {
        final TaxVisitorImpl taxVisitor = new TaxVisitorImpl(10.0, 5.0);
        final TaxableImportedItem taxableImportedItem = mockTaxableImportedItem("bottle of perfume", 10.0);

        assertThat(taxVisitor.visit(taxableImportedItem).snd, is(1.50));
    }


    @Test
    public void itShouldComputeTaxForTaxableImportedItemWithNoneZeroDecimal() {
        final TaxVisitorImpl taxVisitor = new TaxVisitorImpl(10.0, 5.0);
        TaxableImportedItem taxableImportedItem = mockTaxableImportedItem("bottle of perfume", 47.50);

        assertThat(taxVisitor.visit(taxableImportedItem).snd, is(7.13));
    }

    @Test
    public void itShouldComputeTaxForTaxableImportedItemButNotAsExpectedInSpecification() {
        final TaxVisitorImpl taxVisitor = new TaxVisitorImpl(10.0, 5.0);
        final TaxableImportedItem taxableImportedItem = mockTaxableImportedItem("bottle of perfume", 47.50);

        assertNotEquals(taxVisitor.visit(taxableImportedItem).snd, is(7.13));
    }


    @Test
    public void itShouldComputeTaxForNoneTaxableImportedItem() {
        final TaxVisitorImpl taxVisitor = new TaxVisitorImpl(10.0, 5.0);
        final NoneTaxableImportedItem item = mockNoneTaxableImportedItem("box of imported chocolates", 11.25);

        assertThat(taxVisitor.visit(item).snd, is(0.56));
    }

    @Test
    public void itShouldComputeTaxForNoneTaxableImportedItemNotEqualsToExpectedValueFromSpecification() {
        final TaxVisitorImpl taxVisitor = new TaxVisitorImpl(10.0, 5.0);
        final NoneTaxableImportedItem item = mockNoneTaxableImportedItem("box of imported chocolates", 11.25);

        assertNotEquals(taxVisitor.visit(item).snd, is(0.60));
    }

    private static NoneTaxableImportedItem mockNoneTaxableImportedItem(String itemName, double itemPrice) {
        final NoneTaxableImportedItem noneTaxableImportedItem = Mockito.mock(NoneTaxableImportedItem.class);
        Mockito.when(noneTaxableImportedItem.getName()).thenReturn(itemName);
        Mockito.when(noneTaxableImportedItem.getPrice()).thenReturn(itemPrice);

        return noneTaxableImportedItem;
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
