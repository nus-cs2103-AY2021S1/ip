package duke.ui;

import duke.tasks.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;

/** Represents the UI that prints out messages in Duke format. */
public class Ui {

    private Scanner sc;

    /** Constructor. */
    public Ui() {
        sc = new Scanner(System.in);
    }

    private void showLine() {
        System.out.println("____________________________________________________________");
    }

    /** Shows the welcome message in Duke format. */
    public void showWelcome() {
        format("Hello! I'm Duke\nWhat can I do for you?");
    }

    /** Shows the goodbye message in Duke format. */
    public void showBye() {
        format("Bye. Hope to see you again soon!");
    }

    /** Reads the user input from the scanner. */
    public String readCommand() {
        return sc.nextLine();
    }

    private void format(String message) {
        showLine();
        System.out.println(message);
        showLine();
    }

    /** Shows the list of tasks in Duke format.
     *
     * @param tasks The list of tasks.
     */
    public void formatLst(ArrayList<Task> tasks) {
        showLine();
        int size = tasks.size();
        if (size > 0) {
            System.out.println("Here are the tasks in your list:");
            for (int i = 1; i <= tasks.size(); i++) {
                System.out.println(i + "." + tasks.get(i-1));
            }
        } else {
            System.out.println("There are no tasks in your list.");
        }
        showLine();
    }

    /** Shows the message where a task is marked as done in Duke format.
     *
     * @param tasks The list of tasks.
     * @param num The index of the task that is marked as done.
     */
    public void formatMarkAsDone(ArrayList<Task> tasks, int num) {
        showLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(num));
        showLine();
    }

    /** Shows the list of tasks after a task is added in Duke format.
     *
     * @param tasks The list of tasks.
     * @param task The task that is added.
     */
    public void formatAddTask(ArrayList<Task> tasks, Task task) {
        int size = tasks.size();
        showLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println(String.format("Now you have %d %s in the list.", size, size == 1 ? "task" : "tasks"));
        showLine();
    }

    /** Shows the list of tasks after a task is deleted in Duke format.
     *
     * @param tasks The list of tasks.
     * @param num The index of the task that is deleted.
     */
    public void formatDeleteTask(ArrayList<Task> tasks, int num) {
        int sizeAfterDeletion = tasks.size() - 1;
        showLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println(tasks.get(num));
        System.out.println(String.format("Now you have %d %s in the list.", sizeAfterDeletion,
                sizeAfterDeletion == 1 ? "task" : "tasks"));
        showLine();
    }

    /** Shows the list of tasks that occur on the specified date in Duke format.
     *
     * @param tasksOnDate The list of tasks that occur on the specified date.
     * @param queryDate The specified date.
     */
    public void formatShowTasksOnDate(ArrayList<Task> tasksOnDate, LocalDate queryDate) {
        showLine();
        if (!tasksOnDate.isEmpty()) {
            System.out.println(String.format("The following deadlines/events are scheduled on %s.",
                    queryDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))));
            for (int i = 1; i <= tasksOnDate.size(); i++) {
                System.out.println(i + "." + tasksOnDate.get(i-1));
            }
        } else {
            System.out.println(String.format("There are no deadlines/events scheduled on %s.",
                    queryDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))));
        }
        showLine();
    }

    /** Shows the list of tasks that contain the keyword in Duke format.
     *
     * @param matchingTasks The list of tasks that contain the keyword.
     */
    public void formatFindTasks(ArrayList<Task> matchingTasks) {
        showLine();
        if (!matchingTasks.isEmpty()) {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 1; i <= matchingTasks.size(); i++) {
                System.out.println(i + "." + matchingTasks.get(i-1));
            }
        } else {
            System.out.println("There are no matching tasks in your list.");
        }
        showLine();
    }

    /** Shows the error message in Duke format.
     *
     * @param e The exception that is thrown.
     */
    public void showError(Exception e) {
        showLine();
        if (e instanceof DateTimeParseException) {
            System.out.println("☹ OOPS!!! The format of the date given is invalid.");
        } else {
            System.out.println(e.getMessage());
        }
        showLine();
    }
}
