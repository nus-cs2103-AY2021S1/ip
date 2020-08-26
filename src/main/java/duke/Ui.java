package duke;

/**
 * UI class to handle aesthetics of Duke.
 */
public class Ui {
    private static final String OPENING = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String WELCOME = OPENING + "\nHello I am Duke!\nWhat can I help you with?";
    private static final String GOODBYE = "Goodbye. See you soon!";
    private static final String LINE = "--------------------------------------------------";

    /**
     * Function to print Duke's welcome message.
     */
    public static void printWelcome() {
        System.out.println(WELCOME);
        System.out.println(LINE);
    }

    /**
     * Function to print Duke's goodbye message.
     */
    public static void printGoodbye() {
        System.out.println(LINE);
        System.out.println(GOODBYE);
    }

    /**
     * Adds line under given input for aesthetics.
     *
     * @param input Input for which line shall be printed under.
     */
    public static void addLine(String input) {
        System.out.println(input);
        System.out.println(LINE);
    }
}