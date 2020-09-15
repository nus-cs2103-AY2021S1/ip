package ekud.utils.ui;

import java.util.Scanner;

/**
 * The CLI Handler. Handles User Input and Output
 * through the Command Line Interface.
 */
public class Cli implements Ui {

    private final Scanner scanner;

    public Cli() {
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
     * Prints a given list of strings as a <code>string</code> with
     * indentation and decoration.
     *
     * @param strings the list of strings to print
     */
    public void print(String... strings) {
        String lineDecoration = "\t" + "_".repeat(60);
        System.out.println(lineDecoration);
        for (String string : strings) {
            System.out.println("\t" + string);
        }
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
