package duke;

import java.util.Scanner;

/**
 * The Ui class handles the I/O of the programme.
 */
public class Ui {
    Scanner s;

    /**
     * Instantiates an Ui object.
     */
    Ui() {
        s = new Scanner(System.in);
    }

    /**
     * Displays a message in between two horizontal lines.
     * @param message A String to be displayed.
     */
    public void displayMessage(String message) {
        System.out.println("\t " + message);
    }

    /**
     * Displays the logo of Duke.
     */
    void showLogo() {
        String logo = "\t██████╗ ██╗   ██╗██╗  ██╗███████╗\n" +
                "\t██╔══██╗██║   ██║██║ ██╔╝██╔════╝\n" +
                "\t██║  ██║██║   ██║█████╔╝ █████╗  \n" +
                "\t██║  ██║██║   ██║██╔═██╗ ██╔══╝  \n" +
                "\t██████╔╝╚██████╔╝██║  ██╗███████╗\n" +
                "\t╚═════╝  ╚═════╝ ╚═╝  ╚═╝╚══════╝\n";
        System.out.println("\tHello boss! This is\n\n" + logo);
    }

    /**
     * Displays the welcome message.
     */
    void showWelcome() {
        showLogo();
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t___________________________________________\n");
    }

    /**
     * Prints a horizontal line.
     */
    void showLine() {
        System.out.println("\t___________________________________________");
    }

    /**
     * Displays the exiting message.
     */
    public void bye() {
        displayMessage("Bye. Hope to see you again soon");
    }

    /**
     * Reads in a line and returns it.
     * @return The next line read from the terminal.
     */
    String readCommand() {
        return s.nextLine();
    }
}
