package ru.vadim.vadlpl.compiler.analysis.ast.statements;

import java.io.Serializable;

public interface Statement extends Serializable {
    void execute();
}
