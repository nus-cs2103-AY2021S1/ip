package duke;

import java.util.Scanner;

/**
 * Encapsulates the various methods and constants relevant to the UI of the Duke program.
 */
public class Ui {
    public static final String LINE = "____________________________________________________________";
    private static final String WELCOME_MESSAGE = "Hello, I'm Duke, your personal assistant!\n"
        + "What can I do for you?";
    private static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon! :)";

    private Scanner sc;

    /**
     * Instantiates a Ui instance.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Returns the next input of the user.
     * @return the next input of the user
     */
    public String getInput() {
        return sc.nextLine();
    }

    /**
     * Prints a String wrapped with lines on the top and bottom.
     * @param txt the text to be printed
     */
    public static void print(String txt) {
        System.out.println(LINE + "\n" + txt + "\n" + LINE);
    }

    /**
     * Prints a hardcoded greeting to the user.
     */
    public static void printGreeting() {
        print(WELCOME_MESSAGE);
    }

    /**
     * Prints a hardcoded Goodbye to the user.
     */
    public static void printGoodbye() {
        print(GOODBYE_MESSAGE);
    }
}
