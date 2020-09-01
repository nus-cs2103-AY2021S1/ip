package duke;

import duke.task.Task;
import duke.task.TaskList;

/**
 * The main class to deal with interactions with the user.
 */
public class Ui {

    /**
     * Displays the introduction to the user indicating that Duke is running.
     */
    public static void intro() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hi! I'm duke.Duke your friendly neighbourhood chat bot");
        System.out.println("What can i do for you?");
    }

    /**
     * Displays an indicator that the list is about to be shown
     */
    public static void tasks() {
        System.out.println("        Here are the tasks in your list:");
    }

    /**
     * Displays the error that was encountered as well as 2 lines of separation.
     * @param e
     */
    public static void errorEncounter(Exception e) {
        System.out.println("------------------------------------------------------");
        System.out.println(e.getMessage());
        System.out.println("------------------------------------------------------");
    }

    /**
     * Displays a goodbye message.
     */
    public static void finish() {
        System.out.println("        Bye have a good day!");
    }

    /**
     * Displays to the user the task that has been deleted.
     * @param deleted the deleted task
     * @param listSize the size of the list
     */
    public static void deleteMessage(String deleted, int listSize) {
        System.out.println("        Noted I've removed this task");
        System.out.println("        " + deleted);
        System.out.println("        you now have " + listSize + " tasks on the list");
    }

    /**
     * Displays to the user the task that has been added.
     * @param task the added task
     * @param listSize the size of the list
     */
    public static void addedTaskMessage(Task task, int listSize) {
        System.out.println("        Got it I have added this task:");
        System.out.println("        " + task.toString());
        System.out.println("        you now have " + listSize + " tasks on the list");
    }

    /**
     * Displays to the user the task that has been completed.
     * @param isDone indicating that the task is done
     * @param description the description of the task
     */
    public static void doneMessage(boolean isDone, String description) {
        System.out.println("        I have marked this as done:");
        System.out.println("        [" + isDone + "] " + description);
    }

    /**
     * Displays to the user a list of all the tasks which watch the given phrase.
     * @param list a new condensed list which match the phrase
     */
    public static void printFoundTask(TaskList list) {
        System.out.println("        I have found these matching tasks in your list:");
        for (Task t : list.getList()) {
            System.out.println("        " + t.toString());
        }
    }
}
