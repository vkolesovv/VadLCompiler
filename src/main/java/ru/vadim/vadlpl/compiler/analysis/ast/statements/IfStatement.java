package ru.vadim.vadlpl.compiler.analysis.ast.statements;

import ru.vadim.vadlpl.compiler.analysis.ast.expressions.Expression;

public class IfStatement implements Statement {
    private final Expression expression;
    private final Statement ifStatement, elseStatement;

    public IfStatement(Expression expression, Statement ifStatement, Statement elseStatement) {
        this.expression = expression;
        this.ifStatement = ifStatement;
        this.elseStatement = elseStatement;
    }

    @Override
    public void execute() {
        double result = this.expression.eval().asDouble();

        if (result != 0) {
            this.ifStatement.execute();
        } else {
            this.elseStatement.execute();
        }
    }
}
