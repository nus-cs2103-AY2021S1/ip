package main.java.duke.main;

import java.util.Scanner;

/**
 * This class is to interact with the user.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Initializes a Ui.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Returns a string that is equal to
     * the user input.
     *
     * @return The above described string.
     */
    public String readFromClient() {
        return scanner.nextLine();
    }

    /**
     * Eliminates the spaces
     * around the user input.
     *
     * @return A string with spaces around it.
     */
    public String conciseInput() {
        String extract = readFromClient();
        return new Format<>(extract).shorten().getContent();
    }

    /**
     * Prints the beginning greeting that
     * a user will receive at the start of Duke operation.
     */
    public void greet() {
        System.out.println(
                new Format<>(
                        new Response(Statement.GREET.toString()
                        )
                )
        );
    }

    /**
     * Prints the ending words that
     * a user will receive after he inputs "bye"
     */
    public void exit() {
        scanner.close();
        System.out.println(
                new Format<>(
                        new Response(Statement.BYE.toString()
                        )
                )
        );
    }
}
