package dukeclass;


/**
 * Deals with interactions with the user
 */
public class Ui {

    /**
     * Prints the welcome message
     *
     * @return welcome message of String type.
     */
    public static String welcomeMessage() {
        return "____________________________________________________________\n"
                + "     Hello! I'm Duke\n"
                + "     What can I do for you?\n"
                + "____________________________________________________________\n";
    }

    /**
     * Prints the ending message
     *
     * @return ending message of String type.
     */
    public static String endMessage() {
        return "____________________________________________________________\n"
                + "     Bye. Hope to see you again soon!\n"
                + "____________________________________________________________\n";
    }

    /**
     * Prints the list of tasks
     *
     * @param list  list to be printed.
     * @return list of String type.
     */
    public static String printTaskList(TaskList list) {
        return "____________________________________________________________\n"
                + list.toString() + "\n"
                + "____________________________________________________________\n";
    }

    /**
     * Prints task
     *
     * @param task  task to be printed.
     * @return task of String type.
     */
    public static String printTask(Task task) {
        return "____________________________________________________________\n"
                + task.toString() + "\n"
                + "____________________________________________________________\n";
    }

    /**
     * Prints error message
     *
     * @param e  Exception to be printed.
     * @return Exception message
     */
    public static String unknownInputErrorMessage(Exception e) {
        return "DUKE DOES NOT UNDERSTAND YOU!!!@#%#$%^!@^%\n"
                + e
                + "\nTRY AGAIN!!!";
    }

}
