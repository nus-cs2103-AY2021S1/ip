package duke.misc;

/**
 * UI class to handle messages that user sees.
 */
public final class Ui {
    /**
     * Greeting messages.
     */
    public static final String WELCOME = "D\n    U\n        K\n            E\n"
            + "\nHello I am Duke, a sentient AI here to help you!"
            + " Type 'help' for a brief intro to the various commands!";
    static final String GOODBYE = "Goodbye. See you soon!";

    /**
     * Action notification messages.
     */
    static final String LIST_MESSAGE = "Here are your tasks:\n";
    static final String EMPTY_LIST_MESSAGE = "No tasks found.";
    static final String HELP_MESSAGE = "Here are some useful commands:\n"
            + "- list: Lists all tasks at the moment.\n"
            + "- bye: Exit Duke.\n"
            + "- find [KEYWORD]: Find all tasks with a specific keyword\n"
            + "- done [INDEX]: Complete the task at the specific index\n"
            + "- delete [INDEX]: Delete the task at the specific index\n"
            + "- todo [DESCRIPTION]: Adds a todo task\n"
            + "- event [DESCRIPTION] /at [TIMING]: Adds an event task. Timing must be in yyyy-MM-dd format\n"
            + "- deadline [DESCRIPTION] /by [TIMING]: Adds a deadline task. Timing must be in yyyy-MM-dd format";
    static final String ADD_TASK_MESSAGE = "Got it. I've added this task:\n    %s\n"
            + "Now you have %d tasks in the list.";
    static final String COMPLETE_TASK_MESSAGE = "    Nice! I've marked this task as done:\n    %s";
    static final String DELETE_TASK_MESSAGE = "    Nice! I've removed this task:\n    %s\n"
            + "    Now you have %d tasks in the list.";
}
