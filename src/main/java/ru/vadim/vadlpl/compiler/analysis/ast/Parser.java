package ru.vadim.vadlpl.compiler.analysis.ast;

import ru.vadim.vadlpl.compiler.analysis.ast.expressions.*;
import ru.vadim.vadlpl.compiler.analysis.ast.statements.AssignmentStatement;
import ru.vadim.vadlpl.compiler.analysis.ast.statements.Statement;
import ru.vadim.vadlpl.compiler.tokens.Token;
import ru.vadim.vadlpl.compiler.tokens.TokenType;

import java.util.ArrayList;
import java.util.List;

public final class Parser {
    private static Token EOF = new Token(TokenType.EOF);

    private final List<Token> tokens;
    private final int size;

    private int position;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
        this.size = tokens.size();
    }

    // Parsing

    public List<Expression> parse() {
        final List<Expression> result = new ArrayList<>();

        while (!match(TokenType.EOF)) {
            result.add(expression());
        }
        return result;
    }

    // Statements

    private Statement statement() {
        return this.assignmentStatement();
    }

    private Statement assignmentStatement() {
        Token currentToken = this.get();
        if (match(TokenType.WORD) && this.get().getType() == TokenType.ASSIGN) {
            String variable = currentToken.getText();
            consume(TokenType.ASSIGN);
            return new AssignmentStatement(variable, expression());
        }
        throw new RuntimeException("VD0005: Unknown statement");
    }

    // Expressions

    private Expression expression() {
        return additive();
    }

    private Expression additive() {
        Expression expression = multiplicative();

        while (true) {
            if (this.match(TokenType.PLUS)) {
                return new BinaryExpression('+', expression, multiplicative());
            }
            if (this.match(TokenType.MINUS)) {
                return new BinaryExpression('-', expression, multiplicative());
            }
            break;
        }
        return expression;
    }

    private Expression multiplicative() {
        Expression expression = this.pow();

        while (true) {
            if (this.match(TokenType.STAR)) {
                return new BinaryExpression('*', expression, pow());
            }
            if (this.match(TokenType.SLASH)) {
                return new BinaryExpression('/', expression, pow());
            }
            break;
        }
        return expression;
    }

    private Expression pow() {
        Expression expression = this.unary();

        while (true) {
            if (this.match(TokenType.POW)) {
                return new BinaryExpression('^', expression, this.unary());
            }
            if (this.match(TokenType.ROOT_OF_NUMBER)) {
                return new BinaryExpression('√', expression, this.unary());
            }
            break;
        }
        return expression;
    }

    private Expression unary() {
        if (this.match(TokenType.MINUS)) {
            return new UnaryExpression('-', this.primary());
        }
        return this.primary();
    }

    private Expression primary() {
        Token current = this.get();

        if (this.match(TokenType.NUMBER)) {
            return new NumberExpression(Double.parseDouble(current.getText()));
        }
        if (this.match(TokenType.LEFT_PAREN)) {
            Expression expression = this.expression();
            match(TokenType.RIGHT_PAREN);
            return expression;
        }
        if (this.match(TokenType.WORD)) {
            return new VariableExpression(current.getText());
        }

        throw new RuntimeException("VD0003: Undefined token type");
    }

    // Match methods

    private boolean match(TokenType type) {
        final Token current = get();
        if (type != current.getType()) return false;
        this.position++;
        return true;
    }

    private Token consume(TokenType type) {
        Token current = this.get();
        if (type != current.getType()) throw new RuntimeException("VD0004: Expected " + type + " token. Got " + current.getType());
        return current;
    }

    // Other token methods

    private Token get() {
        return this.get(0);
    }

    private Token get(int relative) {
        final int realPosition = this.position + relative;
        if (realPosition >= size) return EOF;
        return this.tokens.get(realPosition);
    }
}
