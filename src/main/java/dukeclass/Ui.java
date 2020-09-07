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
     * Prints the help message
     *
     * @return help message of String type.
     */
    public static String helpMessage() {
        return "Full list of commands:\n"
                + "type \"bye\" to save your current list\n"
                + "type \"list\" to view your current list\n"
                + "type \"done <number>\" to mark the specific task as done\n"
                + "type \"delete <number>\" to delete the specific task\n"
                + "type \"find <keyword>\" to find a task with the specific keyword\n"
                + "To add new tasks:"
                + "type \"todo <task>\" to add a to do\n"
                + "type \"event <task> /<preposition> <yyyy-MM-dd HH:mm>\""
                        + "to add an event e.g event do chores /by 2020-08-08 12:00\n"
                + "type \"deadline <task> /<preposition> <yyyy-MM-dd HH:mm>\""
                        + "to add a deadline e.g event do chores /by 2020-08-08 12:00\n"
                + "\n";
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
