package ui;

import java.util.Scanner;

/**
 * Encapsulates a User Interface class.
 */
public class Ui {
    private Scanner scanner = new Scanner(System.in);

    /**
     * Displays the welcome message.
     */
    public void displayWelcome() {
        System.out.println("Hello from Bikini Bottom!");
        System.out.println("____________________________________________________________\n"
            + "Hello! I'm Spongebob\n"
            + "What can I do for you?");
    }

    /**
     * Displays a line.
     */

    public void displayLine() {
        System.out.println("____________________________________________________________\n");
    }

    /**
     * Reads the command from the user.
     * @return User input
     */

    public String readCommand() {
        String str = scanner.nextLine();
        return str;
    }

    /**
     * Displays message when program is closed
     */

    public void displayBye() {
        System.out.println("Bye. Hope to see you again soon! Bahahahaha!\n"
                + "____________________________________________________________\n");
    }
}
