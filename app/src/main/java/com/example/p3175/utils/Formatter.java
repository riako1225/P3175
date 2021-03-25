package com.example.p3175.utils;

import java.math.BigDecimal;

public class Formatter {
    /**
     * Format a Big Decimal to String for display
     */
    public static String formatBigDecimal(BigDecimal n) {
        return n.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString();
    }
}
