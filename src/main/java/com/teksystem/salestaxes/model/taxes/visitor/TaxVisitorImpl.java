package com.teksystem.salestaxes.model.taxes.visitor;

import com.teksystem.salestaxes.model.items.*;
import com.teksystem.salestaxes.utils.Pair;

import java.math.BigDecimal;

import static com.teksystem.salestaxes.utils.CustomFormatter.format;
import static com.teksystem.salestaxes.utils.RateCalculator.calculateRate;

public class TaxVisitorImpl implements TaxVisitor {
    private final Double importationRate;
    private final Double basicRate;

    public TaxVisitorImpl(final Double basicRate, final Double importationRate) {
        this.importationRate = importationRate;
        this.basicRate = basicRate;
    }

    @Override
    public Pair<Item, Double> visit(final TaxableItem item) {
        final Double formattedTax = format(calculateRate(item.getPrice(), basicRate));
        return new Pair<Item, Double>(new TaxableItem(item.getName(), item.getPrice() + formattedTax), formattedTax);
    }

    @Override
    public Pair<Item, Double> visit(final TaxableImportedItem item) {
        final BigDecimal calculatedRate = calculateRate(item.getPrice(), importationRate).add(calculateRate(item.getPrice(), basicRate));
        final Double formattedTax = format(calculatedRate);

        return new Pair<Item, Double>(new TaxableImportedItem(item.getName(), item.getPrice() + formattedTax), formattedTax);
    }

    @Override
    public Pair<Item, Double> visit(final NonTaxableItem nonTaxableItem) {
        return new Pair<Item, Double>(nonTaxableItem, 0.0);
    }

    @Override
    public Pair<Item, Double> visit(final NonTaxableImportedItem item) {
        final BigDecimal calculateRate = calculateRate(item.getPrice(), importationRate);
        final Double formattedTax = format(calculateRate);
        return new Pair<Item, Double>(new NonTaxableImportedItem(item.getName(), item.getPrice() + formattedTax), formattedTax);
    }
}
