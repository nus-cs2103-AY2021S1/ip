package duke.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.tasks.Task;

/** Represents the UI that prints out messages in Duke format. */
public class Ui {

    /** The scanner that scans the user input. */
    private Scanner sc;

    /** The welcome message in Duke format. */
    public static final String showWelcome = "Hello! I'm Milk.\nWhat can I help you with?";

    /** The goodbye message in Duke format. */
    public static final String showBye = "You're going? Bye :( Hope to see you again soon.";

    /** Constructor. */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /** Reads the user input from the scanner. */
    public String readCommand() {
        return sc.nextLine();
    }

    /** Shows the list of tasks in Duke format.
     *
     * @param tasks The list of tasks.
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

    /** Shows the message where a task is marked as done in Duke format.
     *
     * @param tasks The list of tasks.
     * @param num The index of the task that is marked as done.
     */
    public String formatMarkAsDone(ArrayList<Task> tasks, int num) {
        return "Nice! I've marked this task as done:\n" + tasks.get(num);
    }

    /** Shows the list of tasks after a task is added in Duke format.
     *
     * @param tasks The list of tasks.
     * @param task The task that is added.
     */
    public String formatAddTask(ArrayList<Task> tasks, Task task) {
        int size = tasks.size();
        return String.format("Okay. I've added this task:\n" + task + "\nNow you have %d %s in the list.",
            size, size == 1 ? "task" : "tasks");
    }

    /** Shows the list of tasks after a task is deleted in Duke format.
     *
     * @param tasks The list of tasks.
     * @param num The index of the task that is deleted.
     */
    public String formatDeleteTask(ArrayList<Task> tasks, int num) {
        int sizeAfterDeletion = tasks.size() - 1;
        return String.format("Alright. I've removed this task:\n" + tasks.get(num)
            + "\nNow you have %d %s in the list.",
            sizeAfterDeletion, sizeAfterDeletion == 1 ? "task" : "tasks");
    }

    /** Shows the list of tasks that occur on the specified date in Duke format.
     *
     * @param tasksOnDate The list of tasks that occur on the specified date.
     * @param queryDate The specified date.
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

    /** Shows the list of tasks that contain the keyword in Duke format.
     *
     * @param matchingTasks The list of tasks that contain the keyword.
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

    /** Shows the error message in Duke format.
     *
     * @param e The exception that is thrown.
     */
    public String showError(Exception e) {
        if (e instanceof DateTimeParseException) {
            return "Harh? The format of the date given is invalid.";
        } else {
            return e.getMessage();
        }
    }
}
