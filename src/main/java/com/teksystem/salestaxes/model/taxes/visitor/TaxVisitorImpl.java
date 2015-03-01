package com.teksystem.salestaxes.model.taxes.visitor;

import com.sun.tools.javac.util.Pair;
import com.teksystem.salestaxes.model.items.*;
import com.teksystem.salestaxes.utils.CustomFormatter;

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
    public Pair<Item, Double> visit(final TaxableItem taxableItem) {
        final BigDecimal calculatedRate = calculateRate(taxableItem.getPrice(), basicRate);
        return new Pair<Item, Double>(taxableItem, CustomFormatter.format(calculatedRate));
    }

    @Override
    public Pair<Item, Double> visit(final TaxableImportedItem taxableItem) {
        final BigDecimal calculatedRate = calculateRate(taxableItem.getPrice(), importationRate).add(calculateRate(taxableItem.getPrice(), basicRate));
        return new Pair<Item, Double>(taxableItem, CustomFormatter.format(calculatedRate));
    }

    @Override
    public Pair<Item, Double> visit(final NonTaxableItem nonTaxableItem) {
        return new Pair<Item, Double>(nonTaxableItem, 0.0);
    }

    @Override
    public Pair<Item, Double> visit(final NonTaxableImportedItem nonTaxableImportedItem) {
        final BigDecimal calculateRate = calculateRate(nonTaxableImportedItem.getPrice(), importationRate);
        return new Pair<Item, Double>(nonTaxableImportedItem, CustomFormatter.format(calculateRate));
    }
}
