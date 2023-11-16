package ru.vadim.vadlpl.compiler.analysis.ast.statements;

import ru.vadim.vadlpl.compiler.analysis.ast.expressions.Expression;

public class PrintStatement implements Statement {
    private final Expression expression;

    public PrintStatement(Expression expression) {
        this.expression = expression;
    }

    @Override
    public void execute() {
        System.out.println(expression.eval().asString());
    }
}
