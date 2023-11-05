package ru.vadim.vadlpl.compiler.lib;

import java.io.Serializable;

public interface Value extends Serializable {
    double asDouble();
    String asString();

    boolean asBool();
}
