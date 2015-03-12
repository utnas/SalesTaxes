package com.teksystem.salestaxes.utils;

import java.math.MathContext;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

public class CustomDecimalFormatter {
    public final static MathContext MATH_CONTEXT = new MathContext(3, RoundingMode.HALF_UP);
    public final static int MINIMUM_INTEGER_DIGITS = 1;
    public final static int MAXIMUM_INTEGER_DIGITS = 9;
    public final static int MINIMUM_FRACTION_DIGITS = 2;
    public final static int MAXIMUM_FRACTION_DIGITS = 2;

    public static String format(final Number calculatedRate) {
        return applyCustomDecimalFormat(calculatedRate);
    }

    private static String applyCustomDecimalFormat(final Number number) {
        final NumberFormat decimalFormat = NumberFormat.getInstance(Locale.US);
        decimalFormat.setMinimumIntegerDigits(MINIMUM_INTEGER_DIGITS);
        decimalFormat.setMaximumIntegerDigits(MAXIMUM_INTEGER_DIGITS);
        decimalFormat.setMinimumFractionDigits(MINIMUM_FRACTION_DIGITS);
        decimalFormat.setMaximumFractionDigits(MAXIMUM_FRACTION_DIGITS);
        return decimalFormat.format(number);
    }
}
