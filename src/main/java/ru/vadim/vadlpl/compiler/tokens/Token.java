package ru.vadim.vadlpl.compiler.tokens;

public class Token {
    private final TokenType type;
    private final String text;

    public Token(TokenType type, String text) {
        this.type = type;
        this.text = text;
    }

    public Token(TokenType type) {
        this.type = type;
        this.text = "";
    }

    public TokenType getType() {
        return this.type;
    }

    public String getText() {
        return this.text;
    }

    @Override
    public String toString() {
        return String.format("%s { %s }", this.type, this.text);
    }
}
