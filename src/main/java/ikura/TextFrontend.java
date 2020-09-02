// TextFrontend.java
// Copyright (c) 2020, zhiayang, Apache License 2.0.

package ikura;

import java.util.Scanner;
import java.util.Optional;
import java.io.PrintStream;

/**
 * A class to abstract the user input and output from the rest of the application. An output stream
 * in the form of a PrintStream and an input stream in the form of a Scanner can be specified.
 */
public class TextFrontend extends Frontend {

    private final Scanner sc;
    private final PrintStream out;

    /**
     * Constructs a new TextFrontend with the given name, using stdio (System.out and System.in) as
     * the input and output streams respectively.
     *
     * @param name the name of the bot.
     */
    public TextFrontend(TaskList tasks) {
        this(tasks, new Scanner(System.in), System.out);
    }

    /**
     * Constructs a new TextFrontend with the given name, using the specified input and output streams.
     *
     * @param name    the name of the bot.
     * @param scanner the Scanner to use as the input stream.
     * @param out     the PrintStream to use as the output stream.
     */
    public TextFrontend(TaskList tasks, Scanner scanner, PrintStream out) {
        super(tasks);

        this.sc     = scanner;
        this.out    = out;
    }

    private Optional<String> readLine() {
        this.out.printf("> ");

        return this.sc.hasNextLine()
            ? Optional.of(this.sc.nextLine())
            : Optional.empty();
    }


    @Override
    public void run() {
        println("Hello, I'm %s", Frontend.BOT_NAME);
        println("What can I do for you?");

        while (this.readLine().map(bot::processCommand).orElse(false))
            ;
    }

    @Override
    public void println(String fmt, Object... args) {
        this.out.printf(fmt, args);
        this.out.println();
    }

    @Override
    public void beginLog() {
        println("");
    }

    @Override
    public void endLog() {
        println("--------------------------------------");
    }
}
