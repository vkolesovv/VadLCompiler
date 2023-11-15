package ru.vadim.vadlpl.compiler.lib.values;

import java.io.Serializable;

public interface Value extends Serializable {
    double asDouble();
    String asString();

    boolean asBool();
}
