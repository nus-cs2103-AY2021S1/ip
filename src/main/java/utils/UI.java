package utils;

import java.util.Scanner;

/**
 * The UI Handler. Handles User Input and Output
 * through the Command Line Interface.
 */
public class UI {

    private final Scanner scanner;

    public UI() {
        scanner = new Scanner(System.in);
    }

    /**
     * Prints a given input as a <code>string</code> with indentation
     * and decoration.
     *
     * @param object the object to print
     */
    public void print(Object object) {
        String lineDecoration = "\t" + "_".repeat(60);
        System.out.println(lineDecoration);
        System.out.println("\t" + object);
        System.out.println(lineDecoration);
    }

    /**
     * Read an entire line as a <code>string</code> from the CLI.
     *
     * @return the string read
     */
    public String read() {
        return scanner.nextLine();
    }
}
