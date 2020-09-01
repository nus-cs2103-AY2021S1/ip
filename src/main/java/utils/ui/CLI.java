package utils.ui;

import utils.ui.UI;

import java.util.Scanner;

/**
 * The CLI Handler. Handles User Input and Output
 * through the Command Line Interface.
 */
public class CLI implements UI {

    private final Scanner scanner;

    public CLI() {
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
