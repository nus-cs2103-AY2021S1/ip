package duke;

/**
 * String constants for printing to user.
 */
public class StringConstants {
    public static final String[] GREETING_MESSAGES = new String[]{
        "Hello! I'm Duke",
        "What can I do for you?"
    };
    public static final String DONE_MESSAGE = "Nice! I've marked this task as done:";
    public static final String ADD_MESSAGE = "Got it. I've added this task: ";
    public static final String DELETE_MESSAGE = "Noted. I've removed this task:";
    public static final String COUNT_MESSAGE = "Now you have %d tasks in the list.";
    public static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";
    public static final String TASK_FOUND_MESSAGE = "Here are the matching tasks in your list:";
    public static final String NO_TASK_FOUND_MESSAGE = "No matching tasks found.";

    /**
     * Joins array of strings together separated by newline character.
     * @param strings array of strings to join
     * @return joined string
     */
    public static String joinStrings(String[] strings) {
        return String.join("\n", strings);
    }

    public static String getGreeting() {
        return joinStrings(StringConstants.GREETING_MESSAGES);
    }

}
