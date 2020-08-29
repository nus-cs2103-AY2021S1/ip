package duke;

/**
 * Encapsulates the various methods and constants relevant to the UI of the Duke program.
 */
public class Ui {
    public static final String LINE = "____________________________________________________________";
    public static final String WELCOME_MESSAGE = "Hello, I'm Duke, your personal assistant!\n"
        + "What can I do for you?";
    public static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon! :)";

    /**
     * Prints a String wrapped with lines on the top and bottom.
     * @param txt the text to be printed
     */
    public static void print(String txt) {
        System.out.println(LINE + "\n" + txt + "\n" + LINE);
    }
}
