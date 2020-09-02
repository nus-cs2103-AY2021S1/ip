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
    public String displayWelcome() {
        return "Hello from Bikini Bottom!\n"
            + "____________________________________________________________\n"
            + "Hello! I'm Spongebob\n"
            + "What can I do for you?";
    }

    /**
     * Displays a line.
     */

    public String displayLine() {
        return "____________________________________________________________\n";
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

    public String displayBye() {
        return "Bye. Hope to see you again soon! Bahahahaha!\n"
            + "____________________________________________________________\n";
    }
}
