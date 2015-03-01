package com.teksystem.salestaxes.utils;

public final class Pair<F, S> {
    private final F first;
    private final S second;

    public Pair(final F first, final S second) {
        this.first = first;
        this.second = second;
    }

    public final F first() {
        return first;
    }

    public final S second() {
        return second;
    }
}
