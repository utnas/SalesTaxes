package com.teksystem.salestaxes.utils;

import java.math.MathContext;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

public class CustomDecimalFormatter {
    public final static MathContext MATH_CONTEXT = new MathContext(3, RoundingMode.HALF_UP);

    public static String format(final Number calculatedRate) {
        return applyCustomDecimalFormat(calculatedRate);
    }

    private static String applyCustomDecimalFormat(final Number number) {
        final NumberFormat decimalFormat = NumberFormat.getInstance(Locale.US);
        decimalFormat.setMinimumIntegerDigits(1);
        decimalFormat.setMaximumIntegerDigits(9);
        decimalFormat.setMinimumFractionDigits(2);
        decimalFormat.setMaximumFractionDigits(2);
        return decimalFormat.format(number);
    }

}
