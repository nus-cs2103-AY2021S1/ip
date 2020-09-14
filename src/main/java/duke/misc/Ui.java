package duke.misc;

/**
 * UI class to handle aesthetics of Duke.
 */
public class Ui {
    static final String OPENING = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    static final String GOODBYE = "Goodbye. See you soon!";
    static final String WELCOME = OPENING + "\nHello I am Duke, A sentient AI here to help you!";
    static final String HELP_MESSAGE = "Don't panic! I am here. Here are some useful commands:\n" +
            "list: Lists all tasks at the moment\n" +
            "bye: Exit Duke\n" +
            "find [KEYWORD]: Find all tasks with a specific keyword\n" +
            "done [INDEX]: Complete the task at the specific index\n" +
            "delete [INDEX]: Delete the task at the specific index";
}
