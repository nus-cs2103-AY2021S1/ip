// Frontend.java
// Copyright (c) 2020, zhiayang, Apache License 2.0.

package ikura;

import java.util.Scanner;
import java.util.Optional;
import java.io.PrintStream;

public class Frontend {

    private final Scanner sc;
    private final String name;
    private final PrintStream out;

    public Frontend(String name) {
        this(name, new Scanner(System.in), System.out);
    }

    public Frontend(String name, Scanner scanner, PrintStream out) {
        this.name   = name;
        this.sc     = scanner;
        this.out    = out;
    }

    public void greet() {
        println("Hello, I'm %s", this.name);
        println("What can I do for you?");
    }

    public Optional<String> readLine() {
        this.out.printf("> ");

        return this.sc.hasNextLine()
            ? Optional.of(this.sc.nextLine())
            : Optional.empty();
    }

    public void println(String fmt, Object... args) {
        this.out.printf(fmt, args);
        this.out.println();
    }

    public void beginLog() {

        println("");
    }

    public void endLog() {

        println("--------------------------------------");
    }
}
