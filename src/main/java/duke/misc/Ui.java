package duke.misc;

/**
 * UI class to handle aesthetics of Duke.
 */
public class Ui {
    static final String OPENING = "D\n    U\n        K\n            E\n";
    static final String GOODBYE = "Goodbye. See you soon!";
    static final String HELP_MESSAGE = "Don't panic! I am here. Here are some useful commands:\n"
            + "- list: Lists all tasks at the moment.\n"
            + "- bye: Exit Duke.\n"
            + "- find [KEYWORD]: Find all tasks with a specific keyword\n"
            + "- done [INDEX]: Complete the task at the specific index\n"
            + "- delete [INDEX]: Delete the task at the specific index";
    public static final String WELCOME = OPENING + "\nHello I am Duke, a sentient AI here to help you!";
}
