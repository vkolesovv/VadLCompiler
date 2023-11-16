package ru.vadim.vadlpl.compiler;

import ru.vadim.vadlpl.compiler.analysis.Lexer;
import ru.vadim.vadlpl.compiler.analysis.ast.Parser;
import ru.vadim.vadlpl.compiler.analysis.ast.statements.Statement;
import ru.vadim.vadlpl.compiler.tokens.Token;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public final class Main {
    public static void main(String[] args) throws IOException {
        String input = new String(Files.readAllBytes(Paths.get("example.vadl")), "UTF-8");

        List<Token> tokens = new Lexer(input).tokenize();

        for (Token token : tokens) {
            System.out.println(token);
        }

        List<Statement> statements = new Parser(tokens).parse();

        for (Statement statement : statements) {
            statement.execute();
        }

//        for (Statement statement : statements) {
//            System.out.println(statement);
//        }

        /*
        FileInputStream fis = new FileInputStream("gaziches.bin");
        ObjectInputStream oin = new ObjectInputStream(fis);

        List<Statement> statements2 = (List<Statement>) oin.readObject();

        for (Statement statement : statements2) {
            statement.execute();
        }

        for (Statement statement : statements2) {
            System.out.println(statement);
        }
        */ // Здесь из байт-кода JVM можно забрать список выражений

        /*
        FileOutputStream stream = new FileOutputStream("gaziches.bin");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(stream);

        objectOutputStream.writeObject(statements);
        objectOutputStream.flush();
        objectOutputStream.close();
        */ // А здесь - записать
    }
}