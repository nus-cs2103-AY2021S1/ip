package duke;

import java.io.IOException;

public class Ui {
    /**
     * Returns a greeting message
     *
     * @return a greeting message
     */
    public static String greet() {
        return "Hello! This is Duke.\nWhat can I do for you?";
    }

    /**
     * Returns an exiting message
     *
     * @return an exiting message
     */
    public static String exit() {
        return "Bye. Duke is always there for you!";
    }

    /**
     * Returns an error message of command
     *
     * @return an error message
     */
    public static String commandError() {
        return "Sorry, I do not know what that means :(";
    }

    /**
     * Returns an error message of file
     *
     * @return an error message
     */
    public static String fileError() {
        return "Oops! Something went wrong :(";
    }

    /**
     * Returns an error message of list
     *
     * @return an error message
     */
    public static String listError() {
        return "There is no task in the list :)";
    }

    /**
     * Returns a given message
     *
     * @param msg the message given
     * @return the message given
     */
    public static String userMessage(String msg) {
        return msg;
    }

    /**
     * Returns a message for an add command result
     *
     * @param task the task added
     * @return a message or an error
     */
    public static String showAdd(String task) {
        String message = "Got it. I've added the task:\n";
        message += task;
        try {
            int size = TaskList.getTaskListSize();
            message += "\nNow you have " + size + " tasks in the list.";
            return message;
        } catch (IOException e) {
            return Ui.fileError();
        }
    }

    /**
     * Returns a message for a find command result
     *
     * @param task the list of tasks found
     * @return a message or an error
     */
    public static String showFind(String task) {
        if (task.isEmpty()) {
            return "There is no match in the list :)";
        } else {
            String message = "Here are the matching tasks in your list:";
            message += task;
            return message;
        }
    }

    /**
     * Returns a message for a done command result
     *
     * @param task the task done
     * @return a message to the user
     */
    public static String showDone(String task) {
        String message = "Well done! I've marked this as done:\n";
        message += task;
        return message;
    }

    /**
     * Returns a message for a delete command result
     *
     * @param task the task deleted
     * @return a message or an error
     */
    public static String showDelete(String task) {
        String message = "Noted! I've removed this task:\n";
        message += task;
        try {
            int size = TaskList.getTaskListSize();
            message += "\nNow you have " + size + " tasks in the list.";
            return message;
        } catch (IOException e) {
            return Ui.fileError();
        }
    }


}
