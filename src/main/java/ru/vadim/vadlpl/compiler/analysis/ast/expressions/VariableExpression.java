package ru.vadim.vadlpl.compiler.analysis.ast.expressions;

import ru.vadim.vadlpl.compiler.lib.Variables;
import ru.vadim.vadlpl.compiler.lib.values.Value;

public class VariableExpression implements Expression {
    private final String name;

    public VariableExpression(String name) {
        this.name = name;
    }

    @Override
    public Value eval() {
        return Variables.getVariable(this.name);
    }
}
