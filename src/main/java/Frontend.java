// Frontend.java
// Copyright (c) 2020, zhiayang, Apache License 2.0.

import java.util.Scanner;
import java.util.Optional;

public class Frontend {

    private final Scanner sc;
    private final String name;

    public Frontend(String name) {
        this.name = name;
        this.sc = new Scanner(System.in);
    }

    public void greet() {
        println("Hello, I'm %s", this.name);
        println("What can I do for you?");
    }

    public Optional<String> readLine() {
        System.out.printf("> ");

        return this.sc.hasNextLine()
            ? Optional.of(this.sc.nextLine())
            : Optional.empty();
    }

    public void println(String fmt, Object... args) {
        System.out.printf(fmt, args);
        System.out.println();
    }

    public void beginLog() {

        println("");
    }

    public void endLog() {

        println("--------------------------------------");
    }
}
