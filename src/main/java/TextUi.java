/**
 * deals with the interaction with the user
 * prints necessary messages/ outputs
 */
public class TextUi {

    public static final String DIVIDER = "===================================================\n";

    /**
     * prints hello message with DUKE logo
     */
    public static String printHello() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        return ("Hello! I'm Duke\n" + "What can I do for you?\n");
    }

    /**
     * prints messages when new task is added
     *
     * @param description
     */
    public static String printNewTasks(String description) {
        return ("Got it. I've added this task: \n" + description);
    }

    /**
     * prints messages as a summary when taskList is updated
     *
     * @param taskLength
     */
    public static String printTaskSummary(int taskLength) {
        return (String.format("Now you have %d tasks in the list. \n", taskLength));
    }

    /**
     * prints messages when error is encountered
     * @param exception
     */
    public static String printError(Exception exception) {
        return ("☹ OOPS!!!" + exception.getMessage() + "Try again!\n");
    }

    /**
     * prints message for Done, Delete, List, Bye commands
     * @param description
     */
    public static String printMessage(String description) {
        return (description + "\n");

    }
}
