package dukeclass;


/**
 * Deals with interactions with the user
 */
public class Ui {

    public static String welcomeMessage() {
        return "____________________________________________________________\n"
                + "     Hello! I'm Duke\n"
                + "     What can I do for you?\n"
                + "____________________________________________________________\n";
    }

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
     * Prints the tasks
     *
     * @param task  task to be printed.
     * @return task of String type.
     */
    public static String printTask(Task task) {
        return "____________________________________________________________\n"
                + task.toString() + "\n"
                + "____________________________________________________________\n";
    }

    public static String unknownInputErrorMessage(Exception e) {
        return "DUKE DOES NOT UNDERSTAND YOU!!!@#%#$%^!@^%\n"
                + e
                + "\nTRY AGAIN!!!";
    }

}
