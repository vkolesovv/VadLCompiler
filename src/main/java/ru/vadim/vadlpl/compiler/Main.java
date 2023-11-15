package ru.vadim.vadlpl.compiler;

import ru.vadim.vadlpl.compiler.analysis.Lexer;
import ru.vadim.vadlpl.compiler.analysis.ast.Parser;
import ru.vadim.vadlpl.compiler.analysis.ast.expressions.Expression;
import ru.vadim.vadlpl.compiler.tokens.Token;

import java.io.*;
import java.util.List;

public final class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String input = "PI + 2\n2 + 3";

        List<Token> tokens = new Lexer(input).tokenize();

        for (Token token : tokens) {
            System.out.println(token);
        }

        List<Expression> expressions = new Parser(tokens).parse();

        /*
        FileInputStream fis = new FileInputStream("gaziches.bin");
        ObjectInputStream oin = new ObjectInputStream(fis);

        List<Expression> expressionsOIS = (List<Expression>) oin.readObject();
        for (Expression expression : expressionsOIS) {
            System.out.println(expression.eval().asString());
        }
         */ // Здесь из байт-кода JVM можно забрать список выражений

        /*
        FileOutputStream stream = new FileOutputStream("gaziches.bin");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(stream);

        objectOutputStream.writeObject(expressions);
        objectOutputStream.flush();
        objectOutputStream.close();
         */ // А здесь - записать
    }
}