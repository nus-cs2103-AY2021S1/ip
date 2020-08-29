package taskbot.ui;

import java.util.Scanner;

/**
 * Handles user input and interactions.
 */
public class Ui {
    private final String logo =
            "___________              __   __________        __   \n" +
            "\\__    ___/____    _____|  | _\\______   \\ _____/  |_ \n" +
            "  |    |  \\__  \\  /  ___/  |/ /|    |  _//  _ \\   __\\\n" +
            "  |    |   / __ \\_\\___ \\|    < |    |   (  <_> )  |  \n" +
            "  |____|  (____  /____  >__|_ \\|______  /\\____/|__|  \n" +
            "               \\/     \\/     \\/       \\/             ";
    private final String name = "TaskBot";
    private Scanner sc;

    /**
     * Creates a UI with a Scanner instance.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Prints the title to the console.
     */
    public void printTitle() {
        System.out.println(logo);
    }

    /**
     * Prints the greeting to the console.
     */
    public void greet() {
        System.out.printf("Hello there, my name is %s.\nHow may I be of assistance today?\n", name);
    }

    /**
     * Prints a farewell message to the console.
     */
    public void sayBye() {
        System.out.println("Goodbye, I await your next visit.");
    }

    /**
     * Prints the error message to console.
     *
     * @param err The error message
     */
    public void showError(String err) {
        System.out.println(err);
    }

    /**
     * Prints a line separator to console.
     */
    public void showLine() {
        System.out.println("----------------------------------------------");
    }

    /**
     * Accepts user input.
     *
     * @return The user input as a string, empty if no input.
     */
    public String readCommand() {
        if (!sc.hasNextLine()) {
            return "";
        } else {
            return sc.nextLine();
        }
    }
}
