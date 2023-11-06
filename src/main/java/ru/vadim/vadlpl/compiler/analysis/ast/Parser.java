package ru.vadim.vadlpl.compiler.analysis.ast;

import ru.vadim.vadlpl.compiler.analysis.ast.expressions.BinaryExpression;
import ru.vadim.vadlpl.compiler.analysis.ast.expressions.Expression;
import ru.vadim.vadlpl.compiler.analysis.ast.expressions.NumberExpression;
import ru.vadim.vadlpl.compiler.analysis.ast.expressions.UnaryExpression;
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

    private Expression expression() {
        return additive();
    }

    private Expression additive() {
        Expression expression = multiplicative();

        while (true) {
            if (match(TokenType.PLUS)) {
                return new BinaryExpression('+', expression, multiplicative());
            }
            if (match(TokenType.MINUS)) {
                return new BinaryExpression('-', expression, multiplicative());
            }
            break;
        }
        return expression;
    }

    private Expression multiplicative() {
        Expression expression = pows();

        while (true) {
            if (match(TokenType.STAR)) {
                return new BinaryExpression('*', expression, pows());
            }
            if (match(TokenType.SLASH)) {
                return new BinaryExpression('/', expression, pows());
            }
            break;
        }
        return expression;
    }

    private Expression pows() {
        Expression expression = unary();

        while (true) {
            if (match(TokenType.POW)) {
                return new BinaryExpression('^', expression, unary());
            }
            if (match(TokenType.ROOT_OF_NUMBER)) {
                return new BinaryExpression('√', expression, unary());
            }
            break;
        }
        return expression;
    }

    private Expression unary() {
        if (match(TokenType.MINUS)) {
            return new UnaryExpression('-', primary());
        }
        return primary();
    }

    private Expression primary() {
        Token current = this.get();

        if (match(TokenType.NUMBER)) {
            return new NumberExpression(Double.parseDouble(current.getText()));
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

    private void consume() {
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
