package ru.vadim.vadlpl.compiler.tokens;

public enum TokenType {
    NUMBER, STRING, BOOL, CHAR,

    WORD,

    PLUS, MINUS, STAR, SLASH, PERCENT, POW, ROOT_OF_NUMBER,

    LEFT_PAREN,
    RIGHT_PAREN,

    SEPARATOR, // :
    ASSIGN, // =

    EOF
}
