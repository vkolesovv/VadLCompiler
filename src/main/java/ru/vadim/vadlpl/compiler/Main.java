package ru.vadim.vadlpl.compiler;

import ru.vadim.vadlpl.compiler.analysis.Lexer;
import ru.vadim.vadlpl.compiler.analysis.ast.Parser;
import ru.vadim.vadlpl.compiler.analysis.ast.expressions.Expression;
import ru.vadim.vadlpl.compiler.tokens.Token;

import java.util.List;

public final class Main {
    public static void main(String[] args) {
        String input = "1";

        List<Token> tokens = new Lexer(input).tokenize();

        for (Token token : tokens) {
            System.out.println(token);
        }

        List<Expression> expressions = new Parser(tokens).parse();

        for (Expression expression : expressions) {
            System.out.println(expression.eval().asDouble());
        }
    }
}