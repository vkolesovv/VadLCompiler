package ru.vadim.vadlpl.compiler;

import ru.vadim.vadlpl.compiler.analysis.Lexer;
import ru.vadim.vadlpl.compiler.tokens.Token;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String input = "2+2:";

        List<Token> tokens = new Lexer(input).tokenize();

        for (Token token : tokens) {
            System.out.println(token);
        }
    }
}