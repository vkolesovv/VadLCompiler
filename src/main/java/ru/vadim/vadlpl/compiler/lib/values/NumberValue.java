package ru.vadim.vadlpl.compiler.lib.values;

public class NumberValue implements Value {
    private final double rawValue;

    public NumberValue(double value) {
        this.rawValue = value;
    }
    public NumberValue(boolean value) {
        this.rawValue = value ? 1 : 0;
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
