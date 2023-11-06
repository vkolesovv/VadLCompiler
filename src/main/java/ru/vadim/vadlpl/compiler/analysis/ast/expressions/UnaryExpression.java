package ru.vadim.vadlpl.compiler.analysis.ast.expressions;

import ru.vadim.vadlpl.compiler.lib.NumberValue;
import ru.vadim.vadlpl.compiler.lib.Value;

public class UnaryExpression implements Expression {
    private final char operator;
    private final Expression expression;

    public UnaryExpression(char operator, Expression expression) {
        this.operator = operator;
        this.expression = expression;
    }

    @Override
    public Value eval() {
        final double doubleValue = this.expression.eval().asDouble();

        double result;

        switch (this.operator) {
            case '-': result = -doubleValue; break;
            case '+':
            default:
                result = doubleValue;
        }
        return new NumberValue(result);
    }
}
