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
        return "I'm Duke\n"
                + "Why did you disturb my sleep?";

    }

    /**
     * Prints the ending message
     *
     * @return ending message of String type.
     */
    public static String endMessage() {
        return "Bye. Hope to not see you again soon!\n";
    }

    /**
     * Prints the help message
     *
     * @return help message of String type.
     */
    public static String helpMessage() {
        return "Full list of commands:\n"
                + "type \"bye\" to save your current list\n"
                + "\n"
                + "type \"list\" to view your current list\n"
                + "\n"
                + "type \"done <number>\" to mark the specific task as done\n"
                + "\n"
                + "type \"delete <number>\" to delete the specific task\n"
                + "\n"
                + "type \"find <keyword>\" to find a task with the specific keyword\n"
                + "\n"
                + "type \"snooze <number> <number of days(optional)>\" to snooze a task by a certain number of days."
                        +" By default snooze by one day.\n"
                + "\n"
                + "\n"
                + "To add new tasks:"
                + "\n"
                + "type \"todo <task message>\" to add a to do\n"
                + "\n"
                + "type \"event <task message> /<preposition> <yyyy-MM-dd HH:mm>\""
                        + " to add an event e.g event do chores /by 2020-08-08 12:00\n"
                + "\n"
                + "type \"deadline <task message> /<preposition> <yyyy-MM-dd HH:mm>\""
                        + " to add a deadline e.g deadline do chores /by 2020-08-08 12:00\n"
                + "\n";
    }


    /**
     * Prints the list of tasks
     *
     * @param list  list to be printed.
     * @return list of String type.
     */
    public static String printTaskList(TaskList list) {
        return list.toString() + "\n";
    }

    /**
     * Prints task
     *
     * @param task  task to be printed.
     * @return task of String type.
     */
    public static String printTask(Task task) {
        return task.toString() + "\n";
    }

    /**
     * Prints error message
     *
     * @param e  Exception to be printed.
     * @return Exception message
     */
    public static String unknownInputErrorMessage(Exception e) {
        return "DUKE DOES NOT UNDERSTAND YOU!!! @#%#$%^!@^%\n"
                + e
                + "\nTRY AGAIN!!!";
    }

}
