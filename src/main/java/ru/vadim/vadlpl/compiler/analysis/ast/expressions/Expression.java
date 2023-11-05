package ru.vadim.vadlpl.compiler.analysis.ast.expressions;

import ru.vadim.vadlpl.compiler.lib.Value;

import java.io.Serializable;

public interface Expression extends Serializable {
    Value eval();
}
