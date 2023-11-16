package ru.vadim.vadlpl.compiler;

import ru.vadim.vadlpl.compiler.analysis.Lexer;
import ru.vadim.vadlpl.compiler.analysis.ast.Parser;
import ru.vadim.vadlpl.compiler.analysis.ast.statements.Statement;
import ru.vadim.vadlpl.compiler.tokens.Token;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public final class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String path = "example.vadl";

        String mode = "";

        if (args.length > 1) {
            path = args[1];
            mode = "ip";
            if (args.length > 2) {
                mode = args[2];
            }
        }

        Path path1 = Paths.get(path);
        if (Objects.equals(mode, "cp")) {
            String input = new String(Files.readAllBytes(path1), "UTF-8");

            List<Token> tokens = new Lexer(input).tokenize();

            List<Statement> statements = new Parser(tokens).parse();

            FileOutputStream stream = new FileOutputStream(path + ".bin");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(stream);

            objectOutputStream.writeObject(statements);
            objectOutputStream.flush();
            objectOutputStream.close();
        } else if (Objects.equals(mode, "ip")) {
            String input = new String(Files.readAllBytes(path1), "UTF-8");

            List<Token> tokens = new Lexer(input).tokenize();

            List<Statement> statements = new Parser(tokens).parse();

            for (Statement statement : statements) {
                statement.execute();
            }
        } else if (Objects.equals(mode, "rb")) {
            FileInputStream fis = new FileInputStream(path + ".bin");
            ObjectInputStream oin = new ObjectInputStream(fis);

            List<Statement> statements2 = (List<Statement>) oin.readObject();

            for (Statement statement : statements2) {
                statement.execute();
            }
        } else {
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.printf("> ");
                String input = scanner.nextLine();

                List<Token> tokens = new Lexer(input).tokenize();

                List<Statement> statements = new Parser(tokens).parse();
                for (Statement statement : statements) {
                    statement.execute();
                }
            }
        }

        /*
        FileInputStream fis = new FileInputStream("gaziches.bin");
        ObjectInputStream oin = new ObjectInputStream(fis);

        List<Statement> statements2 = (List<Statement>) oin.readObject();

        for (Statement statement : statements2) {
            statement.execute();
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