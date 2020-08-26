package Duke.main;

import java.util.Scanner;

/**
 * This class is to interact with the user.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Initialize a Ui.
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
     * This method eliminates the spaces
     * around the user input.
     *
     * @return A string with spaces around it.
     */
    public String conciseInput() {
        String extract = readFromClient();
        return new Formating<>(extract).shorten().getContent();
    }

    /**
     * This method prints the beginning greeting that
     * a user will receive at the start of Duke operation.
     */
    public void greet() {
        System.out.println(
                new Formating<>(
                        new Response(Status.GREET.toString()
                        )
                )
        );
    }

    /**
     * This method prints the ending words that
     * a user will receive after he inputs "bye"
     */
    public void exit() {
        scanner.close();
        System.out.println(
                new Formating<>(
                        new Response(Status.BYE.toString()
                        )
                )
        );
    }
}
