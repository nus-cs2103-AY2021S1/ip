// Frontend.java
// Copyright (c) 2020, zhiayang, Apache License 2.0.

package ikura;

import java.util.Scanner;
import java.util.Optional;
import java.io.PrintStream;

/**
 * A class to abstract the user input and output from the rest of the application. An output stream
 * in the form of a PrintStream and an input stream in the form of a Scanner can be specified.
 */
public class Frontend {

    private final Scanner sc;
    private final String name;
    private final PrintStream out;

    /**
     * Constructs a new Frontend with the given name, using stdio (System.out and System.in) as
     * the input and output streams respectively.
     *
     * @param name the name of the bot.
     */
    public Frontend(String name) {
        this(name, new Scanner(System.in), System.out);
    }

    /**
     * Constructs a new Frontend with the given name, using the specified input and output streams.
     *
     * @param name    the name of the bot.
     * @param scanner the Scanner to use as the input stream.
     * @param out     the PrintStream to use as the output stream.
     */
    public Frontend(String name, Scanner scanner, PrintStream out) {
        this.name   = name;
        this.sc     = scanner;
        this.out    = out;
    }

    /**
     * Prints a greeting message containing the name of the bot.
     */
    public void greet() {
        println("Hello, I'm %s", this.name);
        println("What can I do for you?");
    }

    /**
     * Reads a line of user input from this Frontend's input stream.
     *
     * @return the input line as an Optional<String>, or an empty optional on EOF.
     */
    public Optional<String> readLine() {
        this.out.printf("> ");

        return this.sc.hasNextLine()
            ? Optional.of(this.sc.nextLine())
            : Optional.empty();
    }

    /**
     * Prints a line of output to this Frontend's output stream. Has an identical interface to
     * System.out.printf().
     *
     * @param fmt  the format string.
     * @param args the list of objects.
     */
    public void println(String fmt, Object... args) {
        this.out.printf(fmt, args);
        this.out.println();
    }

    /**
     * Begins a "session", ie. the output to a given command. Used to delimit output between consecutive
     * commands.
     */
    public void beginLog() {

        println("");
    }

    /**
     * Ends a "session", ie. the output to a given command. Used to delimit output between consecutive
     * commands. In this case, it prints a line separator.
     */
    public void endLog() {

        println("--------------------------------------");
    }
}
