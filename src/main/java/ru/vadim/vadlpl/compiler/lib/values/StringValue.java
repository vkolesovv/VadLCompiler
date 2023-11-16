package ru.vadim.vadlpl.compiler.lib.values;

public class StringValue implements Value {
    private final String rawValue;

    public StringValue(String value) {
        this.rawValue = value;
    }

    @Override
    public double asDouble() {
        throw new RuntimeException("VD0006: Cannot get number from string");
    }

    @Override
    public String asString() {
        return this.rawValue;
    }

    @Override
    public boolean asBool() {
        throw new RuntimeException("VD0007: Cannot get boolean from string");
    }
}
