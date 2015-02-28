package com.teksystem.salestaxes.units.visitor;

import com.sun.tools.javac.util.Pair;
import com.teksystem.salestaxes.units.model.*;

import java.math.BigDecimal;

import static com.teksystem.salestaxes.units.utils.CustomFormatter.format;
import static com.teksystem.salestaxes.units.utils.RateCalculator.calculateRate;

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
        return new Pair<Item, Double>(taxableItem, format(calculatedRate));
    }

    @Override
    public Pair<Item, Double> visit(final TaxableImportedItem taxableItem) {
        final BigDecimal calculatedRate = calculateRate(taxableItem.getPrice(), importationRate).add(calculateRate(taxableItem.getPrice(), basicRate));
        return new Pair<Item, Double>(taxableItem, format(calculatedRate));
    }

    @Override
    public Pair<Item, Double> visit(final NoneTaxableItem nonetaxableItem) {
        return new Pair<Item, Double>(nonetaxableItem, 0.0);
    }

    @Override
    public Pair<Item, Double> visit(final NoneTaxableImportedItem noneTaxableImportedItem) {
        final BigDecimal calculateRate = calculateRate(noneTaxableImportedItem.getPrice(), importationRate);
        return new Pair<Item, Double>(noneTaxableImportedItem, format(calculateRate));
    }
}
