package duke.ui;

import static duke.tasklist.TaskList.getNumCompletedTasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import duke.tasks.Task;

/** Represents the UI that prints out messages in Duke format. */
public class Ui {

    /** The welcome message in Duke format. */
    public static final String DIALOG_WELCOME = "Hello! I'm Milk.\nWhat can I help you with?";
    /** The goodbye message in Duke format. */
    public static final String DIALOG_BYE = "You're going? Bye :( Hope to see you again soon.";
    /** The scanner that scans the user input. */
    private Scanner sc;

    /** Constructs a new Ui object. */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /** Reads the user input from the scanner. */
    public String readCommand() {
        return sc.nextLine();
    }

    /** Gives the list of tasks in Duke format.
     *
     * @param tasks The list of tasks.
     * @return The list of tasks in Duke format.
     */
    public String formatLst(ArrayList<Task> tasks) {
        StringBuilder s;
        int size = tasks.size();
        if (size > 0) {
            s = new StringBuilder("Here are the tasks in your list:\n");
            for (int i = 1; i <= tasks.size(); i++) {
                s.append(i).append(".").append(tasks.get(i - 1)).append("\n");
            }
        } else {
            s = new StringBuilder("There are no tasks in your list.");
        }
        return s.toString();
    }

    /** Gives the message where a task is marked as done in Duke format.
     *
     * @param tasks The list of tasks.
     * @param index The index of the task that is marked as done.
     * @return The message where a task is marked as done in Duke format.
     */
    public String formatMarkAsDone(ArrayList<Task> tasks, int index) {
        return "Nice! I've marked this task as done:\n" + tasks.get(index);
    }

    /** Gives the list of tasks after a task is added in Duke format.
     *
     * @param tasks The list of tasks.
     * @param task The task that is added.
     * @return The list of tasks after a task is added in Duke format.
     */
    public String formatAddTask(ArrayList<Task> tasks, Task task) {
        int size = tasks.size();
        return String.format("Okay. I've added this task:\n" + task + "\nNow you have %d %s in the list.",
            size, size == 1 ? "task" : "tasks");
    }

    /** Gives the list of tasks after a task is deleted in Duke format.
     *
     * @param tasks The list of tasks.
     * @param index The index of the task that is deleted.
     * @return The list of tasks after a task is deleted in Duke format.
     */
    public String formatDeleteTask(ArrayList<Task> tasks, int index) {
        int sizeAfterDeletion = tasks.size() - 1;
        return String.format("Alright. I've removed this task:\n" + tasks.get(index)
            + "\nNow you have %d %s in the list.",
            sizeAfterDeletion, sizeAfterDeletion == 1 ? "task" : "tasks");
    }

    /** Gives the list of tasks that occur on the specified date in Duke format.
     *
     * @param tasksOnDate The list of tasks that occur on the specified date.
     * @param queryDate The specified date.
     * @return The list of tasks that occur on the specified date in Duke format.
     */
    public String formatShowTasksOnDate(ArrayList<Task> tasksOnDate, LocalDate queryDate) {
        if (!tasksOnDate.isEmpty()) {
            StringBuilder s = new StringBuilder(String.format("The following deadlines/events are scheduled on %s.",
                    queryDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))));
            for (int i = 1; i <= tasksOnDate.size(); i++) {
                s.append("\n").append(i).append(".").append(tasksOnDate.get(i - 1));
            }
            return s.toString();
        } else {
            return String.format("There are no deadlines/events scheduled on %s.",
                    queryDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
        }
    }

    /** Gives the list of tasks that contain the keyword in Duke format.
     *
     * @param matchingTasks The list of tasks that contain the keyword.
     * @return The list of tasks that contain the keyword in Duke format.
     */
    public String formatFindTasks(ArrayList<Task> matchingTasks) {
        if (!matchingTasks.isEmpty()) {
            StringBuilder s = new StringBuilder("Here are the matching tasks in your list:");
            for (int i = 1; i <= matchingTasks.size(); i++) {
                s.append("\n").append(i).append(".").append(matchingTasks.get(i - 1));
            }
            return s.toString();
        } else {
            return "There are no matching tasks in your list.";
        }
    }

    /** Gives the error message in Duke format.
     *
     * @param e The exception that is thrown.
     * @return The error message in Duke format.
     */
    public String showError(Exception e) {
        return e.getMessage();
    }

    /** Gives the statistics of the taskList in Duke format.
     *
     * @param tasks The list of tasks.
     * @return The statistics of the taskList in Duke format.
     */
    public String formatStats(ArrayList<Task> tasks) {
        // Gets the number of tasks completed in this session.
        String s = String.format("You have completed %d %s in this session.", getNumCompletedTasks(),
            getNumCompletedTasks() == 1 ? "task" : "tasks");
        // Gets the number of tasks uncompleted in the current list.
        int numUncompletedTasks = 0;
        for (Task task : tasks) {
            if (!task.isDone()) {
                numUncompletedTasks++;
            }
        }
        if (numUncompletedTasks > 0) {
            s += String.format("\nYou have %d uncompleted %s in your current list.", numUncompletedTasks,
                numUncompletedTasks == 1 ? "task" : "tasks");
        }
        return s;
    }
}
