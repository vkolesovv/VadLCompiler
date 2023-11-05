package ru.vadim.vadlpl.compiler.analysis.ast.expressions;

import ru.vadim.vadlpl.compiler.lib.NumberValue;
import ru.vadim.vadlpl.compiler.lib.Value;

public class BinaryExpression implements Expression {
    private Expression first, second;
    private char operation;

    public BinaryExpression(char operator, Expression first, Expression second) {
        this.first = first;
        this.second = second;
        this.operation = operator;
    }

    @Override
    public Value eval() {
        Value firstValue, secondValue;

        firstValue = this.first.eval();
        secondValue = this.second.eval();

        double firstNumber, secondNumber;
        firstNumber = firstValue.asDouble();
        secondNumber = secondValue.asDouble();

        double result = 0;

        switch (this.operation) {
            case '+': result = firstNumber + secondNumber; break;
            case '-': result = firstNumber - secondNumber; break;
            case '*': result = firstNumber * secondNumber; break;
            case '/': result = firstNumber / secondNumber; break;
            case '^': result = Math.pow(firstNumber, secondNumber); break;
            case '√': result = Math.pow(secondNumber, 1/firstNumber); break;
            default: throw new RuntimeException("VD0002: Undefined operation");
        }

        return new NumberValue(result);
    }
}
