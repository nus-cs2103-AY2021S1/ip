package Duke;

import java.util.ArrayList;

public class Ui {
    private static String divider = "************************************************\n";

    /**
     * Introduces Duke to the users upon start up by printing an introduction.
     */
    public static void introduction() {
        String intro = "Hello! I'm Duke\nWhat can i do for you?\n";
        System.out.println(divider + intro + divider);
    }

    /**
     * Prints out a done statement while updating the task string by swapping the cross with a tick.
     * The doneTask argument must comply with the standard output string format of Duke tasks.
     *
     * @param doneTask the task that is to be marked done.
     * @return returns the updated line.
     */
    public static String done(String doneTask) {
        String updatedLine = doneTask.substring(0, 4) + "\u2713" + doneTask.substring(5);
        System.out.println(divider + "Nice! I have marked this task as done:");
        System.out.println(updatedLine + "\n" + divider);
        return updatedLine;
    }

    /**
     * Prints out the tasks in numerical order based on the ArrayList param.
     * The lines argument must contain Strings that comply with the standard output string format of Duke tasks.
     *
     * @param lines List of tasks to be printed.
     */
    public static void listTasks(ArrayList<String> lines) {
        System.out.println(divider);
        System.out.println("Here are the tasks in your list!");
        for (int i = 0; i < lines.size(); i++) {
            int numbering = i + 1;
            String task = lines.get(i);
            System.out.println(numbering + "." + task);
        }
        System.out.println(divider);
    }

    /**
     * Prints out a goodbye message when users exit duke.
     */
    public static void bye() {
        System.out.println(divider + "Bye! See you next time!" + "\n" + divider);
    }

    /**
     * Prints out a statement confirming that a task has been deleted, as well as the number of tasks left.
     *
     * @param task The task string representing the task that was deleted.
     * @param numberOfItems the remaining number of tasks.
     */
    public static void deletedTask(String task, int numberOfItems) {
        System.out.println(divider + "Noted, the task has been deleted");
        System.out.println(task + "\n" + divider);
        System.out.println("Now you have " + numberOfItems + " tasks in the list.");
    }

    /**
     * Prints out a statement confirming that a task has been added, as well as the number of task currently.
     *
     * @param task The task string representing the task that was added.
     * @param numberOfItems the current number of tasks.
     */
    public static void addedTask(Task task, int numberOfItems) {
        if (numberOfItems < 100) {
            System.out.println(divider + "Got it, I've added this task:");
            System.out.println(" " + task);
            System.out.println("Now you have " + numberOfItems + " tasks in the list.");
            System.out.println(divider);
        } else {
            System.out.println(divider + "Sorry, the list is full!\n" + divider);
        }
    }

    /**
     * Handles DukeExceptions{@link DukeException} thrown by Duke package methods by printing them out for users to see.
     *
     * @param e The DukeException to be handled.
     */
    public static void handleDukeException(DukeException e) {
        System.out.println(divider + e.getMessage() + "\n" + divider);
    }
}
