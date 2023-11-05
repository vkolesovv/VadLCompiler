package ru.vadim.vadlpl.compiler.analysis;

import ru.vadim.vadlpl.compiler.tokens.Token;
import ru.vadim.vadlpl.compiler.tokens.TokenType;

import java.util.ArrayList;
import java.util.List;

public class Lexer {
    public static final TokenType[] OPERATORS = {
            TokenType.PLUS, TokenType.MINUS, TokenType.STAR, TokenType.SLASH, TokenType.PERCENT, TokenType.POW, TokenType.ROOT_OF_NUMBER, TokenType.SEPARATOR
    };

    public static final String OPERATOR_CHARS = "+-*/%^<:";

    private final String text;
    private final int length;

    private final List<Token> tokens = new ArrayList<>();

    private int position;

    public Lexer(String text) {
        this.text = text;
        this.length = text.length();
    }

    // Tokenizing

    public List<Token> tokenize() {
        while (this.position < this.length) {
            final char current = this.peek();
            if (Character.isDigit(current)) tokenizeNumber();
            else if (OPERATOR_CHARS.indexOf(current) != -1) tokenizeOperator();
            else if (Character.isWhitespace(current)) next();
            else {
                throw new RuntimeException("VD0001: Incorrect symbol");
            }
        }
        return tokens;
    }

    private void tokenizeOperator() {
        this.addToken(OPERATORS[OPERATOR_CHARS.indexOf(peek())]);
        next();
    }

    private void tokenizeNumber() {
        StringBuilder builder = new StringBuilder();

        char current = peek();

        while (Character.isDigit(current)) {
            builder.append(current);
            current = next();
        }
        addToken(TokenType.INT, builder.toString());
    }

    // Adding tokens

    private void addToken(TokenType type, String text) {
        tokens.add(new Token(type, text));
    }
    private void addToken(TokenType type) {
        tokens.add(new Token(type));
    }

    // Position methods

    private char peek(int relative) {
        int realPosition = this.position + relative;
        if (realPosition >= this.length) return '\0';
        return this.text.charAt(realPosition);
    }
    private char peek() {
        return this.peek(0);
    }

    private char next() {
        this.position++;
        return peek();
    }
}
