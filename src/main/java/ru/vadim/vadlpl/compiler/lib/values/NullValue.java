package ru.vadim.vadlpl.compiler.lib.values;

public class NullValue implements Value {
    @Override
    public double asDouble() {
        return 0;
    }

    @Override
    public String asString() {
        return "null";
    }

    @Override
    public boolean asBool() {
        return false;
    }
}
