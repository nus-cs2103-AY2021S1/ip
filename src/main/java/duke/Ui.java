package duke;

import java.util.Scanner;

/**
 * Interacts with the user by printing instructions and responses.
 */
public class Ui {
    private final Scanner sc;

    /**
     * Constructor to create Ui object and initializes scanner.
     *
     * <p>
     * Prints the errors, responses and instructions which user should get,
     * as well as receives user input to pass on to the rest of the program
     * via scanner initialized.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints out welcome message when user starts program.
     */
    public void showWelcome() {
        String logo = " ______  ___       __         __        _____\n"
                + "   |    /         /  \\       /  \\     /\n"
                + "   |    \\___     /____\\     /____\\   |\n"
                + "   |        \\   /      \\   /      \\   \\\n"
                + " ------  ___/  /        \\ /        \\    -----\n";
        System.out.println("Hello! I'm\n" + logo + "\nWhat can I do for you?");
    }

    /**
     * Prints our error when file cannot be found.
     */
    public void showLoadingError() {
        System.out.println("Created a new directory 'data' and new text file 'database.txt' for you!");
    }

    /**
     * Prints out error message when program runs into one.
     *
     * @param error message to be printed out for user to see.
     */
    public void showError(String error) {
        System.out.println(error);
    }

    /**
     * Responsible for taking in user input.
     *
     * @return command line from user input.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints out message to send off user as user stops program.
     */
    public void showExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints out message or instruction needed for user to see.
     *
     * @param message text that is needed to be printed out for user.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

}
