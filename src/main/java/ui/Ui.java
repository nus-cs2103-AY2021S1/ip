package ui;

import data.exception.DukeException;
import data.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * User interface of Duke which manages all text output.
 */
public class Ui {

    private final Scanner user_input;

    public Ui() {
        this.user_input = new Scanner(System.in);
    }

    /**
     * Generates and prints greeting message upon launching of Duke.
     */
    public static String showGreeting() {
        String greeting = "Oh Golly! Who do we have here?\nThe name's Duke, how can I be of assistance?";
        return greeting;
    }

    /**
     * Generates and prints Duke specific error messages upon catching them.
     * @param e Duke specific exception.
     */
    public String showDukeError(DukeException e) {
        return e.getMessage();
    }

    /**
     * Prints each task in the input tasklist in a labelled and ordered list view.
     * Generates and prints a message when input tasklist is empty.
     * @param taskList to be printed as an indexed list.
     */
    public String showTaskList(ArrayList<Task> taskList) {
        String output = "";
        for (int i = 0; i < taskList.size(); i++) {
            Task currentTask = taskList.get(i);
            if (i == taskList.size() - 1) {
                output = output + (i + 1) + "." + currentTask;
            } else {
                output = output + (i + 1) + "." + currentTask + "\n";
            }
        }
        String getListMsg = "Here are the tasks in your list:";
        String emptyListMsg = "Oh dear, it seems that your tasks list is empty!";
        if (taskList.size() < 1) {
            return emptyListMsg;
        } else {
            return getListMsg + "\n" + output;
        }
    }

    /**
     * Prints a message which displays the total number of tasks in a tasklist.
     * @param i the total number of task in a tasklist.
     */
    public String showTotalTasks(int i) {
        String totalTaskMsg = "Marvellous! Now you have " + i + " tasks in your list!";
        return totalTaskMsg;
    }

    /**
     * Generates and prints the exit message upon exiting Duke.
     */
    public String showExit() {
        String parting = "Well, I'm utterly knackered! Cheerios!";
        return parting;
    }

    /**
     * Generates and prints the task that has been added to the list.
     * @param task that is added to the tasklist.
     */
    public String showAddedToList(Task task) {
        String add_to_listMsg = "No worries, the following task has been added to your list:";
        return add_to_listMsg + "\t" + task;
    }

    public String showFindResults(ArrayList<Task> taskList, String user_input) {
        String output = "";
        for (int i = 0; i < taskList.size(); i++) {
            Task currentTask = taskList.get(i);
            if (i == taskList.size() - 1) {
                output = output + (i + 1) + "." + currentTask;
            } else {
                output = output + (i + 1) + "." + currentTask + "\n";
            }
        }
        String getListMsg = "Splendid! Here are the tasks in your list that matches " + "'" + user_input + "'" + ":";
        String emptyListMsg = "Oh dear, it seems that there are no tasks that matches " + "'" + user_input + "'" + ".";
        if (taskList.size() < 1) {
            return emptyListMsg;
        } else {
            return getListMsg + "\n" + output;
        }
    }

    /**
     * Generates and prints the unknown error message upon encountering an unidentifiable error.
     */
    public static String showUnknownError() {
        String unknownErrorMsg = "OH FIDDLESTICKS, WE SEEM TO HAVE HIT A BUMP ON THE ROAD HERE. "
                + "AN UNKNOWN ERROR HAS BEEN DETECTED.";
        return unknownErrorMsg;
    }

    public String getUserInput() {
        return this.user_input.nextLine();
    }

    /**
     * Generates and prints the done message when a task is being marked as done.
     * @param task that is being marked as done.
     */
    public static String showMarkDone(Task task) {
        String markDoneMsg = "Splendid! I've marked the following task as done:";
        String taskDetails = "  [" + task.getStatusIcon() + "] " + task.getDescription();
        return markDoneMsg + "\n" + taskDetails;
    }

    /**
     * Generates and prints the delete message when a task is being deleted.
     * @param task that is being deleted.
     */
    public String showDelete(Task task) {
        String deleteMsg = "No worries, the following task has been deleted from your list:";
        String taskDetails = "  [" + task.getStatusIcon() + "] " + task.getDescription();
        return deleteMsg + "\n" + taskDetails;
    }
}
