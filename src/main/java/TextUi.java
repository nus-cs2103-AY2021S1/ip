/**
 * deals with the interaction with the user
 * prints necessary messages/ outputs
 */
public class TextUi {

    public static final String divider = "===================================================\n";

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

        System.out.println(divider + "Hello! I'm Duke\n" + "What can I do for you?\n" + divider);
    }

    /**
     * prints messages of todo task
     *
     * @param description
     * @throws IllegalArgumentException
     */
    public static void printNewTasks(String description) {
        System.out.println(divider + "Got it. I've added this task: \n" + description);
    }

    public static void printTaskSummary(int taskLength) {
        System.out.println(String.format("Now you have %d tasks in the list.\n", taskLength) + divider);
    }

    public static void printError(Exception exception) {
        System.out.println(divider + "☹ OOPS!!!" + exception.getMessage() + "Try again!\n" + divider);
    }

    public static void printMessage(String description) {
        System.out.println(divider + "\n" + description + "\n" + divider);

    }

}