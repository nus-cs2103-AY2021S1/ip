package duke.ui;

import java.util.Scanner;

/** Represents the Ui of the system that handles user interaction. */
public class Ui {

    private static String LINE = "\t" + "_".repeat(75);
    private Scanner sc;

    /** Constructs a Ui object. */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /** Displays a line to the user. */
    public void showLine() {
        System.out.println(LINE);
    }

    private void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    /** Displays the greeting to the user. Called when the user first runs the program. */
    public void showGreeting() {
        showWelcome();
        showLine();
        System.out.println("\t Hello! I'm Duke\n"
                + "\t What can I do for you?");
        showLine();
    }

    /** Displays the goodbye message to the user. */
    public void showBye() {
        showLine();
        System.out.println("\t Bye. Hope to see you again soon!");
        showLine();
    }

    /** Displays the specified message to the user.
     *
     * @param message The message to be displayed.
     */
    public void show(String message) {
        showLine();
        System.out.println(message);
        showLine();
    }

    /** Reads the user input.
     *
     * @return The user input as a String.
     */
    public String readCommand() {
        if (sc.hasNextLine()) {
            return sc.nextLine();
        } else {
            return "bye";
        }
    }

}