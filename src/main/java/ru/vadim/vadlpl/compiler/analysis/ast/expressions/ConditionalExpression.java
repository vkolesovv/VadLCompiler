package ru.vadim.vadlpl.compiler.analysis.ast.expressions;

import ru.vadim.vadlpl.compiler.lib.values.NumberValue;
import ru.vadim.vadlpl.compiler.lib.values.StringValue;
import ru.vadim.vadlpl.compiler.lib.values.Value;

public class ConditionalExpression implements Expression {
    private final Expression firstExpression, secondExpression;
    private final char operation;

    public ConditionalExpression(Expression firstExpression, Expression secondExpression, char operation) {
        this.firstExpression = firstExpression;
        this.secondExpression = secondExpression;
        this.operation = operation;
    }

    @Override
    public Value eval() {
        final Value firstValue, secondValue;
        firstValue = this.firstExpression.eval();
        secondValue = this.secondExpression.eval();

        final double firstNumber = firstValue.asDouble();
        final double secondNumber = secondValue.asDouble();

        switch (this.operation) {
            case '>': return new NumberValue(firstNumber > secondNumber);
            case '<': return new NumberValue(firstNumber < secondNumber);
            case '=':
            default:
                return new NumberValue(firstNumber == secondNumber);
        }
    }
}
