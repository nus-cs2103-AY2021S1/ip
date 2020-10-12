package duke;

import duke.task.Task;

import java.util.ArrayList;

/**
 * The Ui class handles all of the Ui interaction with the users using its various static methods
 */
public class Ui {
    private static String divider = "************************************************\n";

    /**
     * Returns a String to be printed as an introduction upon start up duke.Duke
     *
     * @return  returns the intro String
     */
    public static String introduction() {
        String intro = "Hello! I'm duke.Duke\nWhat can i do for you?\n";
        return divider + intro + divider;
    }

    /**
     * returns a done statement while updating the task string by swapping the cross with a tick.
     * The doneTask argument must comply with the standard output string format of duke.Duke tasks.
     *
     * @param doneTask the task that is to be marked done.
     * @return returns the updated line.
     */

    public static String updateDoneTask(String doneTask) {
        assert doneTask.length() >= 5 : "Invalid task done";
        return doneTask.substring(0, 4) + "\u2713" + doneTask.substring(5);
    }

    /**
     * Returns a string representing the message shown to users when a task is done.
     *
     * @param doneTask The task to be updated.
     * @return the string representing the output message.
     */
    public static String done(String doneTask) {
        String updatedLine = updateDoneTask(doneTask);
        String message = divider + "Nice! I have marked this task as done:\n";
        message = message + updatedLine + "\n" + divider;
        return message;
    }

    /**
     * returns out the tasks in numerical order based on the ArrayList param.
     * The lines argument must contain Strings that comply with the standard output string format of duke.Duke tasks.
     *
     * @param lines List of tasks to be printed.
     * @return The string representing the list of tasks.
     */
    public static String listTasks(ArrayList<String> lines) {
        String list = "";
        list = list + divider + "Here are the tasks in your list!\n";
        for (int i = 0; i < lines.size(); i++) {
            int numbering = i + 1;
            String task = lines.get(i);
            list = list + (numbering + "." + task + "\n");
        }
        list = list + divider;
        return list;
    }

    /**
     * Same as listTasks() except that the message printed out is slightly different. If the ArrayList supplied is
     * empty, a "no matching tasks" message would be printed.
     *
     * @param lines The list of tasks to print out.
     * @return A string representing the list of tasks / message to print out.
     */
    public static String listMatchingTasks(ArrayList<String> lines) {
        String list = divider; // Listing matching tasks will always start with a divider string
        if (lines.size() > 0) {
            list = list + "Here are the matching tasks in your list!\n";
            for (int i = 0; i < lines.size(); i++) {
                int numbering = i + 1;
                String task = lines.get(i);
                list = list + numbering + "." + task + "\n";
            }
        } else {
            list = list + "Unfortunately no tasks matches your keyword :(";
        }
        list = list + divider;
        return list;
    }

    /**
     * Returns a String representing the message shown when users exit duke.Duke.
     *
     * @return The string representing the goodbye message.
     */
    public static String bye() {
        return divider + "Bye! See you next time!" + "\n" + divider;
    }

    /**
     * Returns a message representing the tagged task or an error message depending on the supplied
     * boolean.
     *
     * @param task the potentially tagged task
     * @param tagged the boolean representing if the task is tagged or not
     * @return the message representing the outcome
     */
    public static String taggedTask(String task, boolean tagged) {
        String message = "";
        if (tagged) {
            message = divider + "Noted. The following task has been tagged.\n";
            message = message + task + "\n" + divider + "\n";
        } else {
            message = divider + "Hey, the task has already been tagged! Remove the tag first!\n" + divider;
        }
        return message;
    }

    /**
     * Prints out messages depending if a task was successfully untagged or not
     *
     * @param task the task that might have been untagged
     * @param removed true if the task is untagged, false otherwise
     * @return the message representing the outcome
     */
    public static String untaggedTask(String task, boolean removed) {
        if (removed) {
            String message = divider + "Noted. The following task has been untagged!\n";
            message = message + task + "\n" + divider + "\n";
            return message;
        } else {
            String message = divider + "The task did not have a tag to begin with!\n";
            message = message + divider;
            return message;
        }
    }

    /**
     * returns a statement confirming that a task has been deleted, as well as the number of tasks left.
     *
     * @param task The task string representing the task that was deleted.
     * @param numberOfItems the remaining number of tasks.
     * @return the string representing the deleted task.
     */
    public static String deletedTask(String task, int numberOfItems) {
        String message = divider + "Noted, the following task has been deleted\n";
        message = message + task + "\n" + divider + "\n";
        message = message + "Now you have " + numberOfItems + " tasks in the list.";
        return message;
    }

    /**
     * Prints out a statement confirming that a task has been added, as well as the number of tasks currently.
     *
     * @param task The task string representing the task that was added.
     * @param numberOfItems the current number of tasks.
     * @return the message representing the added task
     */
    public static String addedTask(String task, int numberOfItems) {
        String message = "";
        if (numberOfItems < 100) {
            message = message + divider + "Got it, I've added this task:\n";
            message = message + " " + task + "\n";
            message = message + "Now you have " + numberOfItems + " tasks in the list.\n";
            message = message + divider;
        } else {
            message = message + divider + "Sorry, the list is full!\n" + divider;
        }
        return message;
    }


    /**
     * Handles DukeExceptions{@link DukeException} thrown by duke.Duke package methods by returning a string
     * representing the error.
     *
     * @param e The DukeException to be handled.
     * @return the string representing the exception.
     */
    public static String handleDukeException(DukeException e) {
        return divider + e.getMessage() + "\n" + divider;
    }
}
