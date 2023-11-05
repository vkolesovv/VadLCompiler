package ru.vadim.vadlpl.compiler.analysis.ast.expressions;

import ru.vadim.vadlpl.compiler.lib.NumberValue;
import ru.vadim.vadlpl.compiler.lib.Value;

public class NumberExpression implements Expression {
    private final Value value;

    public NumberExpression(double value) {
        this.value = new NumberValue(value);
    }

    @Override
    public Value eval() {
        return this.value;
    }
}
