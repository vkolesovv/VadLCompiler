package ru.vadim.vadlpl.compiler.analysis.ast.expressions;

import ru.vadim.vadlpl.compiler.lib.values.NumberValue;
import ru.vadim.vadlpl.compiler.lib.values.StringValue;
import ru.vadim.vadlpl.compiler.lib.values.Value;

public class ValueExpression implements Expression {
    private final Value value;

    public ValueExpression(double value) {
        this.value = new NumberValue(value);
    }
    public ValueExpression(String value) {
        this.value = new StringValue(value);
    }

    @Override
    public Value eval() {
        return this.value;
    }
}
