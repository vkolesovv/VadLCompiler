package ru.vadim.vadlpl.compiler.lib;

public class NumberValue implements Value {
    private final double rawValue;

    public NumberValue(double value) {
        this.rawValue = value;
    }

    @Override
    public double asDouble() {
        return rawValue;
    }

    @Override
    public String asString() {
        return Double.toString(this.rawValue);
    }

    @Override
    public boolean asBool() {
        return this.rawValue > 0;
    }
}
