package ru.vadim.vadlpl.compiler.analysis.ast.statements;

import ru.vadim.vadlpl.compiler.analysis.ast.expressions.Expression;
import ru.vadim.vadlpl.compiler.lib.Variables;
import ru.vadim.vadlpl.compiler.lib.values.Value;

public class AssignmentStatement implements Statement {
    private final String variable;
    private final Expression expression;

    public AssignmentStatement(String variable, Expression expression) {
        this.variable = variable;
        this.expression = expression;
    }

    @Override
    public void execute() {
        final Value result = this.expression.eval();
        Variables.setVariable(this.variable, result);
    }

    @Override
    public String toString() {
        return String.format("%s = %s", this.variable, this.expression.eval().asString());
    }
}
