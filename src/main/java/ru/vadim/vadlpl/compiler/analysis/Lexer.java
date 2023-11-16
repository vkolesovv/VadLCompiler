package ru.vadim.vadlpl.compiler.analysis;

import ru.vadim.vadlpl.compiler.tokens.Token;
import ru.vadim.vadlpl.compiler.tokens.TokenType;

import java.util.ArrayList;
import java.util.List;

public final class Lexer {
    public static final TokenType[] OPERATORS = {
            TokenType.PLUS, TokenType.MINUS, TokenType.STAR, TokenType.SLASH, TokenType.PERCENT, TokenType.POW, TokenType.ROOT_OF_NUMBER, TokenType.SEPARATOR,
            TokenType.LEFT_PAREN, TokenType.RIGHT_PAREN, TokenType.EQ, TokenType.LT, TokenType.GT
    };

    public static final String OPERATOR_CHARS = "+-*/%^√:()=<>";

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
            else if (current == '"') tokenizeString();
            else if (Character.isLetter(current)) tokenizeWord();
            else if (Character.isWhitespace(current)) next();
            else {
                throw new RuntimeException("VD0001: Incorrect symbol");
            }
        }
        return tokens;
    }

    private void tokenizeWord() {
        char current = peek();
        final StringBuilder builder = new StringBuilder();

        while (Character.isLetterOrDigit(current)) {
            builder.append(current);
            current = next();
        }
        switch (builder.toString()) {
            case "print": addToken(TokenType.PRINT); break;
            case "if": addToken(TokenType.IF); break;
            case "else": addToken(TokenType.ELSE); break;
            default:
                addToken(TokenType.WORD, builder.toString());
                break;
        }
    }

    private void tokenizeString() {
        char current = next();
        final StringBuilder builder = new StringBuilder();

        while (current != '"') {
            builder.append(current);
            current = next();
        }

        next();
        addToken(TokenType.STRING, builder.toString());
    }

    private void tokenizeOperator() {
        this.addToken(OPERATORS[OPERATOR_CHARS.indexOf(peek())]);
        next();
    }

    private void tokenizeNumber() {
        StringBuilder builder = new StringBuilder();

        char current = peek();

        while (true) {
            if (current == '.') {
                if (builder.indexOf(".") != -1) throw new RuntimeException("VD0004: Unexpected dot");
            }
            else if (!Character.isDigit(current)) {
                break;
            }
            builder.append(current);
            current = next();
        }
        addToken(TokenType.NUMBER, builder.toString());
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
