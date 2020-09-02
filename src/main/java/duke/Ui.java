package duke;

import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.control.Label;

/**
 * The Ui class handles all of the Ui interaction with the users using its various static methods
 */
public class Ui {
    private static String divider = "************************************************\n";

    /**
     * Returns a scene to be printed as an introduction upon start up Duke
     *
     * @return  returns the intro scene
     */
    public static Scene introduction() {
        String intro = "Hello! I'm Duke\nWhat can i do for you?\n";
        return new Scene(new Label(divider + intro + divider));
    }

    /**
     * returns a done statement while updating the task string by swapping the cross with a tick.
     * The doneTask argument must comply with the standard output string format of Duke tasks.
     *
     * @param doneTask the task that is to be marked done.
     * @return returns the updated line.
     */

    public static String updateDoneTask(String doneTask) {
        String updatedLine = doneTask.substring(0, 4) + "\u2713" + doneTask.substring(5);
        return updatedLine;
    }

    public static String done(String doneTask) {
        String updatedLine = updateDoneTask(doneTask);
        String message = divider + "Nice! I have marked this task as done:\n";
        message = message + updatedLine + "\n" + divider;
        return message;
    }

    /**
     * returns out the tasks in numerical order based on the ArrayList param.
     * The lines argument must contain Strings that comply with the standard output string format of Duke tasks.
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
        String list = divider;
        if (lines.size() > 0) {
            list = list + "Here are the matching tasks in your list!\n";
            for (int i = 0; i < lines.size(); i++) {
                int numbering = i + 1;
                String task = lines.get(i);
                list = list + (numbering + "." + task + "\n");
            }
        } else {
            list = list + "Unfortunately no tasks matches your keyword :(";
        }
        list = list + divider;
        return list;
    }

    /**
     * returns out a goodbye message when users exit Duke using the goodbye command
     *
     * @return a String representing the goodbye message
     */
    public static String bye() {
       return divider + "Bye! See you next time!" + "\n" + divider;
    }

    /**
     * Prints out a statement confirming that a task has been deleted, as well as the number of tasks left.
     *
     * @param task The task string representing the task that was deleted.
     * @param numberOfItems the remaining number of tasks.
     */

    /**
     * returns a statement confirming that a task has been deleted, as well as the number of tasks left.
     *
     * @param task The task string representing the task that was deleted.
     * @param numberOfItems the remaining number of tasks.
     * @return the string representing the deleted task.
     */
    public static String deletedTask(String task, int numberOfItems) {
        String message = divider + "Noted, the task has been deleted\n";
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
    public static String addedTask(Task task, int numberOfItems) {
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
     * Handles DukeExceptions{@link DukeException} thrown by Duke package methods by returning a string representing
     * the error.
     *
     * @param e The DukeException to be handled.
     * @return the string representing the exception.
     */
    public static String handleDukeException(DukeException e) {
        return divider + e.getMessage() + "\n" + divider;
    }
}
