/**
 * deals with the interaction with the user
 * prints necessary messages/ outputs
 */
public class TextUi {

    public static final String DIVIDER = "===================================================\n";

    /**
     * prints hello message with DUKE logo
     */
    public static void printHello() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println(DIVIDER + "Hello! I'm Duke\n" + "What can I do for you?\n" + DIVIDER);
    }

    /**
     * prints messages when new task is added
     *
     * @param description
     */
    public static void printNewTasks(String description) {
        System.out.println(DIVIDER + "Got it. I've added this task: \n" + description);
    }

    /**
     * prints messages as a summary when taskList is updated
     *
     * @param taskLength
     */
    public static void printTaskSummary(int taskLength) {
        System.out.println(String.format("Now you have %d tasks in the list. \n", taskLength) + DIVIDER);
    }

    /**
     * prints messages when error is encountered
     * @param exception
     */
    public static void printError(Exception exception) {
        System.out.println(DIVIDER + "☹ OOPS!!!" + exception.getMessage() + "Try again!\n" + DIVIDER);
    }

    /**
     * prints message for Done, Delete, List, Bye commands
     * @param description
     */
    public static void printMessage(String description) {
        System.out.println(DIVIDER + description + "\n" + DIVIDER);

    }
}
